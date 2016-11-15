package com.example.alex.studentsrecycler.asynkTask;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.example.alex.studentsrecycler.activity.StudentsDetailActivity;

import org.json.JSONArray;
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

/**
 * Created by Alex on 11.11.2016.
 */

public class GetGoogleApi extends AsyncTask<String, Void, String> {
    Activity activity;

    public GetGoogleApi(Activity activity) {
        this.activity = activity;
    }

    public static String googleName;
    public static String googleAvatar;
    Bitmap ava = null;


    @Override
    protected String doInBackground(String... strings) {
        final String API_KEY = "?key=AIzaSyA7siHAWva567IWazHQP2DGBd3Ja1lxsQ8";
        HttpURLConnection httpConnect = null;
        BufferedReader bufferedReader = null;
        String googleJSon = null;

        URL requestGoogle;
        try {
            requestGoogle = new URL("https://www.googleapis.com/plus/v1/people/" + strings[0] + API_KEY);
            httpConnect = (HttpURLConnection) requestGoogle.openConnection();
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
                buffer.append(line).append("");
            }
            if (buffer.length() == 0) {
                return null;
            }

            googleJSon = buffer.toString();
            Log.d("log", googleJSon);
            httpConnect.disconnect();
            getDataFromJson(googleJSon);

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }


        try {
            InputStream inAva = new URL(googleAvatar).openStream();
            ava = BitmapFactory.decodeStream(inAva);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        return googleJSon;

    }

    void getDataFromJson(String json) throws JSONException {


        JSONObject allData = new JSONObject(json);
        googleName = String.valueOf(allData.get("displayName"));
        Log.d("log", googleName);
        JSONObject avatar = allData.getJSONObject("image");
        googleAvatar = String.valueOf(avatar.get("url") + "0");
        Log.d("log", googleAvatar);

    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        googleName = null;
        googleAvatar = null;
        activity.setTitle("Google loading data...");
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        Log.d("logLink", "name" + googleName);
        if (googleName != null) {
            activity.setTitle(googleName);
            avatar.setImageBitmap(ava);
        } else {
            activity.setTitle("Nothing to show");
        }


    }
}
