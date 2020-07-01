package com.fiek.myapplication;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    EditText Signup_name,Signup_username,Signup_email,Signup_password,Retypepassword,Lusername,Lpassword;
    public static final String MyPREFERENCES = "MyPrefs" ;
    public static final String Name = "nameKey";
    public static final String Pass = "passKey";
    SharedPreferences sharedPreferences;
    Button Signup,Login;
    DatabaseReference reff;
    DatabaseReference reff2;
    User user;
    long MaxId=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SharedPreferences saved_values = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        String username=saved_values.getString("nameKey","");
        String password=saved_values.getString("passKey","");
        ((EditText) findViewById(R.id.Login_username)).setText(username);
        ((EditText) findViewById(R.id.login_password)).setText(password);
        //clickToLogin(findViewById(R.id.btnLogin));

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
                } else if (repassword.isEmpty()) {
                    Retypepassword.setError("Please confirm your password...");
                    Signup_name.requestFocus();
                } else if (!password.equals(repassword)) {
                    Toast.makeText(getApplicationContext(), "Password don't match", Toast.LENGTH_LONG).show();
                } else if (!(name.isEmpty() || username.isEmpty() || email.isEmpty() || password.isEmpty() || repassword.isEmpty() )) {
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
                                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                                startActivity(intent);
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
    public void clickToLogin(final View view) {
        Lusername = (EditText) findViewById(R.id.Login_username);
        final String Useri = Lusername.getText().toString().trim();
        Lpassword = (EditText) findViewById(R.id.login_password);
        final String passi = Lpassword.getText().toString().trim();
        Login = (Button) findViewById(R.id.btnLogin);
        reff2 = FirebaseDatabase.getInstance().getReference("User");
        sharedPreferences=getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        reff2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if ((dataSnapshot.exists())) {
                    // Toast.makeText(getApplicationContext(),"qetu",Toast.LENGTH_LONG).show()
                    for (DataSnapshot messageSnapshot : dataSnapshot.getChildren()) {
                       final String UsernameE=(String) messageSnapshot.child("username").getValue();
                       final String message = (String) messageSnapshot.child("password").getValue();
                       if (Useri.equals(UsernameE)){
                           if (passi.equals(message)){
                               Snackbar snackbar = Snackbar
                                       .make(view, "Save login info", Snackbar.LENGTH_LONG)
                                       .setAction("Save", new View.OnClickListener() {
                                           @Override
                                           public void onClick(View view) {
                                               SharedPreferences.Editor editor = sharedPreferences.edit();
                                               editor.putString(Name, UsernameE);
                                               editor.putString(Pass, message);
                                               editor.apply();
                                               Snackbar snackbar1 = Snackbar.make(view, "info is saved", Snackbar.LENGTH_LONG);
                                               snackbar1.show();
                                           }
                                       });
                               snackbar.show();
                               new Handler().postDelayed(new Runnable() {
                                   @Override
                                   public void run() {
                                       Intent intent = new Intent(getApplicationContext(),Home.class);
                                       intent.putExtra("id",UsernameE);
                                       startActivity(intent);

                                   }

                               }, 3000);


                           }
                           else if (!passi.equals(message)){
                               Toast.makeText(getApplicationContext(),"Password is wrong",Toast.LENGTH_SHORT).show();
                           }
                       }

                    }
                }
                else if (Useri.isEmpty()){
                    Toast.makeText(getApplicationContext(),"Jepni te dhena",Toast.LENGTH_SHORT).show();

                }else{
                    Toast.makeText(getApplicationContext(),"Username or password is wrong",Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }

        });



    }

}
