package com.example.mykapper;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.view.MenuItem;


public class Functions extends AppCompatActivity {

    public void onCreate(){
        mContext = this.getApplicationContext();
    }

    private static Context mContext;

    public static void OpenDatabase_Test () {
        Intent intent = new Intent(mContext, database_test.class);
        mContext.startActivity(intent);
    }
    public static void OpenKapsalon_algemeen() {
        Intent intent = new Intent(mContext, Kapsalon_algemeen.class);
        mContext.startActivity(intent);
    }
    public static void OpenMainActivity () {
        Intent intent = new Intent(mContext, MainActivity.class);
        mContext.startActivity(intent);
    }
    public static void OpenMijn_Kappr_login () {
        Intent intent = new Intent(mContext, Mijn_Kappr_login.class);
        mContext.startActivity(intent);
    }
    public static void OpenMijn_Kappr_Register () {
        Intent intent = new Intent(mContext, Mijn_Kappr_Register.class);
        mContext.startActivity(intent);
    }
    public static void OpenMijnKappr () {
        Intent intent = new Intent(mContext, MijnKappr.class);
        mContext.startActivity(intent);
    }
    public static void Opensecond_activity () {
        Intent intent = new Intent(mContext, Second_activity.class);
        mContext.startActivity(intent);
    }
    public static void Opensettings () {
        Intent intent = new Intent(mContext, SettingsActivity.class);
        mContext.startActivity(intent);
    }


    public static void Options_menu (MenuItem item) {

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

                Functions.OpenMijnKappr();

                break;
            case R.id.subitem3:

                Functions.OpenDatabase_Test();

                break;
        }

    }
}
