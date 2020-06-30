package com.fiek.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Profile extends AppCompatActivity {
    DatabaseReference reff;
    ImageView backbutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        backbutton = findViewById(R.id.back_icon);

        backbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Profile.super.onBackPressed();
            }});

        Bundle c = getIntent().getExtras();
        final String id = c.getString("idja");
        reff = FirebaseDatabase.getInstance().getReference("User");
        reff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if ((dataSnapshot.exists())) {
                    for (DataSnapshot messageSnapshot : dataSnapshot.getChildren()) {
                        String UsernameE = (String) messageSnapshot.child("username").getValue();
                        if (id.equals(UsernameE)){
                            String U=(String) messageSnapshot.child("username").getValue();
                            String P = (String) messageSnapshot.child("password").getValue();
                            String E=(String) messageSnapshot.child("name").getValue();
                            String N = (String) messageSnapshot.child("emailadd").getValue();


                            ((TextView)findViewById(R.id.profileusername)).setText(U);
                            ((TextView)findViewById(R.id.profilename)).setText(N);
                            ((TextView)findViewById(R.id.profilepass)).setText(P);
                            ((TextView)findViewById(R.id.profileemail)).setText(E);
                        }
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
