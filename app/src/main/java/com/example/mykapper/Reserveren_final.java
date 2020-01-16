package com.example.mykapper;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;


import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import static com.example.mykapper.Second_activity.Gekozen_kapper;
import static com.example.mykapper.reserveren.Afspraak_datum;
import static com.example.mykapper.reserveren.Tijd_Afspraak;


public class Reserveren_final extends AppCompatActivity implements View.OnClickListener {

    String NaamSTR;
    String EmailSTR;

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

        Wijzigen.setOnClickListener(this);
        Bevestigen.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.Wijzigen:

                databeseout.setText("");
                databeseout.setVisibility(View.GONE);
                Naam.setVisibility(View.VISIBLE);
                Email.setVisibility(View.VISIBLE);
                Reserveren.setVisibility(View.VISIBLE);
                Naam_text.setVisibility(View.VISIBLE);
                Email_text.setVisibility(View.VISIBLE);
                Kapper.setVisibility(View.VISIBLE);
                Wijzigen.setVisibility(View.GONE);
                Bevestigen.setVisibility(View.GONE);
                Datum.setVisibility(View.VISIBLE);
                Tijd.setVisibility(View.VISIBLE);


                break;
            case R.id.Bevestigen:
                Intent Main = new Intent(this, MainActivity.class);
                Open_activity(Main);
                break;
            case R.id.Reserveren:
                FirebaseFirestore db = FirebaseFirestore.getInstance();


                NaamSTR = String.valueOf(Naam.getText());
                EmailSTR = String.valueOf(Email.getText());


                Map<String, Object> Afspraak = new HashMap<>();
                Afspraak.put("Name", NaamSTR);
                Afspraak.put("Email", EmailSTR);
                Afspraak.put("Datum", Afspraak_datum);
                Afspraak.put("Tijd", Tijd_Afspraak);
                Afspraak.put("Kapper", Gekozen_kapper);


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
                            fields.append("\nEmail: ").append(doc.get("Email"));
                            fields.append("\nDatum: ").append(doc.get("Datum"));
                            fields.append("\nTijd: ").append(doc.get("Tijd"));
                            fields.append("\nKapper: ").append(doc.get("Kapper"));

                            databeseout.setText(fields.toString());
                            databeseout.setVisibility(View.VISIBLE);
                            Naam.setVisibility(View.GONE);
                            Email.setVisibility(View.GONE);
                            Reserveren.setVisibility(View.GONE);
                            Naam_text.setVisibility(View.GONE);
                            Email_text.setVisibility(View.GONE);
                            Kapper.setVisibility(View.GONE);
                            Wijzigen.setVisibility(View.VISIBLE);
                            Bevestigen.setVisibility(View.VISIBLE);
                            Datum.setVisibility(View.GONE);
                            Tijd.setVisibility(View.GONE);


                        }
                    }

                });
                break;


        }

    }
    public void Open_activity(Intent intent) {
        this.startActivity(intent);
    }
}