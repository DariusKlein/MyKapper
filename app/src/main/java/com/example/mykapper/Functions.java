package com.example.mykapper;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;



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
}
