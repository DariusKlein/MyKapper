package com.example.mykapper;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static com.example.mykapper.MyListAdapter.inflater;


public class reserveren extends Fragment implements View.OnClickListener {

    static String Afspraak_datum;
    private static final String TAG = "reserveren";

    public String selectedDate;

    private PageViewModel2 pageViewModel;
    List<String> bechikbare_tijden = new ArrayList<>();
    private Spinner spinner;
    List<String> list = new ArrayList<String>();
    public Button reservern;
    public Button laadkapper;
    public CalendarView calendar1;
    static String Tijd_Afspraak;

    public reserveren() {

    }

    public static reserveren newInstance() {
        return new reserveren();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pageViewModel = ViewModelProviders.of(this).get(PageViewModel2.class);
        pageViewModel.setIndex(TAG);



    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_reserveren, container, false);

        final TextView textView = root.findViewById(R.id.section_label);
        pageViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
            }
        });
        reservern = root.findViewById(R.id.Reserveren);
        reservern.setOnClickListener(this);
        laadkapper = root.findViewById(R.id.load_kappers);
        laadkapper.setOnClickListener(this);
        calendar1 = root.findViewById(R.id.calendarView);

        setSpinner(root);

        calendar1.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                String Date = dayOfMonth + "-" + (month + 1) + "-" + year;
                selectedDate = Date;
            }
        });



        return root;
    }

    public void setSpinner(View root) {

        spinner = (Spinner) root.findViewById(R.id.beschikbaarheid);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Tijd_Afspraak = parent.getItemAtPosition(position).toString();
                reservern.setVisibility(View.VISIBLE);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
            });
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);
    }





    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.Reserveren:
                Intent Reserveren_final = new Intent(getContext(), Reserveren_final.class);
                Open_activity(Reserveren_final);
                break;
            case R.id.load_kappers:


                //SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
                //String dateString = simpleDateFormat.format(selectedDate);
                Afspraak_datum = (String.format(selectedDate));

                FirebaseFirestore db = FirebaseFirestore.getInstance();


                db.collection("Tijden")
                        .whereEqualTo("Beschikbaar", true)
                        .get()
                        .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                if (task.isSuccessful()) {

                                    list.clear();
                                    for (QueryDocumentSnapshot document : task.getResult()) {


                                        String besechikbare_tijdenSTR = document.getId();
                                        list.add(besechikbare_tijdenSTR);


                                    }
                                    ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, list);
                                    dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                    spinner.setAdapter(dataAdapter);
                                }


                            }

                        });
                break;

        }

    }


    public void Open_activity(Intent intent) {
        this.startActivity(intent);
    }


}
