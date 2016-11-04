package com.example.alex.studentsrecycler.adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.alex.studentsrecycler.R;
import com.example.alex.studentsrecycler.models.Person;

import java.util.ArrayList;
import java.util.zip.Inflater;

/**
 * Created by Alex on 04.11.2016.
 */

public class AdapterList extends BaseAdapter {
    Context context;
    ArrayList<Person> persons;
    LayoutInflater inflater;

    public AdapterList(Context context, ArrayList<Person> persons) {
        this.context = context;
        this.persons = persons;
        inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return persons.size();
    }

    @Override
    public Object getItem(int i) {
        return persons.get(i);
    }

    @Override
    public long getItemId(int i) {

        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup)  {
            View newView = view;
        if (newView==null){

             newView =  inflater.inflate(R.layout.recycler_row,viewGroup,false);
        }
        Person person = persons.get(i);
        TextView name;
        Button git;
        name = (TextView)newView.findViewById(R.id.recycler_name_row);
        git = (Button)newView.findViewById(R.id.recycler_git_button);
        name.setText(person.getName());
        name.setContentDescription(person.getGoogle());

        git.setContentDescription(person.getGit());
        name.setOnClickListener(new MyClick(i));
        git.setOnClickListener(new MyClick(i));



        return newView;
    }



    private class MyClick implements View.OnClickListener {
        int position;

        public MyClick(int position) {
            this.position = position;
        }

        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.recycler_git_button:
                    Log.d("log","GIT button to " );
                    Intent googleIntent = new Intent(Intent.ACTION_VIEW,Uri.parse( persons.get(position).getGit()));
                    context.startActivity(googleIntent);
                    break;
                case R.id.recycler_name_row:
                    Log.d("log","Google to " );
                    Intent gitIntent = new Intent(Intent.ACTION_VIEW,Uri.parse(persons.get(position).getGoogle()));
                    context.startActivity(gitIntent);
                    break;
            }

        }

        }
    }


