package com.example.mykapper;

import androidx.appcompat.app.AppCompatActivity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;



public class MainActivity extends AppCompatActivity
    implements View.OnClickListener {
    private Button Button;
    private Button SettingButton;
    private Button My_MyKappr;
    private RatingBar StartRating;

    private FirebaseAuth mAuth;

    static float Rating;

    static boolean loggedIn = false;


    String preferences_name = "isFirstTime";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();

        Button = findViewById(R.id.Zoeken);
        Button.setOnClickListener(this);

        SettingButton = findViewById(R.id.Instellingen);
        SettingButton.setOnClickListener(this);

        My_MyKappr = findViewById(R.id.MyAccount);
        My_MyKappr.setOnClickListener(this);

        mAuth = FirebaseAuth.getInstance();



        firstTime();
        }



    public  void  firstTime(){

        SharedPreferences sharedTime = getSharedPreferences(preferences_name,0);
        if (sharedTime.getBoolean("firstTime",true))
        {
            sharedTime.edit().putBoolean("firstTime",false).apply();
        }
        else FirebaseAuth.getInstance().signOut();
    }

    @Override
    public void onStart() {
        super.onStart();

        FirebaseUser currentUser = mAuth.getCurrentUser();

        System.out.print(currentUser);


    }


    public void onClick(View view) {
        StartRating = findViewById(R.id.StartRating);

        Rating = StartRating.getRating();

        System.out.println(Rating);

        switch (view.getId()) {
            case R.id.Instellingen:
                Functions.Opensettings ();
                break;
            case R.id.MyAccount:
                Functions.OpenMijn_Kappr_login ();

                break;
            case R.id.Zoeken:
                Functions.Opensecond_activity();
                break;

        }
    }







    }


//todo Dinsdag:maak front page mooi
//todo Dinsdag: todo mylistadapt en second_activity met globale variablen
//todo
//todo
//todo



