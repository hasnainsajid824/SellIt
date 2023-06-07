package com.example.sellit;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsFragment extends Fragment implements OnMapReadyCallback{
    private GoogleMap mMap;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Initialize view
        View view = inflater.inflate(R.layout.fragment_maps, container, false);

        // Initialize map fragment
        SupportMapFragment supportMapFragment = (SupportMapFragment)
                getChildFragmentManager().findFragmentById(R.id.google_map);

        // Async map
        supportMapFragment.getMapAsync((OnMapReadyCallback) this);
        return view;
    }
            @Override
            public void onMapReady(GoogleMap googleMap) {
                // When map is loaded

                mMap = googleMap;

                // Add a marker in Riphah and move the camera
                LatLng Riphah = new LatLng(31.377283, 74.230757);
                mMap.addMarker(new MarkerOptions()
                        .position(Riphah)
                        .title("Riphah International University"));
                mMap.moveCamera(
                        CameraUpdateFactory.newLatLng(Riphah));
                mMap.animateCamera(CameraUpdateFactory.zoomTo(15));
            }

    }

