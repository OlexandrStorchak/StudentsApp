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
        if (getIntent().getAction() == "START_GOOGLE_PLUS") {
            GetGoogleApi getGoogleApi = new GetGoogleApi(this);
            getGoogleApi.execute(google);
        } else {

            GetGitApi getGitApi = new GetGitApi(this);
            getGitApi.execute(google);
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
