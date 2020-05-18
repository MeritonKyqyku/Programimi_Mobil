package com.fiek.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.View;
import com.fiek.myapplication.RegistrationUser;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Map;

public class MainActivity extends AppCompatActivity {

    EditText Signup_name,Signup_username,Signup_email,Signup_password,Retypepassword;
    Button Signup;
    DatabaseReference reff;
    User user;
    long MaxId=0;
    public void clickToRegister(View view) {
        setContentView(R.layout.activity_registration);
        Signup_name = (EditText) findViewById(R.id.Signup_Name);
        Signup_username = (EditText) findViewById(R.id.Signup_username);
        Signup_email = (EditText) findViewById(R.id.Signup_username);
        Signup_password = (EditText) findViewById(R.id.Signup_password);
        Retypepassword = (EditText) findViewById(R.id.Retypepassword);
        Signup = (Button) findViewById(R.id.btnRegister);
        user = new User();
        reff = FirebaseDatabase.getInstance().getReference("User");
        reff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists())
                    MaxId = (dataSnapshot.getChildrenCount());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        Signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = Signup_name.getText().toString().trim();
                String username = Signup_username.getText().toString().trim();
                String email = Signup_email.getText().toString().trim();
                String password = Signup_password.getText().toString().trim();
                String repassword = Retypepassword.getText().toString().trim();
//                if (Signup_password.getText() != Retypepassword.getText()){
//                    Toast.makeText(getApplicationContext(),"Passwords doesn't match",Toast.LENGTH_SHORT).show();
//                }
//                else {
                if (name.isEmpty()) {
                    Signup_name.setError("name cannot be empty");
                    Signup_name.requestFocus();
                }
                else if (username.isEmpty()) {
                    Signup_username.setError("username cannot be empty");
                    Signup_username.requestFocus();
                }
                else if (email.isEmpty()) {
                    Signup_email.setError("email cannot be empty");
                    Signup_email.requestFocus();
                }
                else if (password.isEmpty()) {
                    Signup_password.setError("Password cannot be empty");
                    Signup_name.requestFocus();
                }
                else if(!(name.isEmpty()&&username.isEmpty()&&email.isEmpty()&&password.isEmpty())){
                user.setName(name);
                user.setUsername(username);
                user.setEmailadd(email);
                user.setPassword(password);
                reff.child(String.valueOf(MaxId + 1)).setValue(user);
                Toast.makeText(getApplicationContext(), "User inserted", Toast.LENGTH_SHORT).show();
               }
                else{
                    Toast.makeText(getApplicationContext(), "Problem found", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }
    public void clickToLogin(View view){
        setContentView(R.layout.feed);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }



}
