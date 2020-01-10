package com.example.mykapper;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.Activity;
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


import org.w3c.dom.Document;

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
import static com.example.mykapper.MyListAdapter.rowView;


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
    public float distanceInMeters;
    private FusedLocationProviderClient fusedLocationClient;
    private StorageReference mStorageRef;

    static final ArrayList<String> maintitle = new ArrayList<String>();
    final ArrayList<String> subtitle = new ArrayList<String>();
    final ArrayList<Integer> imgid = new ArrayList<Integer>();
    final TreeMap<Float, Integer> DocIDandPIC = new TreeMap<>();
    final TreeMap<Float, String> ListListList = new TreeMap<>();

    static int KapperID;
    float i = 0;
    static boolean GPS = false;

    static MyListAdapter adapter;


    ListView list;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_activity);

        list = null;

        mStorageRef = FirebaseStorage.getInstance().getReference();

        Supersetup();


        Toolbar Toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(Toolbar);
    }

    public void Setuplist() {

        rowView = null;
        list = null;


        adapter = new MyListAdapter(this, maintitle, subtitle, imgid, DocIDandPIC, ListListList);
        list = findViewById(R.id.list);
        list.setAdapter(adapter);


        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                for (int i = 0; i <= position; i = i + 1) {
                    if (position == i) {

                        KapperID = i;

                        Intent Kapsalon_algemeen = new Intent(getBaseContext(), Kapsalon_algemeen.class);
                        Open_activity(Kapsalon_algemeen);
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
                Intent intent = new Intent(this, MainActivity.class);
                Open_activity(intent);
                break;
            case R.id.subitem1:
                Intent SettingsActivity = new Intent(this, SettingsActivity.class);
                Open_activity(SettingsActivity);
                break;
            case R.id.subitem2:
                Intent Mijn_Kappr_login = new Intent(this, Mijn_Kappr_login.class);
                Open_activity(Mijn_Kappr_login);
                break;
            case R.id.subitem3:
                Intent database_test = new Intent(this, database_test.class);
                Open_activity(database_test);
                break;
        }
        return super.onOptionsItemSelected(item);
    }


    public void Open_activity(Intent intent) {
        adapter.clear();
        this.startActivity(intent);
    }
    @Override
    public void onBackPressed() {
        adapter.clear();
        super.onBackPressed();
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
                            ListListList.clear();
                            DocIDandPIC.clear();

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

                                if ( loc1 != null) {

                                    distanceInMeters = loc1.distanceTo(loc2);

                                    distanceInKM = (distanceInMeters / 1000);

                                }else{
                                    distanceInKM = 0;
                                }

                                maintitle.add(DocID);
                                String StringInKM = String.format("%.2f", distanceInKM);
                                subtitle.add(StringInKM + " KM");
                                imgid.add(imagesList[DocPic]);

                                if (distanceInKM > 0) {
                                    ListListList.put(distanceInKM, DocID);
                                    DocIDandPIC.put(distanceInKM, imagesList[DocPic]);
                                    GPS = true;
                                }else{
                                    i = i +1;
                                    ListListList.put(i, DocID);
                                    DocIDandPIC.put(i, imagesList[DocPic]);
                                    GPS = false;
                                }
                            }

                            i = 0;
                            Setuplist();
                        }


                    }
                });
    }


}
