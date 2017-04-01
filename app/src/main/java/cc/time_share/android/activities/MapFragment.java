package cc.time_share.android.activities;


import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import cc.time_share.android.R;
import cc.time_share.android.location.GPSTracker;
import cc.time_share.android.models.Request;
import cc.time_share.android.server.ServerHandler;

import static android.Manifest.permission.ACCESS_COARSE_LOCATION;
import static android.Manifest.permission.ACCESS_FINE_LOCATION;

/**
 * A simple {@link Fragment} subclass.
 */
public class MapFragment extends Fragment  implements OnMapReadyCallback {
       private static final String TAG = MapFragment.class.getSimpleName();
    private ServerHandler mServerHandler;
    @BindView(R.id.mapView)
    MapView mMapView;
    private GoogleMap mGoogleMap;
    private final int PERMISSION_KEY = 7;
    private final String[] permissions = new String[]{ACCESS_FINE_LOCATION, ACCESS_COARSE_LOCATION};
    private GPSTracker mGPSTracker;
    private HashMap<String, Marker> mMarkers = new HashMap<>();
    public MapFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_map, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        mServerHandler = ServerHandler.getInstance();
        mGPSTracker = new GPSTracker(view.getContext());
        try {
            MapsInitializer.initialize(view.getContext());
        } catch (Exception e) {
            e.printStackTrace();
        }
        mMapView.getMapAsync(this);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(permissions, PERMISSION_KEY);
        }

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.mGoogleMap = googleMap;
        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(
                new LatLng(mGPSTracker.getLatitude(),
                        mGPSTracker.getLongitude()), 18));
        subscribeMapToRequests();
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
    public void onDestroy() {
        super.onDestroy();
        mMapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mMapView.onLowMemory();
    }
}
