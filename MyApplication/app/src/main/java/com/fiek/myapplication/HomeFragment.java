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
        getActivity().findViewById(R.id.foto1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().setContentView(R.layout.restaurant_marashi);
            }
        });
        getActivity().findViewById(R.id.foto2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().setContentView(R.layout.gatsbycafe);
            }
        });
        getActivity().findViewById(R.id.foto3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().setContentView(R.layout.theranda);
            }
        });
        getActivity().findViewById(R.id.foto4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().setContentView(R.layout.banka);
            }
        });
        getActivity().findViewById(R.id.foto5).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().setContentView(R.layout.gjimnazi);
            }
        });
        getActivity().findViewById(R.id.foto6).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().setContentView(R.layout.pharmacy);
            }
        });



    }
}
