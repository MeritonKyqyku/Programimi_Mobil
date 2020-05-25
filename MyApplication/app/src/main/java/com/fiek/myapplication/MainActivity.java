package com.fiek.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    EditText Signup_name,Signup_username,Signup_email,Signup_password,Retypepassword,Lusername,Lpassword;
    Button Signup,Login;
    DatabaseReference reff;
    DatabaseReference reff1,reff2,reff3,reff4;
    User user;
    long MaxId=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }
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
//
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
    public void getHome(){
        Intent intent = new Intent(this,Home.class);
        startActivity(intent);
    }
    public void clickToLogin(View view) {
        Lusername = (EditText) findViewById(R.id.Login_username);
        final String Useri = Lusername.getText().toString().trim();
        Lpassword = (EditText) findViewById(R.id.login_password);
        final String passi = Lpassword.getText().toString().trim();
        Login = (Button) findViewById(R.id.btnLogin);
        reff2 = FirebaseDatabase.getInstance().getReference("User");
        reff2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if ((dataSnapshot.exists())) {
                    // Toast.makeText(getApplicationContext(),"qetu",Toast.LENGTH_LONG).show()
                    for (DataSnapshot messageSnapshot : dataSnapshot.getChildren()) {
                       String UsernameE=(String) messageSnapshot.child("username").getValue();
                       String message = (String) messageSnapshot.child("password").getValue();
                       if (Useri.equals(UsernameE)){
                           if (passi.equals(message)){
                               getHome();
                           }
                           else if (!passi.equals(message)){
                               Toast.makeText(getApplicationContext(),"Username or password is wrong",Toast.LENGTH_SHORT).show();
                           }
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
