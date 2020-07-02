package com.fiek.myapplication;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.fragment.app.Fragment;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {
    private ImageView img1;



LinearLayout locations;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_home, container, false);

        locations = v.findViewById(R.id.locations);

        return v;

    }

    @Override
    public void onStart() {
        super.onStart();
        getActivity().findViewById(R.id.resIcon).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
        getActivity().findViewById(R.id.cafIcon).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().setContentView(R.layout.fragment_cafes);
            }
        });
        getActivity().findViewById(R.id.eduIcon).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().setContentView(R.layout.fragment_education);
            }
        });
        getActivity().findViewById(R.id.hotIcon).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().setContentView(R.layout.fragment_hotels);
            }
        });
        getActivity().findViewById(R.id.phaIcon).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().setContentView(R.layout.fragment_pharmacies);
            }
        });
        getActivity().findViewById(R.id.banIcon).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().setContentView(R.layout.fragment_banks);
            }
        });


    }
}
