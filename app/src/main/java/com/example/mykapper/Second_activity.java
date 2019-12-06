package com.example.mykapper;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.location.Location;
import android.os.Bundle;

import android.widget.AdapterView;
import android.widget.ListView;

import android.view.View;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import static com.example.mykapper.MainActivity.Rating;
import static com.example.mykapper.MainActivity.Newpage;
import static com.example.mykapper.MainActivity.imagesList;


public class Second_activity extends AppCompatActivity {

    private TextView loading;
    private String DocID;
    private double Docdouble;
    private Integer DocPic;
    private static GeoPoint Geolocation;
    private static double lat2;
    private static double lon2;
    private Location loc1 = new Location("");
    private Location loc2 = new Location("");
    private double RatingDB;
    private volatile boolean complete = false;
    public float distanceInKM;
    private FusedLocationProviderClient fusedLocationClient;
    private StorageReference mStorageRef;

    final ArrayList<String> maintitle = new ArrayList<String>();
    final ArrayList<String> subtitle = new ArrayList<String>();
    final ArrayList<Integer> imgid = new ArrayList<Integer>();
    final TreeMap<Float, Integer> DocIDandPIC = new TreeMap<>();
    final TreeMap<Float, String> ListListList = new TreeMap<>();



    ListView list;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_activity);

        mStorageRef = FirebaseStorage.getInstance().getReference();

        Supersetup();


        Toolbar Toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(Toolbar);
    }

    public void Setuplist() {

        MyListAdapter adapter = new MyListAdapter(this, maintitle, subtitle, imgid, DocIDandPIC, ListListList);
        list = findViewById(R.id.list);
        list.setAdapter(adapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                for (int i = 0; i <= position; i = i + 1) {
                    if (position == i) {

                        int KapperID = i;
                        Newpage = "Kapsalon_algemeen";
                        Open_activity();

                    }
                }
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.screen_2_menu, menu);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Kapsalons");
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                Newpage = "MainActivity";
                Open_activity();
                break;
            case R.id.subitem1:
                Newpage = "settings";
                Open_activity();
                break;
            case R.id.subitem2:
                Newpage = "Mijn_Kappr_login";
                Open_activity();
                break;
            case R.id.subitem3:
                Newpage = "Database_Test";
                Open_activity();
                break;
        }
        return super.onOptionsItemSelected(item);
    }


    public void Open_activity() {
        Intent intent = new Intent(this, Functions.class);
        this.startActivity(intent);
    }

    public void Supersetup() {

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
                .whereGreaterThanOrEqualTo("Rating", (int) Rating)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            //loading.setText("");

                            int documentcount = 0;

                            for (QueryDocumentSnapshot document : task.getResult()) {


                                documentcount = (documentcount + 1);

                                Geolocation = document.getGeoPoint("Afstand");
                                RatingDB = document.getDouble("Rating");
                                DocID = (document.getId());
                                Docdouble = document.getDouble("image");
                                DocPic = (int) (Docdouble);

                                lat2 = Geolocation.getLatitude();
                                lon2 = Geolocation.getLongitude();

                                loc2.setLatitude(lat2);
                                loc2.setLongitude(lon2);

                                float distanceInMeters = loc1.distanceTo(loc2);

                                if (distanceInMeters <= 1000) {

                                    distanceInKM = distanceInMeters;
                                } else {
                                    distanceInKM = (distanceInMeters / 1000);
                                }

                                maintitle.add(DocID);
                                String StringInKM = String.format("%.2f", distanceInKM);
                                subtitle.add(StringInKM + " KM");
                                imgid.add(imagesList[DocPic]);


                                ListListList.put(distanceInKM, DocID);

                                DocIDandPIC.put(distanceInKM, imagesList[DocPic]);
                            }


                            Setuplist();
                        }


                    }
                });
    }

    public void onBackPressed() {
        Newpage = "MainActivity";
        Open_activity();
    }
}
