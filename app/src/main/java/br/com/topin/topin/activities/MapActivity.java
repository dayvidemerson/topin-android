package br.com.topin.topin.activities;

import android.Manifest;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.android.gms.tasks.OnSuccessListener;

import java.util.List;

import br.com.topin.topin.R;
import br.com.topin.topin.fragments.FeedbackFragment;
import br.com.topin.topin.fragments.MarkerListFragment;
import br.com.topin.topin.fragments.RouteListFragment;
import br.com.topin.topin.fragments.ScheduleListFragment;
import br.com.topin.topin.models.Line;
import br.com.topin.topin.models.Marker;
import br.com.topin.topin.models.Point;
import br.com.topin.topin.util.BottomNavigationViewHelper;

public class MapActivity extends BaseActivity implements OnMapReadyCallback {

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


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_favorite, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_favorite:
                Log.i("#######", "Menu favorito clicado");
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
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
        Line linha = (Line) getIntent().getSerializableExtra(getString(R.string.line_full));
        if(linha != null){
            setLine(linha);
        }
    }

    private void setUpMap() {
        mMapFragment.getMapAsync(this);
    }

    private void setUpBottomNavigation() {
        BottomNavigationView navigation = findViewById(R.id.bottom_navigation_map);

        BottomNavigationViewHelper.disableShiftMode(navigation);
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
                                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(position, 14));
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
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLngBounds.getCenter(), 14));
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
        for (Marker marker: markers) {
            MarkerOptions markerOptions = new MarkerOptions().position(marker.getPosition()).title(marker.getName());
            mMap.addMarker(markerOptions);
        }
    }

    public void setLine(Line line) {
        setTitle(line.getName());
        mMap.clear();
        PolylineOptions polylineOptions = new PolylineOptions();

        polylineOptions.color(Color.RED);
        polylineOptions.width(5);

        MarkerOptions saida = new MarkerOptions().position(line.getPoints().get(0).getPosition()).title("Início da linha "+line.getName().split(" ")[0]);
        saida.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE));
        mMap.addMarker(saida);

        MarkerOptions onibus = new MarkerOptions().position(line.getPoints().get(line.getPoints().size() / 2).getPosition()).title("Ônibus");
        onibus.icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_directions_bus_black_24dp));
        mMap.addMarker(onibus);

        MarkerOptions chegada = new MarkerOptions().position(line.getPoints().get(line.getPoints().size()-1).getPosition()).title("Fim da linha "+line.getName().split(" ")[0]);
        chegada.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE));
        mMap.addMarker(chegada);

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
            Toolbar toolbar = findViewById(R.id.sheet_toolbar);
            TextView toolbarTitle = (TextView) toolbar.findViewById(R.id.toolbar_title);
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    Intent intent = new Intent(MapActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                    break;

                case R.id.navigation_routes:
                    toolbarTitle.setText(R.string.title_lines);
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

                case R.id.navigation_schedule:
                    toolbarTitle.setText(R.string.title_schedules);
                    if (mBottomSheetBehavior.getState() == BottomSheetBehavior.STATE_HIDDEN) {
                        toogleBottomSheet();
                    }

                    if (mBottomSheetBehavior.getState() == BottomSheetBehavior.STATE_COLLAPSED
                            && item.getItemId() == mCurrentBottomSheet) {
                        toogleBottomSheet();
                    } else {
                        mCurrentBottomSheet = item.getItemId();
                        startFragment(new ScheduleListFragment(), R.id.frame_bottom_map);
                    }
                    break;

                case R.id.navigation_points:
                    toolbarTitle.setText(R.string.title_points);
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

                case R.id.navigation_feedback:
                    toolbarTitle.setText(R.string.title_feedback);
                    if (mBottomSheetBehavior.getState() == BottomSheetBehavior.STATE_HIDDEN) {
                        toogleBottomSheet();
                    }

                    if (mBottomSheetBehavior.getState() == BottomSheetBehavior.STATE_COLLAPSED
                            && item.getItemId() == mCurrentBottomSheet) {
                        toogleBottomSheet();
                    } else {
                        mCurrentBottomSheet = item.getItemId();
                        startFragment(new FeedbackFragment(), R.id.frame_bottom_map);
                    }

                    break;

            }
            return true;
        }
    };
}
