package com.example.mykapper;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.location.Location;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.List;

import static java.lang.String.valueOf;


public class Loading extends AppCompatActivity {

    private TextView loading;
    private String LocDoc;
    private double lat1;
    private double lat2;
    private double lon1;
    private double lon2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);

        loading = findViewById(R.id.loading);


        FirebaseFirestore db = FirebaseFirestore.getInstance();



        db.collection("Kapsalons")
                .whereGreaterThanOrEqualTo("Rating",4 )
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                LocDoc = (document.getId() + " => " + document.getData());

                                Location loc1 = new Location("");
                                loc1.setLatitude(lat1);
                                loc1.setLongitude(lon1);

                                Location loc2 = new Location("");
                                loc2.setLatitude(lat2);
                                loc2.setLongitude(lon2);

                                float distanceInMeters = loc1.distanceTo(loc2);
                                loading.setText(String.valueOf(distanceInMeters));

                            }
                        }
                    }



                });
    }

    }

