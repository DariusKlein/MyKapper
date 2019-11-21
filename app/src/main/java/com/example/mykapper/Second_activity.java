package com.example.mykapper;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;



public class Second_activity extends AppCompatActivity {
    ListView list;



    String[] maintitle = {
            "Title 1", "Title 2",
            "Title 3", "Title 4",
            "Title 5",
    };

    String[] subtitle = {
            "Sub Title 1", "Sub Title 2",
            "Sub Title 3", "Sub Title 4",
            "Sub Title 5",
    };

    Integer[] imgid = {
            R.drawable.download_1, R.drawable.download_2,
            R.drawable.download_3, R.drawable.download_4,
            R.drawable.download_5,
    };




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_activity);

        MyListAdapter adapter = new MyListAdapter(this, maintitle, subtitle, imgid);
        list = findViewById(R.id.list);
        list.setAdapter(adapter);

        Toolbar Toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(Toolbar);


            list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    if (position == 0) {


                        //todo kapper die op positie 0 staat = Gekozenkapper (variable) en geef dit door
                        openActivity6(); //todo maak pagina's voor iedere kapper en link naar de goede kapper
                    } else if (position == 1) {


                        //todo kapper die op positie 1 staat = Gekozenkapper (variable) en geef dit door
                        openActivity6();//todo maak pagina's voor iedere kapper en link naar de goede kapper
                    } else if (position == 2) {

                        //todo kapper die op positie 2 staat = Gekozenkapper (variable) en geef dit door
                        openActivity6();//todo maak pagina's voor iedere kapper en link naar de goede kapper

                    } else if (position == 3) {

                        //todo kapper die op positie 3 staat = Gekozenkapper (variable) en geef dit door
                        openActivity6();//todo maak pagina's voor iedere kapper en link naar de goede kapper


                    } else if (position == 4) {

                        //todo kapper die op positie 4 staat = Gekozenkapper (variable) en geef dit door
                        openActivity6(); //todo maak pagina's voor iedere kapper en link naar de goede kapper

                    }
                }
            });

        }

        @Override
        public boolean onCreateOptionsMenu (Menu menu){
            MenuInflater inflater = getMenuInflater();
            inflater.inflate(R.menu.screen_2_menu, menu);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle("Kapsalons");
            return true;
        }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                openActivity1();
                break;
            case R.id.item3:

                break;
            case R.id.subitem1:

                openActivity5();

                break;
            case R.id.subitem2:

                openActivity4();

                break;
            case R.id.subitem3:

                openActivity3();

                break;
        }
        return super.onOptionsItemSelected(item);
    }
    public void openActivity3() {
        Intent intent = new Intent(this, database_test.class);
        startActivity(intent);

    }
    public void openActivity1() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);

    }

    public void openActivity4() {
        Intent intent = new Intent(this, Mijn_Kappr_login.class);
        startActivity(intent);

    }

    public void openActivity5() {
        Intent intent = new Intent(this, SettingsActivity.class);
        startActivity(intent);

    }
    public void openActivity6() {
        Intent intent = new Intent(this, Kapsalon_algemeen.class);
        startActivity(intent);

    }
    }




