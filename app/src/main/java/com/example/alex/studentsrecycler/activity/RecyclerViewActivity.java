package com.example.alex.studentsrecycler.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;

import com.example.alex.studentsrecycler.models.Person;
import com.example.alex.studentsrecycler.R;
import com.example.alex.studentsrecycler.data.RealData;
import com.example.alex.studentsrecycler.adapters.AdapterRecycler;

import java.util.ArrayList;

public class RecyclerViewActivity extends AppCompatActivity {
ArrayList<Person> list;
    RecyclerView recyclerView;
    LayoutInflater inflater;
     AdapterRecycler adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);
        getSupportActionBar().setTitle(R.string.recyclerview);

        list = new ArrayList<>();
        for (int i = 0; i<= RealData.studentsName.length-3; i=i+3) {

            list.add(new Person(RealData.studentsName[i],
                    RealData.studentsName[i+1],
                    RealData.studentsName[i+2]));

        }


        recyclerView = (RecyclerView)findViewById(R.id.recycler_id);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new AdapterRecycler(this,list);
        recyclerView.setAdapter(adapter);

    }
}
