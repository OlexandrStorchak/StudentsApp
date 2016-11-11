package com.example.alex.studentsrecycler.adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.alex.studentsrecycler.activity.StudentsDetailActivity;
import com.example.alex.studentsrecycler.models.Person;
import com.example.alex.studentsrecycler.R;

import java.util.ArrayList;

/**
 * Created by Alex on 04.11.2016.
 */

public class AdapterRecycler extends RecyclerView.Adapter<AdapterRecycler.VH> {
    private ArrayList<Person> list;
    private LayoutInflater inflater;
    private Context context;

    public AdapterRecycler(Context context, ArrayList<Person> list) {
        this.context = context;
        this.list = list;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public VH onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = inflater.inflate(R.layout.recycler_row,parent,false);
        return new VH(v);
    }

    @Override
    public void onBindViewHolder(VH holder, int position) {
        Person person = list.get(position);
        holder.name.setText(person.getName());
        holder.name.setContentDescription(person.getGoogle());
        holder.git.setContentDescription(person.getGit());


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class VH extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView name;
        Button git;


        VH(View itemView) {
            super(itemView);
            name = (TextView)itemView.findViewById(R.id.recycler_name_row);
            git = (Button)itemView.findViewById(R.id.recycler_git_button);
            git.setOnClickListener(this);
            name.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.recycler_git_button:
                    Log.d("log","GIT button to " + getAdapterPosition());
                    Intent googleIntent = new Intent(Intent.ACTION_VIEW,Uri.parse((String) git.getContentDescription()));
                    context.startActivity(googleIntent);
                    break;
                case R.id.recycler_name_row:
                    Log.d("log","Google to " + getAdapterPosition());
                    //Intent gitIntent = new Intent(Intent.ACTION_VIEW,Uri.parse((String) name.getContentDescription()));
                   // context.startActivity(gitIntent);
                    Intent detail = new Intent(context, StudentsDetailActivity.class);

                    detail.putExtra("google",name.getContentDescription());
                    context.startActivity(detail);
                    break;
            }

        }
    }
}
