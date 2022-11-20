package com.cookandroid.suwonpractice3;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.Map;

public class MapFragment extends Fragment implements OnMapReadyCallback {
    GoogleMap gMap;
    MapView mapView;
    CardView mapcardView;
    View rootView;
    TextView mapcardtitle;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_map, container, false);
        FragmentManager fm = getActivity().getSupportFragmentManager();
        ActionBar actionBar = ((StartActivity)getActivity()).getSupportActionBar();
        actionBar.setTitle("지도");

        setHasOptionsMenu(true);

        mapcardView = (CardView) rootView.findViewById(R.id.mapcardview);
        mapcardtitle = (TextView) rootView.findViewById(R.id.mapcardtitle);

        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mapView = (MapView) rootView.findViewById(R.id.map);
        if(mapView != null){
            mapView.onCreate(null);
            mapView.onResume();
            mapView.getMapAsync(this);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        getActivity().invalidateOptionsMenu();
    }


    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menumap, menu);
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){
            case R.id.front:
                gMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(37.214194, 126.978796), 20));
                gMap.addMarker(new MarkerOptions().position(new LatLng(37.214194, 126.978796)).title("수원대 정문"));
                return true;
            case R.id.back:
                gMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(37.206550, 126.980001), 20));
                gMap.addMarker(new MarkerOptions().position(new LatLng(37.206550, 126.980001)).title("수원대 후문"));
                return true;
        }
        return false;
    }


    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        MapsInitializer.initialize(getContext());
        gMap = googleMap;

        googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(37.214194, 126.978796), 20));
        googleMap.getUiSettings().setZoomControlsEnabled(true);
        googleMap.addMarker(new MarkerOptions().position(new LatLng(37.214194, 126.978796)).title("수원대 정문"));
        googleMap.addMarker(new MarkerOptions().position(new LatLng(37.214212, 126.978535)).title("이삭토스트"));
        googleMap.addMarker(new MarkerOptions().position(new LatLng(37.214521, 126.978054)).title("던킨도너츠"));
        googleMap.addMarker(new MarkerOptions().position(new LatLng(37.214394, 126.978029)).title("밥집"));
        googleMap.addMarker(new MarkerOptions().position(new LatLng(37.214055, 126.97718)).title("빽다방"));


        googleMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                mapcardView.setVisibility(View.VISIBLE);
                mapcardtitle.setText(marker.getTitle());
                return false;
            }
        });

        googleMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                mapcardView.setVisibility(View.INVISIBLE);
            }
        });



    }
}
