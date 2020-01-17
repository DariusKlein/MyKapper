package com.example.mykapper;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



public class test2 extends AppCompatActivity {

    public boolean beschikbaar = true;
    public double timeloop;
    public String timestring;
    public String Kapper;
    public String Maand;
    public String Week;
    public String Dag;
    public TextView textview;
    public int testcount = 0;
    List<String> list_kappers = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test2);

        textview = findViewById(R.id.textViewRipdatabase);
         final String[] values = new String[]{"09.00", "09.15", "09.30", "09.45",
                 "10.00", "10.15", "10.30", "10.45",
                 "11.00", "11.15", "11.30", "11.45",
                 "12.00", "12.15", "12.30", "12.45",
                 "13.00", "13.15", "13.30", "13.45",
                 "14.00", "14.15", "14.30", "14.45",
                 "15.00", "15.15", "15.30", "15.45",
                 "16.00", "16.15", "16.30", "16.45",
                 "17.00", "17.15", "17.30", "17.45",
                 "18.00", "18.15", "18.30", "18.45",
         };
         final String[] Maanden = new String[]{"Januari","Febuari","Maart", "April","Mei","Juni","juli","Augustus","September","October","November","December"};
        final String[] Dagen = new String[]{"Maandag","Disndag","Woensdag", "Donderdag","Vrijdag","Zaterdag","Zondag"};


        data(values,Maanden,Dagen,list_kappers);
    }

    public void data(final String[] values,final String[]Maanden,final String[] Dagen,final List list_kappers) {

        FirebaseFirestore db = FirebaseFirestore.getInstance();

        db.collection("Kapsalons")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {


                            for (QueryDocumentSnapshot document : task.getResult()) {


                                String Kapsalons_naam = document.getId();
                                list_kappers.add(Kapsalons_naam);


                            }
                        DB_RIP(values,Maanden,Dagen,list_kappers);
                        }


                    }

                });
    }
    public void DB_RIP(String[] value,String[]Maanden,String[] Dagen,List list_kappers){

        FirebaseFirestore db = FirebaseFirestore.getInstance();

        for(int X = 0; X < list_kappers.size(); X++ ){
            Kapper = String.valueOf(list_kappers.get(X));

            for(int Y = 0; Y < 12; Y++){
                Maand = Maanden[Y];

                for(int P = 0; P < 4; P++){
                    Week = ("Week" + P);

                    for(int L = 0; L < 7; L++){
                        Dag = Dagen[L];

                        for(int i = 0; i < 40; i++) {


                            Map<String, Boolean> time_set = new HashMap<>();
                            time_set.put("Beschikbaar", beschikbaar);


                            db.collection("Kapsalons").document(Kapper).collection("Tijden").document(Maand).collection("Weken").document(Week).collection("Dagen").document(Dag).collection("Tijden").document((value[i]))
                                    .set(time_set)
                                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void aVoid) {
                                            testcount = testcount + 1;
                                            textview.setText(testcount);

                                        }
                                    })
                                    .addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            textview.setText("Fail");
                                        }
                                    });
        }}}}}
    }
}
