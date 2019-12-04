package com.example.arduino_test;


import android.os.AsyncTask;
import android.os.Bundle;
//import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button btnOff;
    Button btnOn;



    EditText editIp;
    TextView textInfo1;
    TextView textInfo2;

    View.OnClickListener btnOnOffClickListener = new View.OnClickListener() {

        public void onClick(View v) {
            String onoff;
            if (v == MainActivity.this.btnOn) {
                onoff = "/on";
            } else {
                onoff = "/off";
            }
            MainActivity.this.btnOn.setEnabled(false);
            MainActivity.this.btnOff.setEnabled(false);
            new TaskEsp(MainActivity.this.editIp.getText().toString() + onoff).execute(new Void[0]);
        }
    };





    private class TaskEsp extends AsyncTask<Void, Void, String> {
        String server;

        TaskEsp(String server2) {
            this.server = server2;
        }

        /* access modifiers changed from: protected */
        public String doInBackground(Void... params) {
            final String p = "http://" + this.server;
            MainActivity.this.runOnUiThread(new Runnable() {
                public void run() {
                    MainActivity.this.textInfo1.setText(p);
                }
            });
            String serverResponse = "";
            try {
                HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(p).openConnection();
                if (httpURLConnection.getResponseCode() != 200) {
                    return serverResponse;
                }
                InputStream inputStream = httpURLConnection.getInputStream();
                String serverResponse2 = new BufferedReader(new InputStreamReader(inputStream)).readLine();
                inputStream.close();
                return serverResponse2;
            } catch (MalformedURLException e) {
                e.printStackTrace();
                return e.getMessage();
            } catch (IOException e2) {
                e2.printStackTrace();
                return e2.getMessage();
            }
        }

        /* access modifiers changed from: protected */
        public void onPostExecute(String s) {
            MainActivity.this.textInfo2.setText(s);
            MainActivity.this.btnOn.setEnabled(true);
            MainActivity.this.btnOff.setEnabled(true);
        }
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) R.layout.activity_main);
        this.editIp = (EditText) findViewById(R.id.ip);
        this.btnOn = (Button) findViewById(R.id.bon);
        this.btnOff = (Button) findViewById(R.id.boff);
        this.textInfo1 = (TextView) findViewById(R.id.info1);
        this.textInfo2 = (TextView) findViewById(R.id.info2);
        this.btnOn.setOnClickListener(this.btnOnOffClickListener);
        this.btnOff.setOnClickListener(btnOnOffClickListener);
    }
}

