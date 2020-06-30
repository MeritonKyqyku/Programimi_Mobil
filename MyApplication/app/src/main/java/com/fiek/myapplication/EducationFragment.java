package com.fiek.myapplication;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class EducationFragment extends Fragment implements imageAdapter.OnItemClickListener {
    private RecyclerView mRecyclerView;
    private imageAdapter mAdapter;
    private ProgressBar mProgressCircle;
    private DatabaseReference mDatabaseRef;
    private List<Upload> mUploads;
    private FirebaseStorage mStorage;
    private ValueEventListener mDBListener;


    public EducationFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_education, container, false);
    }
    @Override
    public void onStart() {
        super.onStart();
        mRecyclerView = getView().findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mProgressCircle = getView().findViewById(R.id.progress_circle);
        mUploads = new ArrayList<>();
        mAdapter = new imageAdapter(getContext(), mUploads);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(this);
        mStorage = FirebaseStorage.getInstance();
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("uploads/Education");
        mDBListener = mDatabaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                mUploads.clear();
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    Upload upload = postSnapshot.getValue(Upload.class);
                    upload.setkey(postSnapshot.getKey());
                    mUploads.add(upload);
                }
                mAdapter.notifyDataSetChanged();
                mProgressCircle.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(getContext(), databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                mProgressCircle.setVisibility(View.INVISIBLE);
            }
        });
    }
    @Override
    public void onItemClick(int position) {
        Upload selectedItem = mUploads.get(position);
        final String selectedKey = selectedItem.getKey();
        StorageReference imageRef = mStorage.getReferenceFromUrl(selectedItem.getImageUrl());
        //    Toast.makeText(getContext(),selectedKey,Toast.LENGTH_LONG).show();
        Intent intent = new Intent(getContext(),deepinfo.class);
        intent.putExtra("keyid","Education");
        intent.putExtra("keyvalue",selectedKey.toString());
        startActivity(intent);

    }
    @Override
    public void onWhatEverClick(int position) {
        Toast.makeText(getContext(), "Whatever click at position: " + position, Toast.LENGTH_SHORT).show();
    }
    @Override
    public void onDeleteClick(int position) {
        Upload selectedItem = mUploads.get(position);
        final String selectedKey = selectedItem.getKey();
        StorageReference imageRef = mStorage.getReferenceFromUrl(selectedItem.getImageUrl());
        imageRef.delete().addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                mDatabaseRef.child(selectedKey).removeValue();
                Toast.makeText(getContext(), "Item deleted", Toast.LENGTH_SHORT).show();
            }
        });
    }

}