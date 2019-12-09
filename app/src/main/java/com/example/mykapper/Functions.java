package com.example.mykapper;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import static com.example.mykapper.MainActivity.Newpage;
import static com.example.mykapper.Second_activity.KapperID;
import static com.example.mykapper.Second_activity.maintitle;


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
            case "Loading":
                Loading();
                break;
            case "Kapsalon_algemeen":
                //for (String i : maintitle)

                //try {
                    //DynamicIntent(Class.forName("com.example.mykapper." + i ));

                //} catch (ClassNotFoundException e) {

                //}

                OpenKapsalon_algemeen();

                break;
        }
    }



    public void DynamicIntent(Class activityClass) {
        Intent intent = new Intent(this, activityClass);

        startActivity(intent);
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
        public void Loading () {
            Intent intent = new Intent(this, Loading.class);
            this.startActivity(intent);
        }
    public void Kapper_Placeholder1 () {
        Intent intent = new Intent(this, SettingsActivity.class);
        this.startActivity(intent);
    }
    public void Kapper_placeholder2 () {
        Intent intent = new Intent(this, SettingsActivity.class);
        this.startActivity(intent);
    }

}


