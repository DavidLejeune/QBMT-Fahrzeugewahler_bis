package com.lejeune.david.fahrzeugewahler;

import android.os.AsyncTask;

/**
 * Created by Lucian on 4/8/2017.
 */

public class AsyncDataDownloadDL extends AsyncTask<String, String, String> {


    private FTPFunctions ftpclient = null;

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
    }

    @Override
    protected void onProgressUpdate(String... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected String doInBackground(String... params) {

        ftpclient = new FTPFunctions();
        ftpclient.ftpConnect(MyVars.HOST, MyVars.USERNAME, MyVars.PASSWORD, 21);

        boolean status = false;
        status = ftpclient.downloadDataFiles(MyVars.FOLDER_DATA);
        ftpclient.ftpDisconnect();

        return null;
    }

}

