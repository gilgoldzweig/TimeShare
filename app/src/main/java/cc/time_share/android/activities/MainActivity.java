package cc.time_share.android.activities;

import static android.Manifest.permission.*;

import android.app.ActivityOptions;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.haha.guava.base.Joiner;

import org.apache.commons.lang3.StringUtils;

import java.util.StringJoiner;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cc.time_share.android.R;
import cc.time_share.android.adapters.RequestHolder;
import cc.time_share.android.location.GPSTracker;
import cc.time_share.android.models.Request;
import cc.time_share.android.server.ServerHandler;


public class MainActivity extends AppCompatActivity implements OnMapReadyCallback {
    private static final String TAG = MainActivity.class.getSimpleName();

    @BindView(R.id.toolbar_main)
    Toolbar mToolBar;
    @BindView(R.id.fab_create)
    FloatingActionButton mCreateFab;
    @BindView(R.id.requests_recycler_view)
    RecyclerView mRequestsRecyclerView;

    private ServerHandler mServerHandler;
    private MapFragment mMapFragment;
    private GoogleMap mGoogleMap;
    private final int PERMISSION_KEY = 999999999;
    private final String[] permissions = new String[]{ACCESS_FINE_LOCATION, ACCESS_COARSE_LOCATION};
    private GPSTracker mGPSTracker;
    private FirebaseRecyclerAdapter<Request, RequestHolder> mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mToolBar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(mToolBar);
        mMapFragment = (MapFragment) getFragmentManager()
                .findFragmentById(R.id.mapFragmentView);
        mServerHandler = ServerHandler.getInstance();

        subscribeListToRequests();
        subscribeMapToRequests();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(permissions, PERMISSION_KEY);
        }
        mGPSTracker = new GPSTracker(this);
        mMapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.mGoogleMap = googleMap;
        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(
                new LatLng(mGPSTracker.getLatitude(),
                        mGPSTracker.getLongitude()), 18));
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    private void subscribeListToRequests() {
        DatabaseReference requestsRef =
                FirebaseDatabase.getInstance().getReference().child("requests");
        mAdapter = new FirebaseRecyclerAdapter<Request, RequestHolder>(
                Request.class, android.R.layout.item_requests, RequestHolder.class, requestsRef) {
            @Override
            public void populateViewHolder(RequestHolder requestViewHolder,
                                           Request request,
                                           int position) {
                requestViewHolder.mNameText.setText(request.getName());
                requestViewHolder.mTitleText.setText(request.getTitle());
                requestViewHolder.mDescriptionText.setText(request.getDescription());
                requestViewHolder.mSkillsText.setText(StringUtils.join(request.getNeeds(), ", "));
            }
        };
        mRequestsRecyclerView.setAdapter(mAdapter);
    }

    private void subscribeMapToRequests() {
        mServerHandler.setRequestsListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Request request = dataSnapshot.getValue(Request.class);
                Log.d(TAG, "onDataChange: " + request.getTitle());

                mGoogleMap.addMarker(new MarkerOptions()
                        .flat(false)
                        .title(request.getTitle())
                        .snippet(request.getDescription())
                        .position(new LatLng(request.getLatitude(),
                                request.getLongitude())));
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_icons_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.action_filter:
                break;
            case R.id.action_change_view:
                if (mRequestsRecyclerView.getVisibility() == View.VISIBLE) {
                    item.setIcon(R.drawable.ic_map_white_24dp);
                    mRequestsRecyclerView.setVisibility(View.INVISIBLE);
                } else {
                    item.setIcon(R.drawable.ic_view_list_white_24dp);
                    mRequestsRecyclerView.setVisibility(View.VISIBLE);
                }
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @OnClick(R.id.fab_create)
    public void createFabAction(View view) {
        Intent createIntent = new Intent(MainActivity.this, CreateActivity.class);

        String transitionName = getString(R.string.fab_to_toolbar);

        ActivityOptions transitionActivityOptions =
                ActivityOptions.makeSceneTransitionAnimation(MainActivity.this, mCreateFab, transitionName);
        startActivity(createIntent, transitionActivityOptions.toBundle());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mAdapter.cleanup();
    }
}