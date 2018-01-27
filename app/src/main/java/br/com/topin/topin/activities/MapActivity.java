package br.com.topin.topin.activities;

import android.Manifest;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.LinearLayout;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.android.gms.tasks.OnSuccessListener;

import java.util.List;

import br.com.topin.topin.R;
import br.com.topin.topin.fragments.MarkerListFragment;
import br.com.topin.topin.fragments.RouteListFragment;
import br.com.topin.topin.models.Line;
import br.com.topin.topin.models.Marker;
import br.com.topin.topin.models.Point;

public class MapActivity extends AppCompatActivity implements OnMapReadyCallback {

    private static final int LOCATION_REQUEST_CODE = 1;
    private GoogleMap mMap;
    private FusedLocationProviderClient mFusedLocationClient;
    private MapFragment mMapFragment;
    private BottomSheetBehavior mBottomSheetBehavior;

    private Integer mCurrentBottomSheet = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        mMapFragment = new MapFragment();
        startFragment(mMapFragment, R.id.frame_map);
        startFragment(new RouteListFragment(), R.id.frame_bottom_map);
        setUpMap();
        setUpBottomNavigation();
    }

    public void startFragment(Fragment fragment, int i) {
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(i, fragment);
        fragmentTransaction.commit();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        setUpLocation();
    }

    private void setUpMap() {
        mMapFragment.getMapAsync(this);
    }

    private void setUpBottomNavigation() {
        BottomNavigationView navigation = findViewById(R.id.bottom_navigation_map);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        LinearLayout bottomSheetMap = findViewById(R.id.bottom_sheet_map);
        mBottomSheetBehavior = BottomSheetBehavior.from(bottomSheetMap);
        mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
    }

    private void setUpLocation() {
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        moveCameraLastLocation();
    }

    private void moveCameraLastLocation() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, LOCATION_REQUEST_CODE);
        } else {
            mMap.setMyLocationEnabled(true);
            mFusedLocationClient.getLastLocation()
                    .addOnSuccessListener(this, new OnSuccessListener<Location>() {
                        @Override
                        public void onSuccess(Location location) {
                            if (location != null) {
                                LatLng position = new LatLng(location.getLatitude(), location.getLongitude());
                                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(position, 10));
                            }
                        }
                    });
        }
    }

    private void moveCameraLine(Line line) {
        Point first = line.getPoints().get(0);
        Point last = line.getPoints().get(line.getPoints().size() - 1);
        LatLng start;
        LatLng end;
        if (first.getLatitude() < last.getLatitude()) {
            start = new LatLng(first.getLatitude(), first.getLongitude());
            end = new LatLng(last.getLatitude(), last.getLongitude());
        } else {
            start = new LatLng(last.getLatitude(), last.getLongitude());
            end = new LatLng(first.getLatitude(), first.getLongitude());
        }

        LatLngBounds latLngBounds = new LatLngBounds(start, end);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLngBounds.getCenter(), 12));
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case LOCATION_REQUEST_CODE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    moveCameraLastLocation();
                }
                break;
        }
    }

    private void toogleBottomSheet() {
        switch (mBottomSheetBehavior.getState()) {
            case BottomSheetBehavior.STATE_HIDDEN:
                mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                break;
            case BottomSheetBehavior.STATE_COLLAPSED:
                mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
                break;
        }
    }

    public void setMarkers(List<Marker> markers) {
        mMap.clear();
        for (Marker marker: markers) {
            MarkerOptions markerOptions = new MarkerOptions().position(marker.getPosition()).title(marker.getName());
            mMap.addMarker(markerOptions);
        }
    }

    public void setLine(Line line) {
        mMap.clear();
        PolylineOptions polylineOptions = new PolylineOptions();

        for (Point point: line.getPoints()) {
            polylineOptions.add(point.getPosition());
        }

        mMap.addPolyline(polylineOptions);

        moveCameraLine(line);

        mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_routes:
                    if (mBottomSheetBehavior.getState() == BottomSheetBehavior.STATE_HIDDEN) {
                        toogleBottomSheet();
                    }

                    if (mBottomSheetBehavior.getState() == BottomSheetBehavior.STATE_COLLAPSED
                            && item.getItemId() == mCurrentBottomSheet) {
                        toogleBottomSheet();
                    } else {
                        mCurrentBottomSheet = item.getItemId();
                        startFragment(new RouteListFragment(), R.id.frame_bottom_map);
                    }
                    break;
                case R.id.navigation_points:
                    if (mBottomSheetBehavior.getState() == BottomSheetBehavior.STATE_HIDDEN) {
                        toogleBottomSheet();
                    }

                    if (mBottomSheetBehavior.getState() == BottomSheetBehavior.STATE_COLLAPSED
                            && item.getItemId() == mCurrentBottomSheet) {
                        toogleBottomSheet();
                    } else {
                        mCurrentBottomSheet = item.getItemId();
                        startFragment(new MarkerListFragment(), R.id.frame_bottom_map);
                    }
                    break;
            }
            return true;
        }
    };
}
