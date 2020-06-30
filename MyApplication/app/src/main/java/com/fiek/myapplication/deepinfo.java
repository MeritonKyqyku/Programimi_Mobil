package com.fiek.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.List;

public class deepinfo extends AppCompatActivity {

    DatabaseReference reff2,reff1;
    final String TAG="deepinfo";
    private List<Upload> mUploads;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deepinfo);
        Bundle d = getIntent().getExtras();
        final String id = d.getString("keyid");
        final String vler = d.getString("keyvalue");
        reff1 = FirebaseDatabase.getInstance().getReference("uploads/"+id+"/"+vler);
        reff1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String F=(String) dataSnapshot.child("imageUrl").getValue();
                 String N=(String) dataSnapshot.child("name").getValue();
                String P=(String) dataSnapshot.child("mDesc").getValue();
                String R= dataSnapshot.child("mRating").getValue().toString();

                Picasso.get().load(F)
                        .fit()
                        .centerCrop()
                        .into((ImageView)findViewById(com.fiek.myapplication.R.id.pamja));
                ((TextView)findViewById(com.fiek.myapplication.R.id.emriivendi)).setText(N);
                ((TextView)findViewById(com.fiek.myapplication.R.id.pershkrimithell)).setText(P);
                ((TextView)findViewById(com.fiek.myapplication.R.id.ratingu)).setText(R);

                }



            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(deepinfo.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });;

    }
}
