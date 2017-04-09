package com.lejeune.david.fahrzeugewahler;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.Toast;


import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPCmd;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

public class MainActivity extends AppCompatActivity {

    Context cntx;
    MyTools myTools;

    Intent newIntent;


    FTPClient ftpClient = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //region init
        //Setting the background color to white
        getWindow().getDecorView().setBackgroundColor(Color.WHITE);

        cntx = getApplicationContext();

        myTools= new MyTools();
        myTools.retrieveSharedPref(cntx);
        System.out.println("username : " + MyVars.username);

        myTools.createFolders();
        //endregion

        //region play sound
        final MediaPlayer engine = MediaPlayer.create(this, R.raw.vehicle092);
        engine.start();
        //endregion

        String connectionType = MyTools.checkNetworkStatus(cntx);
        if (connectionType.equalsIgnoreCase("wifi")){

            //region auto redirect
            if (MyVars.username.length()>1){
                newIntent = new Intent(MainActivity.this, MenuActivity.class);
            }
            else
            {
                newIntent = new Intent(MainActivity.this, RegisterActivity.class);
            }

            //automatically launch the next activity
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                public void run() {
                    finish();
                    startActivity(newIntent);
                }
            }, 3330);
            //endregion

            // Downloading all the files
            new AsyncDataDownloadDL().execute();

        }
        else
        {
            //region Error action when no wifi
            Toast toast = Toast.makeText(MainActivity.this,
                    "This application requires an internet connection.", Toast.LENGTH_LONG);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();
            //automatically close activity
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                public void run() {
                    finish();
                }
            }, 3330);
            //endregion
        }


    }

}
