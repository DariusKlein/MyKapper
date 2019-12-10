package com.example.mykapper;

import android.app.Activity;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;
import java.util.TreeMap;


import java.util.ArrayList;

class MyListAdapter extends ArrayAdapter<String> {

    private final Activity context;
    private final ArrayList<String> maintitle;
    private final ArrayList<String> subtitle;
    private final ArrayList<Integer> imgid;
    TreeMap<Float, Integer> DocIDandPIC;
    TreeMap<Float, String> ListListList;

    public MyListAdapter(Activity context, ArrayList<String> maintitle, ArrayList<String> subtitle, ArrayList<Integer> imgid,
                         TreeMap<Float, Integer> DocIDandPIC, TreeMap<Float, String> ListListList) {

        super(context, R.layout.mylist, maintitle);


        this.context=context;
        this.maintitle=maintitle;
        this.subtitle=subtitle;
        this.imgid=imgid;
        this.DocIDandPIC = DocIDandPIC;
        this.ListListList = ListListList;


    }

    public View getView(int position,View view,ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.mylist, null,true);

        TextView titleText = (TextView) rowView.findViewById(R.id.title);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.icon);
        TextView subtitleText = (TextView) rowView.findViewById(R.id.subtitle);


        List<String> Title = new ArrayList<>();
        Title.addAll((ListListList.values()));

        List<Integer> Image = new ArrayList<>();
        Image.addAll((DocIDandPIC.values()));

        List<Float> Afstand = new ArrayList<>();
        Afstand.addAll((ListListList.keySet()));



        titleText.setText(maintitle.get(position));
        imageView.setImageResource(imgid.get(position));
        subtitleText.setText((Afstand.get(position) + " KM"));

        return rowView;

    }
}