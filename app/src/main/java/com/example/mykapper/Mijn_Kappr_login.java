package com.example.mykapper;

import android.content.Intent;
import android.os.Bundle;
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
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import static com.example.mykapper.MainActivity.loggedIn;


public class Mijn_Kappr_login extends AppCompatActivity implements View.OnClickListener {

    static boolean privacy_boolean;
    static String Naam;
    static String Email;
    static String Wachtwoord;

    private Button inloggen;
    private Button Register_pagina;
    private EditText Naam_input;
    private EditText Email_input;
    private EditText Wachtwoord_input;
    private CheckBox privacy;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mijn__kappr_login);
        Toolbar Toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(Toolbar);

        inloggen = findViewById(R.id.inloggen);
        Naam_input = findViewById(R.id.Naam_input);
        Email_input = findViewById(R.id.Email_input);
        Wachtwoord_input = findViewById(R.id.Wachtwoord_input);
        Register_pagina = findViewById(R.id.Register_pagina);
        privacy = (CheckBox)findViewById(R.id.privicy);
        inloggen.setOnClickListener(this);
        privacy.setOnClickListener(this);
        Register_pagina.setOnClickListener(this);

        mAuth = FirebaseAuth.getInstance();

        if (loggedIn == true) {

            Functions.OpenMijnKappr();

        }



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
                Functions.OpenMainActivity();
                break;
            case R.id.item3:

                break;
            case R.id.subitem1:

                Functions.Opensettings();

                break;
            case R.id.subitem2:

                Functions.OpenMijn_Kappr_login();

                break;
            case R.id.subitem3:

                Functions.OpenDatabase_Test();

                break;
        }
        return super.onOptionsItemSelected(item);
    }



    @Override
    public void onClick(View v)
    {
        Naam = String.valueOf(Naam_input.getText());
        Email = String.valueOf(Email_input.getText());
        Wachtwoord = String.valueOf(Wachtwoord_input.getText());

        switch (v.getId()) {
            case R.id.inloggen:

                if (Email.length() < 5 || Wachtwoord.length() < 5)

                //todo niets ingevuld
                    Functions.OpenMijn_Kappr_login();

                else

                signin();


                break;

            case R.id.Register_pagina:

                Intent intent = new Intent(this, Mijn_Kappr_Register.class);
                startActivity(intent);


                break;
            case R.id.privicy:
                if(privacy.isChecked())
                    privacy_boolean = true;
                else
                    privacy_boolean = false;
                break;

        }
    }

    public void signin(){

        mAuth.signInWithEmailAndPassword(Email, Wachtwoord)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(Mijn_Kappr_login.this, "signInWithEmail:success", Toast.LENGTH_SHORT).show();
                            FirebaseUser currentUser = mAuth.getCurrentUser();

                            System.out.print(currentUser);

                            loggedIn = true;

                            Functions.OpenMijnKappr();

                        } else {

                            Toast.makeText(Mijn_Kappr_login.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();

                        }

                    }
                });

    }

}
