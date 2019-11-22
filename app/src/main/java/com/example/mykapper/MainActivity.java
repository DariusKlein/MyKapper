package com.example.mykapper;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar.OnRatingBarChangeListener;
import android.widget.RatingBar;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class MainActivity extends AppCompatActivity
    implements View.OnClickListener {
    private Button Button;
    private Button SettingButton;
    private Button My_MyKappr;
    private RatingBar StartRating;
    static float Rating;
    private FirebaseAuth mAuth;
    static boolean loggedIn = false;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();

        Button = findViewById(R.id.Zoeken);
        SettingButton = findViewById(R.id.Instellingen);
        My_MyKappr = findViewById(R.id.MyAccount);
        SettingButton.setOnClickListener(this);
        Button.setOnClickListener(this);
        My_MyKappr.setOnClickListener(this);
        }


    @Override
    public void onStart() {
        super.onStart();

        FirebaseUser currentUser = mAuth.getCurrentUser();




        System.out.print(currentUser);


    }


    public void onClick(View view) {
        StartRating = (RatingBar) findViewById(R.id.StartRating);


        Rating = StartRating.getRating();
        System.out.println(Rating);

        switch (view.getId()) {
            case R.id.Instellingen:
                Opensettings ();
                break;
            case R.id.MyAccount:
                OpenMijn_Kappr_login ();
                break;
            case R.id.Zoeken:
                Opensecond_activity();
                break;

        }
    }

        public void Opensecond_activity () {
            Intent intent = new Intent(this, Second_activity.class);
            startActivity(intent);
        }

        public void Opensettings () {
            Intent intent = new Intent(this, SettingsActivity.class);
            startActivity(intent);
        }
        public void OpenMijn_Kappr_login () {
            Intent intent = new Intent(this, Mijn_Kappr_login.class);
            startActivity(intent);
        }



    }


//todo Dinsdag:maak front page mooi
//todo Dinsdag: todo mylistadapt en second_activity met globale variablen
//todo
//todo
//todo