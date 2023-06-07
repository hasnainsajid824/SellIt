package com.example.sellit;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class AboutFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_about, container, false);
        Fragment fragment=new MapsFragment();
        getChildFragmentManager().beginTransaction()
                .replace(R.id.map_frame,fragment).commit();
        // Inflate the layout for this fragment
        return v;
    }
}