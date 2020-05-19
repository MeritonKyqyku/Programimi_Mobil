package com.fiek.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View;
import com.fiek.myapplication.RegistrationUser;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.EventListener;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    EditText Signup_name,Signup_username,Signup_email,Signup_password,Retypepassword,Lusername,Lpassword;
    Button Signup,Login;
    DatabaseReference reff;
    DatabaseReference reff1,reff2,reff3,reff4;
    User user;
    long MaxId=0;
    public void clickToRegister(View view) {
        setContentView(R.layout.activity_registration);
        Signup_name = (EditText) findViewById(R.id.Signup_Name);
        Signup_username = (EditText) findViewById(R.id.Signup_username);
        Signup_email = (EditText) findViewById(R.id.Signup_email);
        Signup_password = (EditText) findViewById(R.id.Signup_password);
        Retypepassword = (EditText) findViewById(R.id.Retypepassword);
        Signup = (Button) findViewById(R.id.btnRegister);
        user = new User();
        Signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String name = Signup_name.getText().toString().trim();
                final String username = Signup_username.getText().toString().trim();
                final String email = Signup_email.getText().toString().trim();
                final String password = Signup_password.getText().toString().trim();
                String repassword = Retypepassword.getText().toString().trim();
                if (name.isEmpty()) {
                    Signup_name.setError("name cannot be empty");
                    Signup_name.requestFocus();
                } else if (username.isEmpty()) {
                    Signup_username.setError("username cannot be empty");
                    Signup_username.requestFocus();
                } else if (email.isEmpty()) {
                    Signup_email.setError("email cannot be empty");
                    Signup_email.requestFocus();
                } else if (password.isEmpty()) {
                    Signup_password.setError("Password cannot be empty");
                    Signup_name.requestFocus();
                } else if (!(name.isEmpty() || username.isEmpty() || email.isEmpty() || password.isEmpty())) {
                    reff = FirebaseDatabase.getInstance().getReference("User").child(Signup_username.getText().toString());

                    reff.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot123) {

                            if(!(dataSnapshot123.exists())){
                                user.setName(name);
                                user.setUsername(username);
                                user.setEmailadd(email);
                                user.setPassword(password);
                                reff.setValue(user);
                                Toast.makeText(getApplicationContext(), "User inserted", Toast.LENGTH_LONG).show();
                            }
//                            else if(!(dataSnapshot123.exists())){
//                                Toast.makeText(getApplicationContext(),"User exists",Toast.LENGTH_LONG).show();
//                            }
                        }



                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {
                            Toast.makeText(getApplicationContext(),"Stringu",Toast.LENGTH_LONG);

                        }
                    });

                }

            }

        });
    }
    public void clickToLogin(View view) {
        Lusername = (EditText) findViewById(R.id.Login_username);
        final String Useri = Lusername.getText().toString().trim();
        Lpassword = (EditText) findViewById(R.id.login_password);
        final String passi = Lpassword.getText().toString().trim();
        Login = (Button) findViewById(R.id.btnLogin);
        reff2 = FirebaseDatabase.getInstance().getReference("User").child(Useri);
     //   Toast.makeText(getApplicationContext(),"qetu",Toast.LENGTH_SHORT).show();
        reff2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if ((dataSnapshot.exists())) {
                   // Toast.makeText(getApplicationContext(),"qetu",Toast.LENGTH_LONG).show();
                    try {
                        reff1 = reff.child(passi);
                        reff2 = reff.child(passi);
                        if(reff1.toString().equals(reff2)) {
                            setContentView(R.layout.feed);
                            Toast.makeText(getApplicationContext(), "Miresevini" + Useri, Toast.LENGTH_LONG).show();
                        }
                        else {
                            Toast.makeText(getApplicationContext(),reff1.toString()+"    "+reff2.toString(),Toast.LENGTH_SHORT).show();
                        }
                    } catch (Exception e) {
                        Toast.makeText(getApplicationContext(), "e.toString()", Toast.LENGTH_LONG).show();
                    }
                }
                else {
                    Toast.makeText(getApplicationContext(),"qetu gabimi",Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }

        });


    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }



}
