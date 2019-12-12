package com.example.mykapper;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import static com.example.mykapper.MainActivity.Newpage;

import static com.example.mykapper.R.id.Tabs1;
import static com.example.mykapper.R.id.toolbar;
import static com.example.mykapper.R.id.view_pager;

import static com.example.mykapper.R.layout.activity_kapsalon_algemeen;


public class Kapsalon_algemeen extends AppCompatActivity {


    @Override
    public boolean onCreateOptionsMenu (Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.screen_2_menu, menu);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Kappers algemeen");
        return true;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(activity_kapsalon_algemeen);
        Toolbar Toolbar = findViewById(toolbar);
        setSupportActionBar(Toolbar);
        TabsPagerAdapter2 TabsPagerAdapter = new TabsPagerAdapter2(this, getSupportFragmentManager());
        ViewPager viewPager = findViewById(view_pager);
        viewPager.setAdapter(TabsPagerAdapter);



        TabLayout tabs = findViewById(Tabs1);
        tabs.setupWithViewPager(viewPager);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                Intent intent = new Intent(this, MainActivity.class);
                Open_activity(intent);
                break;
            case R.id.subitem1:
                Intent SettingsActivity = new Intent(this, SettingsActivity.class);
                Open_activity(SettingsActivity);
                break;
            case R.id.subitem2:
                Intent Mijn_Kappr_login = new Intent(this, Mijn_Kappr_login.class);
                Open_activity(Mijn_Kappr_login);
                break;
            case R.id.subitem3:
                Intent database_test = new Intent(this, database_test.class);
                Open_activity(database_test);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void Open_activity(Intent intent) {
        this.startActivity(intent);
    }
}
