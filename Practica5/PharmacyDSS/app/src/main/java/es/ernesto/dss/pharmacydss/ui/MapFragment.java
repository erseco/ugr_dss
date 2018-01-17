package es.ernesto.dss.pharmacydss.ui;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import es.ernesto.dss.pharmacydss.R;
import es.ernesto.dss.pharmacydss.controller.ProductsRestDownloader;

public class MapFragment extends Fragment {

    MapView mMapView;
    private GoogleMap googleMap;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_map, container, false);

        MainActivity.cart.products.clear();
        MainActivity.cart.total = 0;

        mMapView = (MapView) rootView.findViewById(R.id.mapView);
        mMapView.onCreate(savedInstanceState);

        mMapView.onResume(); // needed to get the map to display immediately

        try {
            MapsInitializer.initialize(getActivity().getApplicationContext());
        } catch (Exception e) {
            e.printStackTrace();
        }

        mMapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap mMap) {
                googleMap = mMap;

                // For showing a move to my location button
                if ((ActivityCompat.checkSelfPermission(MainActivity.instance, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) && (ActivityCompat.checkSelfPermission(MainActivity.context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED)) {

                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                         int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    //return;
                }
                //googleMap.setMyLocationEnabled(true);

                // For dropping a marker at a point on the Map
                //LatLng sydney = new LatLng(-34, 151);
                //googleMap.addMarker(new MarkerOptions().position(sydney).title("Marker Title").snippet("Marker Description"));


                mMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
                    @Override
                    public void onInfoWindowClick(Marker marker) {

                        MainActivity.cart.pharmacy = marker.getId();


                        CharSequence text = "Downloading database";
                        int duration = Toast.LENGTH_LONG;

                        Toast toast = Toast.makeText(MainActivity.context, text, duration);
                        toast.show();


                        new ProductsRestDownloader().execute();


                        Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {

                                // Create fragment and give it an argument specifying the article it should show
                                ProductsFragment newFragment = new ProductsFragment();
                                Bundle args = new Bundle();

                                newFragment.setArguments(args);

                                FragmentTransaction transaction = MainActivity.instance.getSupportFragmentManager().beginTransaction();

                                // Replace whatever is in the fragment_container view with this fragment,
                                // and add the transaction to the back stack so the user can navigate back
                                transaction.replace(R.id.fragment_container, newFragment);
                                transaction.addToBackStack(null);

                                // Commit the transaction
                                transaction.commit();
                            }
                        }, 2000);


                        // Determine what marker is clicked by using the argument passed in
                        // for example, marker.getTitle() or marker.getSnippet().
                        // Code here for navigating to fragment activity.
                    }
                });
                // Add pharmacy markers

                LatLng center = new LatLng(37.1970976248000444, -3.624563798608392);

                mMap.addMarker(new MarkerOptions().position(new LatLng(37.188533879320474, -3.624520883264154  )).title("Farmacia 1"));
                mMap.addMarker(new MarkerOptions().position(new LatLng(37.1955422062948, -3.615251168908685    )).title("Farmacia 2"));
                mMap.addMarker(new MarkerOptions().position(new LatLng(37.20128513072, -3.6193066689392026     )).title("Farmacia 3"));
                mMap.addMarker(new MarkerOptions().position(new LatLng(37.19957597268761, -3.6283188912292417  )).title("Farmacia 4"));
                mMap.addMarker(new MarkerOptions().position(new LatLng(37.19335431064119, -3.627632245721429   )).title("Farmacia 5"));
                mMap.addMarker(new MarkerOptions().position(new LatLng(37.188533879320474, -3.625894174279779  )).title("Farmacia 6"));

                //float zoomLevel = 15.0f; //This goes up to 21

                //mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(center, zoomLevel));





                // For zooming automatically to the location of the marker
                CameraPosition cameraPosition = new CameraPosition.Builder().target(center).zoom(15).build();
                googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
            }
        });

        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        mMapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mMapView.onPause();
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