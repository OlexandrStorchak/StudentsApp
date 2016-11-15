package com.example.alex.studentsrecycler.asynkTask;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import static com.example.alex.studentsrecycler.activity.StudentsDetailActivity.avatar;
import static com.example.alex.studentsrecycler.asynkTask.GetGoogleApi.googleAvatar;

/**
 * Created by Alex on 11.11.2016.
 */

public class GetGitApi extends AsyncTask<String, Void, String> {
    Activity activity;
    Bitmap ava;
    String avatarUrl;
    String gitName;

    public GetGitApi(Activity activity) {
        this.activity = activity;
    }

    @Override
    protected String doInBackground(String... strings) {

        HttpURLConnection httpConnect = null;
        BufferedReader bufferedReader = null;


        URL requestGit = null;
        String gitJSon = null;
        try {
            requestGit = new URL("https://api.github.com/users/" + strings[0]);
            httpConnect = (HttpURLConnection) requestGit.openConnection();
            httpConnect.setRequestMethod("GET");
            httpConnect.connect();

            InputStream inStream = httpConnect.getInputStream();
            StringBuilder buffer = new StringBuilder();
            if (inStream == null) {
                return null;
            }
            bufferedReader = new BufferedReader(new InputStreamReader(inStream));
            String line;

            while ((line = bufferedReader.readLine()) != null) {
                buffer.append(line).append(" ");
            }
            if (buffer.length() == 0) {
                return null;
            }

            gitJSon = buffer.toString();
            Log.d("log", gitJSon);
            httpConnect.disconnect();
            getDataFromJson(gitJSon);

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }


        try {

            InputStream inAva = new URL(avatarUrl).openStream();
            ava = BitmapFactory.decodeStream(inAva);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        return gitJSon;

    }

    void getDataFromJson(String json) throws JSONException {


            JSONObject allData = new JSONObject(json);
            avatarUrl = allData.getString("avatar_url");
            gitName = allData.getString("name");



    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        activity.setTitle("Git loading data...");
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        if (gitName != null) {
            avatar.setImageBitmap(ava);
            activity.setTitle("GIT " + gitName);
        } else {
            activity.setTitle("Nothing to show");
        }
    }
}
