package com.lejeune.david.fahrzeugewahler;

import android.content.Context;
import android.net.ConnectivityManager;
import android.os.Environment;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Lucian on 4/7/2017.
 */

public class MyTools {

    //region Shared pref
    public void retrieveSharedPref(Context cntx){
        MyVars.username = SharedPrefHelper.getInstance().getValue(cntx, "username");
        MyVars.avatar = SharedPrefHelper.getInstance().getValue(cntx, "avatar");
        MyVars.car = SharedPrefHelper.getInstance().getValue(cntx, "car");
        MyVars.option0 = SharedPrefHelper.getInstance().getValue(cntx, "option0");
        MyVars.option1 = SharedPrefHelper.getInstance().getValue(cntx, "option1");
        MyVars.carname = SharedPrefHelper.getInstance().getValue(cntx, "carname");
        MyVars.fullprice = SharedPrefHelper.getInstance().getValue(cntx, "fullprice");
        System.out.println("car"+ MyVars.car);
    }
    //endregion

    //region folder management
    public void createFolders() {

        String[] foldernames = {MyVars.ROOT_FOLDER,MyVars.FOLDER_DATA };

        for (String s: foldernames)
        {
            File root = new File(Environment.getExternalStorageDirectory(),s);
            if (!root.exists()) {
                root.mkdirs();
                if (root.exists()) {
                }
                else
                {
                }
            }
            else
            {
            }
        }

    }
    public static void deleteFolders(){

        String[] foldernames = {MyVars.ROOT_FOLDER,MyVars.FOLDER_DATA };

        for (String s: foldernames)
        {

            File dir = new File(Environment.getExternalStorageDirectory()+ s );
            if (dir.isDirectory())
            {
                String[] children = dir.list();
                for (int i = 0; i < children.length; i++)
                {
                    new File(dir, children[i]).delete();
                }
            }

        }


    }
    //endregion

    //region querying data
    public static String queryValueCars(String filename, String car ,int indexValue){
        File dir = Environment.getExternalStorageDirectory();
        File file = new File(dir, MyVars.FOLDER_DATA + filename + ".txt");
        String line = "";

        String result = "" ;

        if (file.exists()) {

            StringBuilder text = new StringBuilder();
            try {
                BufferedReader br = new BufferedReader(new FileReader(file));
                while ((line = br.readLine()) != null) {
                    if (line.length() > 0) {
                        {
                            String[] str = line.split(",");
                            if (line.toLowerCase().contains(car.toLowerCase()))
                            {
                                result = str[indexValue];
                            }
//                            String[] str = line.split(",");
//
//                            String[] str = line.split(",");
//                            String id = str[0];
//                            String nameCar = str[1];
//                            String image = str[3];
//                            String price = str[4];

                            //System.out.println("typeDoc " + typeDoc);
//                            }

                        }

                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else
        {
            result = "0";
        }

        return result;
    }

    public static void queryValueOptions(String filename){
        File dir = Environment.getExternalStorageDirectory();
        File file = new File(dir, MyVars.FOLDER_DATA + filename + ".txt");
        String line = "";

        if (file.exists()) {

            StringBuilder text = new StringBuilder();
            try {
                BufferedReader br = new BufferedReader(new FileReader(file));
                while ((line = br.readLine()) != null) {
                    if (line.length() > 0) {
                        {
                            String[] str = line.split(",");
                            System.out.println(line);
                            if (line.toLowerCase().contains("option0") && line.toLowerCase().contains("true") )
                            {
                                MyVars.boolOption0 = true;
                            }

                            if (line.toLowerCase().contains("option1") && line.toLowerCase().contains("true") )
                            {
                                MyVars.boolOption1 = true;
                            }

                        }

                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public static String queryPriceOption(String filename ){
        File dir = Environment.getExternalStorageDirectory();
        File file = new File(dir, MyVars.FOLDER_DATA + filename + ".txt");
        String line = "";
        String result = "";
        if (file.exists()) {
            System.out.println("file eitst");
            StringBuilder text = new StringBuilder();
            try {
                BufferedReader br = new BufferedReader(new FileReader(file));
                while ((line = br.readLine()) != null) {
                    if (line.length() > 0) {
                        {
                            String[] str = line.split(",");
                            String option = str[3];
                            String price = str[4];
                            if (option.equalsIgnoreCase("option0") )
                            {
                                MyVars.priceOp0 = price;
                            }
                            if (option.equalsIgnoreCase("option1") )
                            {
                                MyVars.priceOp1 = price;
                            }

                        }

                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return result;
    }

    public static void createArrayListAvailableCars(){
    File dir = Environment.getExternalStorageDirectory();
    File file = new File(dir, MyVars.FOLDER_DATA + "car_types.txt");
    String line = "";

    MyVars.imgArr = new ArrayList<String>();


    if (file.exists()) {

        StringBuilder text = new StringBuilder();
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            while ((line = br.readLine()) != null) {
                if (line.length() > 0) {
                    {
                        String[] str = line.split(",");
                        String id = str[0];
                        String nameCar = str[1];
                        String image = str[3];
                        String price = str[4];

                        if ( !id.equalsIgnoreCase("id"))
                        {
                            MyVars.imgArr.add(image);

                        }

                        System.out.println(line);
                    }

                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    else
    {
    }

}
    //endregion

    //region check networstatus (wifi / mobile /no network)
    public static String checkNetworkStatus(final Context context) {

        String networkStatus = "";

        // Get connect mangaer
        final ConnectivityManager connMgr = (ConnectivityManager)
                context.getSystemService(Context.CONNECTIVITY_SERVICE);

        // check for wifi
        final android.net.NetworkInfo wifi = connMgr.getNetworkInfo(ConnectivityManager.TYPE_WIFI);

        // check for mobile data
        final android.net.NetworkInfo mobile = connMgr.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);

        if( wifi.isConnectedOrConnecting () ) {
            networkStatus = "wifi";
        } else if( mobile.isConnectedOrConnecting () ) {
            networkStatus = "mobileData";
        } else {
            networkStatus = "noNetwork";
        }

        return networkStatus;

    }  // end checkNetworkStatus
    //endregion
}
