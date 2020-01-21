package com.example.mykapper;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.text.Layout;
import android.util.Patterns;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;


import java.lang.ref.Reference;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.example.mykapper.Second_activity.Gekozen_kapper;
import static com.example.mykapper.reserveren.Afspraak_datum;
import static com.example.mykapper.reserveren.Tijd_Afspraak;


public class Reserveren_final extends AppCompatActivity implements View.OnClickListener {


    String NaamSTR;
    String EmailSTR;
    String Email_local;
    String Behandeling_STR;
    String Uitleg_behandeling;

    private Button Reserveren;
    private TextView databeseout;

    public EditText Naam;
    public EditText Email;

    public TextView Naam_text;
    public TextView Email_text;
    public TextView Kapper;
    public TextView Wijzigen;
    public TextView Bevestigen;
    public TextView Datum;
    public TextView Tijd;
    public TextView Behandeling_Text;
    public Switch Kind;
    public Switch lang_haar;
    public Switch kort_haar;
    private Spinner Behandeling;

    List<String> Behandelingen_list = new ArrayList<String>();
    List<String> Uitleg_list = new ArrayList<String>();

    public Switch haar_lengte;

    public ConstraintLayout.LayoutParams params;
    public ConstraintLayout.LayoutParams params2;
    public ConstraintLayout.LayoutParams params3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reserveren_final);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Naam = findViewById(R.id.Naam);
        Email = findViewById(R.id.Email);

        Datum = findViewById(R.id.Datum);
        Tijd = findViewById(R.id.Tijd);

        Datum.setText(Afspraak_datum);
        Tijd.setText(Tijd_Afspraak);

        Naam_text = findViewById(R.id.Naam_text);
        Email_text = findViewById(R.id.Email_text);

        Reserveren = findViewById(R.id.Reserveren);
        databeseout = findViewById(R.id.databaseout);

        Reserveren.setOnClickListener(this);

        Kapper = findViewById(R.id.Kapper);
        Kapper.setText(Gekozen_kapper);

        Wijzigen = findViewById(R.id.Wijzigen);
        Bevestigen = findViewById(R.id.Bevestigen);

        Kind = findViewById(R.id.Kind);
        kort_haar = findViewById(R.id.kort_haar);
        lang_haar = findViewById(R.id.lang_haar);
        haar_lengte =  findViewById(R.id.kort_haar);

        params = (ConstraintLayout.LayoutParams)kort_haar.getLayoutParams();
        params2 = (ConstraintLayout.LayoutParams)lang_haar.getLayoutParams();
        params3 = (ConstraintLayout.LayoutParams)Kind.getLayoutParams();

        Kind.setOnClickListener(this);
        kort_haar.setOnClickListener(this);
        lang_haar.setOnClickListener(this);

        Behandeling_Text = findViewById(R.id.Behandeling_Text);

        Wijzigen.setOnClickListener(this);
        Bevestigen.setOnClickListener(this);

        Behandeling = findViewById(R.id.Behandeling);
        setspinner();
    }
    public void moveswitches() {

        if (Kind.isChecked()) {
            params.topMargin = 53;
        } else if (kort_haar.isChecked()) {
            params2.topMargin = 53;
        } else if (lang_haar.isChecked()) {
            params3.topMargin = 53;
        } else {
            params.topMargin = 53;
            params2.topMargin = 159;
            params3.topMargin = 265;
        }

        Kind.setLayoutParams(params);
        kort_haar.setLayoutParams(params2);
        lang_haar.setLayoutParams(params3);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.Kind:

                moveswitches();

                haar_lengte = Kind;
                Visibility(kort_haar);
                Visibility(lang_haar);

                break;
            case R.id.kort_haar:

                moveswitches();

                haar_lengte = kort_haar;
                Visibility(lang_haar);
                Visibility(Kind);

                break;
            case R.id.lang_haar:

                moveswitches();

                haar_lengte = lang_haar;
                Visibility(kort_haar);
                Visibility(Kind);

                break;
            case R.id.Wijzigen:

                databeseout.setText("");
                Visibility(databeseout);
                Visibility(Naam);
                Visibility(Email);
                Visibility(Reserveren);
                Visibility(Naam_text);
                Visibility(Email_text);
                Visibility(Kapper);
                Visibility(Wijzigen);
                Visibility(Bevestigen);
                Visibility(Datum);
                Visibility(Tijd);
                Visibility(haar_lengte);
                Visibility(Behandeling_Text);
                Visibility(Behandeling);



                break;
            case R.id.Bevestigen:
                Intent Main = new Intent(this, MainActivity.class);
                Open_activity(Main);
                break;
            case R.id.Reserveren:

                Email_local = (String.valueOf(Email.getText()));

                if (Naam.length() < 4) {
                    Naam.setError("Naam is te kort");
                }else if (Email.getText() == null){
                    Email.setError("Email niet ingevuld");
                }else if (!Patterns.EMAIL_ADDRESS.matcher(Email_local).matches()){
                    Email.setError("e-mailadres niet geldig");
                }else if (haar_lengte.isChecked() == false){
                    haar_lengte.setError("Kies een haarlengte");
                }else {

                    FirebaseFirestore db = FirebaseFirestore.getInstance();


                    NaamSTR = String.valueOf(Naam.getText());
                    EmailSTR = String.valueOf(Email.getText());
                    String HaarLengte = String.valueOf(haar_lengte.getText());


                    Map<String, Object> Afspraak = new HashMap<>();
                    Afspraak.put("Name", NaamSTR);
                    Afspraak.put("Email", EmailSTR);
                    Afspraak.put("Datum", Afspraak_datum);
                    Afspraak.put("Tijd", Tijd_Afspraak);
                    Afspraak.put("Kapper", Gekozen_kapper);
                    Afspraak.put("Haar lengte", HaarLengte);
                    Afspraak.put("Behandeling",Behandeling_STR);


                    final String MyNextDocID = (EmailSTR + (Calendar.getInstance().get(Calendar.HOUR_OF_DAY)) + ":" + (Calendar.getInstance().get(Calendar.MINUTE)));

                    DocumentReference docRef = db.collection("Afspraken").document(MyNextDocID);
                    docRef.set(Afspraak);
                    DocumentReference docRef3 = db.collection("Kapsalons").document(Gekozen_kapper).collection("Afspraken").document(MyNextDocID);
                    docRef3.set(Afspraak);

                    DocumentReference docRef2 = db.collection("Afspraken").document(MyNextDocID);
                    docRef2.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                            if (task.isSuccessful()) {
                                DocumentSnapshot doc = task.getResult();

                                StringBuilder fields = new StringBuilder("");

                                fields.append("Name: ").append(doc.get("Name"));
                                fields.append("\n").append(doc.get("Email"));
                                fields.append("\nDatum: ").append(doc.get("Datum"));
                                fields.append("\nTijd: ").append(doc.get("Tijd"));
                                fields.append("\nKapper: ").append(doc.get("Kapper"));
                                fields.append("\nHaar lengte: ").append(doc.get("Haar lengte"));
                                fields.append("\nBehandeling: ").append(doc.get("Behandeling"));

                                databeseout.setText(fields.toString());
                                Visibility(databeseout);
                                Visibility(Naam);
                                Visibility(Email);
                                Visibility(Reserveren);
                                Visibility(Naam_text);
                                Visibility(Email_text);
                                Visibility(Kapper);
                                Visibility(Wijzigen);
                                Visibility(Bevestigen);
                                Visibility(Datum);
                                Visibility(Tijd);
                                Visibility(haar_lengte);
                                Visibility(Behandeling_Text);
                                Visibility(Behandeling);


                            }
                        }

                    });
                }
                break;


        }

    }
    public void Visibility(View v){

        if (v.getVisibility() == View.VISIBLE) {
            v.setVisibility(View.GONE);
        }else{
            v.setVisibility(View.VISIBLE);
        }
    }
    public void Open_activity(Intent intent) {
        this.startActivity(intent);
    }
    public void setspinner(){

        FirebaseFirestore db = FirebaseFirestore.getInstance();


        db.collection("Kapsalons").document(Gekozen_kapper).collection("Behandelingen")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {

                            Behandelingen_list.clear();
                            for (QueryDocumentSnapshot document : task.getResult()) {

                                String Behandelingen = document.getId();
                                Behandelingen_list.add(Behandelingen);
                                Uitleg_list.add(document.getString("Uitleg"));


                            }
                            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_spinner_item, Behandelingen_list);
                            dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            Behandeling.setAdapter(dataAdapter);
                        }


                    }

                });

        Behandeling.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Behandeling_STR = parent.getItemAtPosition(position).toString();
                Uitleg_behandeling = String.valueOf(Uitleg_list.get(position));
                Behandeling_Text.setText(Uitleg_behandeling);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
}