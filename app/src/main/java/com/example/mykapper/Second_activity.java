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

import static com.example.mykapper.MainActivity.Newpage;


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

        Toolbar Toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(Toolbar);


            list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    if (position == 0) {
                        Newpage = "Kapsalon_algemeen";
                        Open_activity();
                    } else if (position == 1) {
                        Newpage = "Kapsalon_algemeen";
                        Open_activity();
                    } else if (position == 2) {

                        Newpage = "Kapsalon_algemeen";
                        Open_activity();
                    } else if (position == 3) {

                        Newpage = "Kapsalon_algemeen";
                        Open_activity();

                    } else if (position == 4) {

                        Newpage = "Kapsalon_algemeen";
                        Open_activity();
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
                Newpage = "MainActivity";
                Open_activity();
                break;
            case R.id.subitem1:
                Newpage = "settings";
                Open_activity();
                break;
            case R.id.subitem2:
                Newpage = "Mijn_Kappr_login";
                Open_activity();
                break;
            case R.id.subitem3:
                Newpage = "Database_Test";
                Open_activity();
                break;
        }
        return super.onOptionsItemSelected(item);
    }


    public void Open_activity(){
        Intent intent = new Intent(this, Functions.class);
        this.startActivity(intent);
    }
    }




