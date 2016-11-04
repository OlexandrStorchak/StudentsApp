package com.example.alex.studentsrecycler;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.alex.studentsrecycler.activity.ListViewActivity;
import com.example.alex.studentsrecycler.activity.RecyclerViewActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button toListView;
    Button toRecyclerView;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toListView = (Button)findViewById(R.id.start_list_activity);
        toListView.setOnClickListener(this);
        toRecyclerView = (Button)findViewById(R.id.start_recycler_activity);
        toRecyclerView.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.start_list_activity:
                Intent intentList = new Intent(this, ListViewActivity.class);
                startActivity(intentList);
                break;
            case R.id.start_recycler_activity:
                Intent intentRecycler = new Intent(this, RecyclerViewActivity.class);
                startActivity(intentRecycler);

        }
    }
}
