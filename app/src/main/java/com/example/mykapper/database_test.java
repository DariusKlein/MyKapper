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
import java.util.HashMap;
import java.util.Map;
import java.util.Random;



public class database_test extends AppCompatActivity implements View.OnClickListener {

    String MyTestvariable1;
    String MyTestvariable2;
    String MyTestvariable3;

private Button databasetest;
private TextView databeseout;
public EditText databasein1;
public EditText databasein2;
public EditText databasein3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database_test);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        databasein1 = findViewById(R.id.databasein1);
        databasein2 = findViewById(R.id.databasein2);
        databasein3 = findViewById(R.id.databasein3);
        databasetest = findViewById(R.id.databasetest);
        databeseout = findViewById(R.id.databaseout);
        databasetest.setOnClickListener(this);

    }
    public void openactivityconfirm () {
        Intent intent = new Intent(this, MainActivity.class); //todo comfirm activity
        startActivity(intent);
    }

    @Override
    public void onClick(View v) {




        FirebaseFirestore db = FirebaseFirestore.getInstance();
        FirebaseFirestore db2 = FirebaseFirestore.getInstance();

        databasein1.getText();
        databasein2.getText();
        databasein3.getText();
        MyTestvariable1 = String.valueOf(databasein1.getText());
        MyTestvariable2 = String.valueOf(databasein2.getText());
        MyTestvariable3 = String.valueOf(databasein3.getText());


        Map<String, Object> Test = new HashMap<>();
        Test.put("Name", MyTestvariable1);
        Test.put("Email", MyTestvariable2);
        Test.put("Phone", MyTestvariable3);


        //todo
        //// TODO: 29/10/2019  generate id voor docuements (kapsalon,kapper,klant,uur,minuut,sec) in variable MyNextDocID
        //// TODO: 29/10/2019  hier onder tijdelijke definitie
        Random randomGenerator = new Random();
        final String MyNextDocID = String.valueOf(randomGenerator.nextInt(999999999) + 1);
        //// TODO: 29/10/2019

        DocumentReference docRef = db.collection("Afspraken").document(MyNextDocID);

// Add a new document with a generated ID
        docRef.set(Test);
        //
        //Read
        DocumentReference docRef2 = db.collection("Afspraken").document(MyNextDocID);
        docRef2.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot doc = task.getResult();

                    StringBuilder fields = new StringBuilder("");
                    fields.append("Name: ").append(doc.get("Name"));
                    fields.append("\nEmail: ").append(doc.get("Email"));
                    fields.append("\nPhone: ").append(doc.get("Phone"));
                    databeseout.setText(fields.toString());


                }
            }

        });
}

    }