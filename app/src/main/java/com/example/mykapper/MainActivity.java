package com.example.mykapper;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;

import static com.example.mykapper.MyListAdapter.inflater;
import static com.example.mykapper.MyListAdapter.rowView;


public class MainActivity extends AppCompatActivity
    implements View.OnClickListener {


    static Integer[] imagesList = {R.drawable.kapper_1, R.drawable.kapper_2, R.drawable.kapper_3, R.drawable.kapper_4,
                                   R.drawable.kapper_5, R.drawable.kapper_6, R.drawable.kapper_7, R.drawable.kapper_8};

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
                Intent SettingsActivity = new Intent(this, SettingsActivity.class);
                Open_activity(SettingsActivity);
                break;
            case R.id.MyAccount:
                Intent Mijn_Kappr_login = new Intent(this, Mijn_Kappr_login.class);
                Open_activity(Mijn_Kappr_login);
                break;
            case R.id.Zoeken:
                rowView = null;
                inflater = null;

                Intent Second_activity = new Intent(this, Second_activity.class);
                Open_activity(Second_activity);
                break;

        }
    }

    public void Open_activity(Intent intent) {
        this.startActivity(intent);
    }


    }





