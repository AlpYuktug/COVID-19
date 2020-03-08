package com.alpyuktug.covid_19.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.widget.Toast;

import com.alpyuktug.covid_19.R;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        Thread timerThread = new Thread(){
            public void run(){
                try{
                    sleep(3000);
                }catch(InterruptedException e){
                    e.printStackTrace();
                }finally{
                    CheckNetwork();
                }
            }
        };
        timerThread.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }

    protected void CheckNetwork() {

        ConnectivityManager cm = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnectedOrConnecting()) {

            Intent intent = new Intent(SplashActivity.this, MainActivity.class);
            startActivity(intent);

        } else {
            runOnUiThread(new Runnable() {
                public void run() {
            Toast.makeText(SplashActivity.this,  getResources().getString(R.string.network),Toast.LENGTH_LONG).show();
        }
        });
    }
    }
}




