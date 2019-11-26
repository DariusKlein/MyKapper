package com.example.mykapper;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import static com.example.mykapper.MainActivity.Newpage;

public class Functions extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        switch (Newpage) {
            case "Second_activity":
                Opensecond_activity();
                break;
            case "Database_Test":
                OpenDatabase_Test();
                break;
            case "Kapsalon_algemeen":
                OpenKapsalon_algemeen();
                break;
            case "MainActivity":
                OpenMainActivity();
                break;
            case "Mijn_Kappr_login":
                OpenMijn_Kappr_login();
                break;
            case "Mijn_Kappr_Register":
                OpenMijn_Kappr_Register();
                break;
            case "MijnKappr":
                OpenMijnKappr();
                break;
            case "settings":
                Opensettings();
                break;
        }
    }


        public void OpenDatabase_Test () {
            Intent intent = new Intent(this, database_test.class);
            this.startActivity(intent);
        }
        public void OpenKapsalon_algemeen () {
            Intent intent = new Intent(this, Kapsalon_algemeen.class);
            this.startActivity(intent);
        }
        public void OpenMainActivity () {
            Intent intent = new Intent(this, MainActivity.class);
            this.startActivity(intent);
        }
        public void OpenMijn_Kappr_login () {
            Intent intent = new Intent(this, Mijn_Kappr_login.class);
            this.startActivity(intent);
        }
        public void OpenMijn_Kappr_Register () {
            Intent intent = new Intent(this, Mijn_Kappr_Register.class);
            this.startActivity(intent);
        }
        public void OpenMijnKappr () {
            Intent intent = new Intent(this, MijnKappr.class);
            this.startActivity(intent);
        }
        public void Opensecond_activity () {
            Intent intent = new Intent(this, Second_activity.class);
            this.startActivity(intent);
        }
        public void Opensettings () {
            Intent intent = new Intent(this, SettingsActivity.class);
            this.startActivity(intent);
        }

    public static double distance(double lat1, double lng1, double lat2, double lng2) {

        double earthRadius = 6371;

        double dLat = Math.toRadians(lat2-lat1);
        double dLng = Math.toRadians(lng2-lng1);

        double sindLat = Math.sin(dLat / 2);
        double sindLng = Math.sin(dLng / 2);

        double a = Math.pow(sindLat, 2) + Math.pow(sindLng, 2)
                * Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2));

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));

        double dist = earthRadius * c;

        return dist;
}

    }
