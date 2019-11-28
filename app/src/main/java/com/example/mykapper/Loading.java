package com.example.mykapper;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.location.Location;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.widget.TextView;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.GeoPoint;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.List;
import java.util.Map;

import static java.lang.String.valueOf;


public class Loading extends AppCompatActivity {

    private TextView loading;
    private String DocID;
    private double Rating;
    private GeoPoint Location;
    private double lat1;
    private double lat2;
    private double lon1;
    private double lon2;

    private FusedLocationProviderClient fusedLocationClient;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);

        loading = findViewById(R.id.loading);

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);



        FirebaseFirestore db = FirebaseFirestore.getInstance();

        fusedLocationClient.getLastLocation()
                .addOnSuccessListener(this, new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
                        //todo use location
                        //todo FIX this lat2 = GeoPoint.getLatitude();
                        //todo FiX this lon2 = GeoPoint.getLongitude ();
                    }
                });


        db.collection("Kapsalons")
                .whereGreaterThanOrEqualTo("Rating",4 )
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {




                                Location = document.getGeoPoint("Afstand");
                                Rating = document.getDouble("Rating");
                                DocID = (document.getId());


                                //todo FIX this lat1 = GeoPoint.getLatitude();
                                //todo FIX this lon1 = GeoPoint.getLongitude ();

                                Location loc1 = new Location("");
                                loc1.setLatitude(lat1);
                                loc1.setLongitude(lon1);

                                Location loc2 = new Location("");
                                loc2.setLatitude(lat2);
                                loc2.setLongitude(lon2);

                                float distanceInMeters = loc1.distanceTo(loc2);
                                loading.setText(distanceInMeters + "Meter");

                            }
                        }
                    }



                });
    }

    }

