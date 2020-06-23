package com.fiek.myapplication;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.Manifest;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.nfc.Tag;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;

import java.io.IOException;
import java.util.List;

public class SearchActivity extends AppCompatActivity implements OnMapReadyCallback, GoogleApiClient.ConnectionCallbacks,GoogleApiClient.OnConnectionFailedListener, LocationListener {
    SupportMapFragment supportMapFragment;
    FusedLocationProviderClient client;
    GoogleMap nMap;
    GoogleApiClient googleApiClient;
    Location lastLocation;
    Marker currentUserLocation;
    final public String Tag =".SearchActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        supportMapFragment= (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.google_map);
        client = LocationServices.getFusedLocationProviderClient(this);
        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)== PackageManager.PERMISSION_GRANTED)
        {
            getCurrentLocation();
        }
        else {
            ActivityCompat.requestPermissions(this,new String[]{
                    Manifest.permission.ACCESS_FINE_LOCATION},44
            );
        }


        final DrawerLayout drawerLayout = findViewById(R.id.layout_of_drawer);

        findViewById(R.id.menu_icon).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(GravityCompat.START);

            }
        });
        findViewById(R.id.addImageView).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setContentView(R.layout.activity_add_image_view);
            }
        });



        NavigationView navigationView = findViewById(R.id.nav_view);

        navigationView.setItemIconTintList(null);


    }


    private void getCurrentLocation() {
        Task<Location> task= client.getLastLocation();
        task.addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(final Location location) {
                if(location != null){
                    supportMapFragment.getMapAsync(new OnMapReadyCallback(){
                        @Override
                        public void onMapReady(final GoogleMap nMap) {
                            LatLng latLng =new LatLng(location.getLatitude(),location.getLongitude());
                            MarkerOptions options = new MarkerOptions().position(latLng).title("You are Here");
                            nMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng,10));
                            nMap.addMarker(options);

                            findViewById(R.id.searchButton).setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    EditText addressField=(EditText) findViewById(R.id.location_search);
                                    String address=addressField.getText().toString();
                                    new AddData().execute(address);
                                     List<Address> addressList = null;
                                    MarkerOptions userMarkerOptions =new MarkerOptions();

                                    if (!TextUtils.isEmpty(address)){
                                        Geocoder geocoder= new Geocoder(getApplicationContext());

                                        try {
                                            addressList=geocoder.getFromLocationName(address,6);
                                            if(addressList != null){
                                                for (int i=0;i<addressList.size();i++){
                                                    Address userAddress=addressList.get(i);
                                                    LatLng latLng =new LatLng(userAddress.getLatitude(),userAddress.getLongitude() );

                                                    userMarkerOptions.position(latLng);
                                                    userMarkerOptions.title(address);
                                                    userMarkerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_MAGENTA));

                                                    MarkerOptions options = new MarkerOptions().position(latLng).title(address);
                                                    nMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng,10));
                                                    nMap.addMarker(userMarkerOptions);

                                                }
                                            }
                                            else {
                                                Toast.makeText(getApplicationContext(),"'Location Not Found",Toast.LENGTH_SHORT).show();
                                            }
                                        } catch (IOException e) {
                                            e.printStackTrace();
                                        }

                                    }
                                    else {
                                        Toast.makeText(getApplicationContext(),"Please write a name",Toast.LENGTH_SHORT).show();
                                    }

                                }
                            });
                            findViewById(R.id.btnhistory).setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Intent intent = new Intent(getApplicationContext(), UsersActivity.class);
                                    startActivity(intent);
                                }
                            });
                        }


                    });
                }
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode==44){
            if(grantResults.length>0 && grantResults[0]==PackageManager.PERMISSION_GRANTED){
                getCurrentLocation();
            }
        }
    }
    public void onClick1(){

    }



    @Override
    public void onLocationChanged(Location location) {

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {


    }
    public class AddData extends AsyncTask<String,Void,String>{
        @Override
        protected String doInBackground(String... strings) {
            ContentValues cv = new ContentValues();
            cv.put(Perdoruesi.Emri,strings[0]);
           String database=strings[0];
            try {
                SQLiteDatabase objDb = new Databaza(SearchActivity.this).getWritableDatabase();
                try
                {
                    long retValue = objDb.insert(Databaza.Perdoruesit,null,cv);

                }
                catch (Exception ex)
                {
                    Log.e("except", ex.getMessage());
                }
                finally {
                    objDb.close();
                }
            }
            catch (Exception e){

                e.printStackTrace();
            }
             return database;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Toast.makeText(getApplicationContext(),String.valueOf(s),Toast.LENGTH_SHORT).show();

        }
    }



    /**
     * customizable toast
     * @param message
     */
    private void toastMessage(String message){
        Toast.makeText(this,message, Toast.LENGTH_SHORT).show();
    }



}
