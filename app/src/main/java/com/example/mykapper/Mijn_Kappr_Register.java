package com.example.mykapper;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import androidx.appcompat.widget.Toolbar;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;


import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class Mijn_Kappr_Register extends AppCompatActivity implements View.OnClickListener {

    static boolean privacy_boolean;
    static String Naam;
    static String Email;
    static String Wachtwoord;

    private Button Register;
    private EditText Naam_input;
    private EditText Email_input;
    private EditText Wachtwoord_input;
    private CheckBox privacy;
    private FirebaseAuth mAuth;
    private String verify;
    private ProgressDialog message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mijn__kappr_register);
        Toolbar Toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(Toolbar);

        Register = findViewById(R.id.Register);
        Naam_input = findViewById(R.id.Naam_input);
        Email_input = findViewById(R.id.Email_input);
        Wachtwoord_input = findViewById(R.id.Wachtwoord_input);
        privacy = (CheckBox)findViewById(R.id.privicy);
        Register.setOnClickListener(this);
        privacy.setOnClickListener(this);

        message = new ProgressDialog(this);
        mAuth = FirebaseAuth.getInstance();

    }


    @Override
    public boolean onCreateOptionsMenu (Menu menu){
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
                OpenMain();
                break;
            case R.id.item3:

                break;
            case R.id.subitem1:

                OpenSettings();

                break;
            case R.id.subitem2:

                OpenMijn_kappr_login();

                break;
            case R.id.subitem3:

                OpenDatabase_test();

                break;
        }
        return super.onOptionsItemSelected(item);
    }
    public void OpenMijnkappr() {
        Intent intent = new Intent(this, MijnKappr.class);
        startActivity(intent);

    }
    public void OpenMain() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);

    }

    public void OpenMijn_kappr_login() {
        Intent intent = new Intent(this, Mijn_Kappr_login.class);
        startActivity(intent);

    }

    public void OpenSettings() {
        Intent intent = new Intent(this, SettingsActivity.class);
        startActivity(intent);

    }

    public void OpenDatabase_test() {
        Intent intent = new Intent(this, database_test.class);
        startActivity(intent);

    }


    @Override
    public void onClick(View v)
    {
        Naam = String.valueOf(Naam_input.getText());
        Email = String.valueOf(Email_input.getText());
        Wachtwoord = String.valueOf(Wachtwoord_input.getText());

        switch (v.getId()) {
            case R.id.Register:
                userRegister();


                break;
            case R.id.privicy:
                if(privacy.isChecked())
                    privacy_boolean = true;
                else
                    privacy_boolean = false;
                break;

        }
    }



    //todo
    //todo
    //todo



    private void userRegister(){

        if(TextUtils.isEmpty(Email)){
            Toast.makeText(this, "Please enter email", Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(Wachtwoord)){
            Toast.makeText(this, "Please enter password", Toast.LENGTH_SHORT).show();
            return;
        }
        Toast.makeText(this, Email, Toast.LENGTH_SHORT).show();

        //todo verify

        verify = Wachtwoord;

        if(TextUtils.equals(Wachtwoord, verify)){
            message.setMessage("Registering User...");
            message.show();
            mAuth.createUserWithEmailAndPassword(Email, Wachtwoord)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(Mijn_Kappr_Register.this, "Successful Registration", Toast.LENGTH_SHORT).show();
                                message.hide();
                                add_user_database();
                            }
                            if(!task.isSuccessful()){
                                Toast.makeText(Mijn_Kappr_Register.this, "Failed Registration", Toast.LENGTH_SHORT).show();
                                message.hide();
                                return;
                            }
                        }
                    });
        }

        else {
            Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show();
            return;
        }

    }
public void add_user_database(){

    FirebaseFirestore db = FirebaseFirestore.getInstance();

    Map<String, Object> Test = new HashMap<>();
        Test.put("Name", Naam);
        Test.put("Email", Email);


    //todo
    //// TODO: 29/10/2019  generate id voor docuements (kapsalon,kapper,klant,uur,minuut,sec) in variable MyNextDocID
    //// TODO: 29/10/2019  hier onder tijdelijke definitie
    Random randomGenerator = new Random();
    final String MyNextDocID = String.valueOf(randomGenerator.nextInt(999999999) + 1);
    //// TODO: 29/10/2019

    DocumentReference docRef = db.collection("Users").document(MyNextDocID);

// Add a new document with a generated ID
        docRef.set(Test);
    //
    //Read
    DocumentReference docRef2 = db.collection("Users").document(MyNextDocID);
        docRef2.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
        @Override
        public void onComplete(@NonNull Task<DocumentSnapshot> task) {
            if (task.isSuccessful()) {
                DocumentSnapshot doc = task.getResult();
                OpenMijn_kappr_login();


            }
        }

    });
}

}
