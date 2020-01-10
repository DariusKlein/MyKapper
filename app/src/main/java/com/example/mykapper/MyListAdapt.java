package com.example.mykapper;

import android.app.Activity;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;


import java.util.List;
import java.util.TreeMap;


import java.util.ArrayList;

import static com.example.mykapper.Second_activity.GPS;

class MyListAdapter extends ArrayAdapter<String> {

    private final Activity context;
    private final ArrayList<String> maintitle;
    private final ArrayList<String> subtitle;
    private final ArrayList<Integer> imgid;
    TreeMap<Float, Integer> DocIDandPIC;
    TreeMap<Float, String> ListListList;
    static List<String> Title = new ArrayList<>();
    List<Integer> Image = new ArrayList<>();
    List<Float> Afstand = new ArrayList<>();
    static View rowView = null;
    static LayoutInflater inflater = null;


    public MyListAdapter(Activity context, ArrayList<String> maintitle, ArrayList<String> subtitle, ArrayList<Integer> imgid,
                         TreeMap<Float, Integer> DocIDandPIC, TreeMap<Float, String> ListListList) {

        super(context, R.layout.mylist, maintitle);



        this.context=context;
        this.maintitle=maintitle;
        this.subtitle=subtitle;
        this.imgid=imgid;
        this.DocIDandPIC = DocIDandPIC;
        this.ListListList = ListListList;

        Title.removeAll(Title);
        Title.addAll((ListListList.values()));
        Image.removeAll(Image);
        Image.addAll((DocIDandPIC.values()));
        Afstand.removeAll(Afstand);
        Afstand.addAll((ListListList.keySet()));




    }
    @NonNull
    public View getView(int position,View view,ViewGroup parent) {




            inflater = context.getLayoutInflater();
            rowView = inflater.inflate(R.layout.mylist, null, true);


            TextView titleText = (TextView) rowView.findViewById(R.id.title);
            ImageView imageView = (ImageView) rowView.findViewById(R.id.icon);
            TextView subtitleText = (TextView) rowView.findViewById(R.id.subtitle);

        if (Title.size() > position) {

            titleText.setText(Title.get(position));
            imageView.setImageResource(Image.get(position));
            if (GPS == true) {
                subtitleText.setText((Afstand.get(position) + "KM"));
            }else{
                subtitleText.setText(("Location ERROR: Afstand niet berekenbaar"));
            }
        }

        return rowView;

    }
}