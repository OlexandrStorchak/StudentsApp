package com.example.alex.studentsrecycler.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.ListView;

import com.example.alex.studentsrecycler.R;
import com.example.alex.studentsrecycler.adapters.AdapterList;
import com.example.alex.studentsrecycler.data.RealData;
import com.example.alex.studentsrecycler.models.Person;

import java.util.ArrayList;

/**
 * Created by Alex on 04.11.2016.
 */

public class ListViewActivity extends AppCompatActivity {
    ArrayList<Person> personArrayList;

    AdapterList adapterList;
    ListView listView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);
        getSupportActionBar().setTitle(R.string.listview);
        Log.d("log","ListActivity");

        personArrayList = new ArrayList<>();
        for (int i=0;i<=RealData.studentsName.length-3;i=i+3){
            personArrayList.add(new Person(RealData.studentsName[i],
                    RealData.studentsName[i+1],
                    RealData.studentsName[i+2]));
            Log.d("log", String.valueOf(i));
        }

        adapterList = new AdapterList(this,personArrayList);

        listView = (ListView)findViewById(R.id.list_view_id);
        listView.setAdapter(adapterList);

    }
}
