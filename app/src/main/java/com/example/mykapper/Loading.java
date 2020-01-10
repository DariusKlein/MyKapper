package com.example.mykapper;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.GeoPoint;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import static com.example.mykapper.MainActivity.Newpage;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class Loading extends AppCompatActivity {

    ListView list;

    private TextView loading;
    private String DocID;
    private String DocPic;
    private double Rating;
    private static GeoPoint Geolocation;
    private static double lat2;
    private static double lon2;
    private Location loc1 = new Location("");
    private Location loc2 = new Location("");



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
                        loc1 = location;
                    }
                });


        db.collection("Kapsalons")
                .whereGreaterThanOrEqualTo("Rating",0 )
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            loading.setText("");

                            int documentcount = 0;

                            for (QueryDocumentSnapshot document : task.getResult()) {


                                documentcount = (documentcount + 1);

                                Geolocation = document.getGeoPoint("Afstand");
                                Rating = document.getDouble("Rating");
                                DocID = (document.getId());
                                DocPic = document.getString("image");

                                lat2 = Geolocation.getLatitude();
                                lon2 = Geolocation.getLongitude();

                                loc2.setLatitude(lat2);
                                loc2.setLongitude(lon2);

                                float distanceInMeters = loc1.distanceTo(loc2);

                                DecimalFormat df = new DecimalFormat();
                                df.setMaximumFractionDigits(2);
                                float distanceInKM = (distanceInMeters / 1000);
                                loading.append(distanceInKM + " KM ");



                                ArrayList<String> maintitle = new ArrayList<String>();
                                maintitle.add(DocID);
                                ArrayList<String> subtitle = new ArrayList<String>();
                                subtitle.add(distanceInKM + "KM");
                                ArrayList<String> imgid = new ArrayList<String>();
                                imgid.add(DocPic);

                            }
                        }
                    }

                });
    }

    }

