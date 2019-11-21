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


public class MijnKappr extends AppCompatActivity {



    @Override
    public boolean onCreateOptionsMenu (Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.screen_2_menu, menu);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Kapsalons");
        return true;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mijn_kappr);
        Toolbar Toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(Toolbar);
        TabsPagerAdapter tabsPagerAdapter = new TabsPagerAdapter(this, getSupportFragmentManager());

        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(tabsPagerAdapter);

        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);





    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                OpenMain();
                break;
            case R.id.item3:

                break;
            case R.id.subitem1:

                OpenSettings();

                break;
            case R.id.subitem2:

                OpenMijnkappr();

                break;
            case R.id.subitem3:

                OpenDatabase_test();

                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void OpenDatabase_test() {
        Intent intent = new Intent(this, database_test.class);
        startActivity(intent);

    }


    public void OpenMijnkappr() {
        Intent intent = new Intent(this, MijnKappr.class);
        startActivity(intent);

    }

    public void OpenSettings() {
        Intent intent = new Intent(this, SettingsActivity.class);
        startActivity(intent);

    }

    public void OpenMain() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);

    }



}
