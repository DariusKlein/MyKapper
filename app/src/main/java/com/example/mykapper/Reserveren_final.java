package com.example.mykapper;

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

import org.w3c.dom.Text;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import static com.example.mykapper.Second_activity.Gekozen_kapper;
import static com.example.mykapper.reserveren.Afspraak_datum;


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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reserveren_final);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Naam = findViewById(R.id.Naam);
        Email = findViewById(R.id.Email);

        Naam_text = findViewById(R.id.Naam_text);
        Email_text = findViewById(R.id.Email_text);

        Reserveren = findViewById(R.id.Reserveren);
        databeseout = findViewById(R.id.databaseout);

        Reserveren.setOnClickListener(this);

        Kapper = findViewById(R.id.Kapper);
        Kapper.setText(Gekozen_kapper);

    }

    @Override
    public void onClick(View v) {

        FirebaseFirestore db = FirebaseFirestore.getInstance();




        NaamSTR = String.valueOf(Naam.getText());
        EmailSTR = String.valueOf(Email.getText());


        Map<String, Object> Afspraak = new HashMap<>();
        Afspraak.put("Name", NaamSTR);
        Afspraak.put("Email", EmailSTR);
        Afspraak.put("Datum",Afspraak_datum);



        final String MyNextDocID = (EmailSTR +(Calendar.getInstance().get(Calendar.MINUTE)));

        DocumentReference docRef = db.collection("Afspraken").document(MyNextDocID);

        docRef.set(Afspraak);
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


                    databeseout.setText(fields.toString());
                    databeseout.setVisibility(View.VISIBLE);
                    Naam.setVisibility(View.GONE);
                    Email.setVisibility(View.GONE);
                    Reserveren.setVisibility(View.GONE);
                    Naam_text.setVisibility(View.GONE);
                    Email_text.setVisibility(View.GONE);
                    Kapper.setVisibility(View.GONE);


                }
            }

        });
}

}