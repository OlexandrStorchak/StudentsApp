package com.example.alex.studentsrecycler.activity;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.alex.studentsrecycler.R;
import com.example.alex.studentsrecycler.asynkTask.GetGitApi;
import com.example.alex.studentsrecycler.asynkTask.GetGoogleApi;

import static com.example.alex.studentsrecycler.asynkTask.GetGoogleApi.googleAvatar;
import static com.example.alex.studentsrecycler.asynkTask.GetGoogleApi.googleName;

public class StudentsDetailActivity extends AppCompatActivity {
    static public ImageView avatar;
    ImageButton buttonOk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_students_detail);


        String google = getIntent().getStringExtra("google");
        String data = getIntent().getDataString();

        if (data == null) {
            Log.d("log", String.valueOf(getIntent().getData()));
            if (getIntent().getAction() == "START_GOOGLE_PLUS") {
                GetGoogleApi getGoogleApi = new GetGoogleApi(this);
                getGoogleApi.execute(google);
            } else {

                GetGitApi getGitApi = new GetGitApi(this);
                getGitApi.execute(google);
            }
        } else {
            Log.d("logLink", String.valueOf(getIntent().getDataString()));
            if (data.contains("https://github.com/")) {

                data = data.replace("https://github.com/", "");
                data = data + "/";
                Log.d("logLink", data);
                int i = 0;
                String t = "";

                do {

                    //Log.d("logLink", (String.valueOf(data.charAt(i))));
                    t = t + String.valueOf(data.charAt(i));
                    i++;


                } while (!String.valueOf(data.charAt(i)).equals("/"));

                Log.d("logLink", t);
                GetGitApi getGitApi = new GetGitApi(this);
                getGitApi.execute(t);

            } else if (data.contains("https://plus.google.com/")){
                data = data.replace("https://plus.google.com/", "");
                data = data + "/";
                Log.d("logLink", data);
                int i = 0;
                String t = "";

                do {

                    //Log.d("logLink", (String.valueOf(data.charAt(i))));
                    t = t + String.valueOf(data.charAt(i));
                    i++;


                } while (!String.valueOf(data.charAt(i)).equals("/"));

                Log.d("logLink", t);
                GetGoogleApi getGoogleApi = new GetGoogleApi(this);
                getGoogleApi.execute(t);


            }


        }
        //Log.d("log",name);
        avatar = (ImageView) findViewById(R.id.detail_avatar);


        buttonOk = (ImageButton) findViewById(R.id.buttonDetailOk);
        buttonOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setResult(RESULT_OK);
                finish();
            }
        });

    }
}
