package com.example.mykapper;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.io.File;
import java.io.FileInputStream;
import java.sql.Array;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity
    implements View.OnClickListener {


    Integer[] images = {R.drawable.download_3,R.drawable.download_2, R.drawable.download_1, R.drawable.Kapper_3};

    public int YourRequestCode = 1;

    private Button Button;
    private Button SettingButton;
    private Button My_MyKappr;

    private RatingBar StartRating;

    private FirebaseAuth mAuth;

    static ArrayList ImageList;

    static float Rating;

    static String Newpage;


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

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {


        }
        else{
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION},YourRequestCode);
        }




        }



    public  void  firstTime(){

        ImageList.add(R.drawable.download_1);

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
                Newpage = "settings";
                Open_activity();
                break;
            case R.id.MyAccount:
                Newpage = "Mijn_Kappr_login";
                Open_activity();
                break;
            case R.id.Zoeken:
                Newpage = "Second_activity";
                Open_activity();
                break;

        }
    }

    public void Open_activity(){
        Intent intent = new Intent(this, Functions.class);
        this.startActivity(intent);
    }
    }





