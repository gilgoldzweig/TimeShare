package cc.time_share.android.activities;

import static android.Manifest.permission.*;

import android.app.ActivityOptions;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
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
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.haha.guava.base.Joiner;

import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cc.time_share.android.R;
import cc.time_share.android.adapters.RequestHolder;
import cc.time_share.android.location.GPSTracker;
import cc.time_share.android.models.Request;
import cc.time_share.android.server.ServerHandler;
import cc.time_share.android.utilites.GlobalSharedPreferences;


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
    private HashMap<String, Marker> mMarkers = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        startActivity(new Intent(this, HomeActivity.class));
        mToolBar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(mToolBar);
        mMapFragment = (MapFragment) getFragmentManager()
                .findFragmentById(R.id.mapFragmentView);
        mServerHandler = ServerHandler.getInstance();

        subscribeListToRequests();

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
        subscribeMapToRequests();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    private void subscribeListToRequests() {
        DatabaseReference requestsRef =
                FirebaseDatabase.getInstance().getReference().child("requests");

        mAdapter = new FirebaseRecyclerAdapter<Request, RequestHolder>(
                Request.class, R.layout.item_requests, RequestHolder.class, requestsRef) {
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

        mRequestsRecyclerView.setHasFixedSize(false);
        mRequestsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRequestsRecyclerView.setAdapter(mAdapter);
    }

    private void subscribeMapToRequests() {
        mServerHandler.setRequestsListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Request request = dataSnapshot.getValue(Request.class);
                Log.d(TAG, "onDataChange: " + request.getTitle());
                mMarkers.put(request.getKey(), mGoogleMap.addMarker(new MarkerOptions()
                        .flat(false)
                        .title(request.getTitle())
                        .snippet(request.getDescription())
                        .position(new LatLng(request.getLatitude(),
                                request.getLongitude()))));
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                Request request = dataSnapshot.getValue(Request.class);
                Marker marker = mMarkers.get(request.getKey());
                marker.setTitle(request.getTitle());
                marker.setSnippet(request.getDescription());
                marker.setPosition(new LatLng(request.getLatitude(), request.getLongitude()));
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                Request request = dataSnapshot.getValue(Request.class);
                mMarkers.remove(request.getKey()).remove();
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
                    mRequestsRecyclerView.setVisibility(View.INVISIBLE);
                    item.setIcon(R.drawable.ic_view_list_white_24dp);
                } else {
                    mRequestsRecyclerView.setVisibility(View.VISIBLE);
                    item.setIcon(R.drawable.ic_map_white_24dp);
                }
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @OnClick(R.id.fab_create)
    public void createFabAction(View view) {
//        Intent createIntent = new Intent(MainActivity.this, CreateActivity.class);
//
//        String transitionName = getString(R.string.fab_to_toolbar);
//
//        ActivityOptions transitionActivityOptions =
//                ActivityOptions.makeSceneTransitionAnimation(MainActivity.this, mCreateFab,
//                        transitionName);
//        startActivity(createIntent, transitionActivityOptions.toBundle());
//        if (GlobalSharedPreferences.getInstance().contains("userKey")) {  // Registered user.
//
//        } else {
////            AlertDialog signInDialog = new AlertDialog.Builder(this).
//            // TODO(gil): Show dialog saying "Please update your profile to post a request."
//            // with one button "OK :)". Clicking that button takes the user to ProfileActivity.
//            // Inside that activity, call ServerHandler.getInstance().addUser(user).
//        }
        if (GlobalSharedPreferences.getInstance().contains("userKey")) {  // Registered user.
            Intent createIntent = new Intent(MainActivity.this, CreateActivity.class);

            String transitionName = getString(R.string.fab_to_toolbar);

            ActivityOptions transitionActivityOptions =
                    ActivityOptions.makeSceneTransitionAnimation(MainActivity.this, mCreateFab,
                            transitionName);
            startActivity(createIntent, transitionActivityOptions.toBundle());
        } else {
            new AlertDialog.Builder(this)
                    .setTitle("Update profile")
                    .setMessage("Please update your profile to post a request.")
                    .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            Intent profileIntent = new Intent(MainActivity.this, ProfileActivity.class);
                            startActivity(profileIntent);
                        }
                    })
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mAdapter.cleanup();
    }
}