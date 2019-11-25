package com.example.mykapper;


import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;


import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.viewpager.widget.ViewPager;


import com.google.android.material.tabs.TabLayout;

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
        return super.onOptionsItemSelected(item);
    }



}
