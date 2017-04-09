package com.lejeune.david.fahrzeugewahler;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class ShowActivity extends AppCompatActivity {

    private ImageView imgProfileShow, imgCarShow, imgOption0Show, imgOption1Show;
    private TextView txtUsernameShow, txtPriceShow, txtCarNameShow;

    Context cntx;
    MyTools myTools;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);


        //region init
        //Setting the background color to white
        getWindow().getDecorView().setBackgroundColor(Color.WHITE);

        //initializations
        cntx = getApplicationContext();
        myTools = new MyTools();
        myTools.retrieveSharedPref(cntx);

        //displaying username
        txtUsernameShow = (TextView) findViewById(R.id.txtUsernameShow);
        txtUsernameShow.setText(MyVars.username);

        txtPriceShow = (TextView) findViewById(R.id.txtPriceShow);
        txtPriceShow.setText(MyVars.fullprice);

        txtCarNameShow = (TextView) findViewById(R.id.txtCarNameShow);
        String nameCar = MyTools.queryValueCars("car_types", MyVars.car, 1);
        System.out.println("MyVars.car : " + MyVars.car);
        System.out.println("nameCar : " + nameCar);
        txtCarNameShow.setText(nameCar);

        //setting profile image
        imgProfileShow = (ImageView) findViewById(R.id.imgProfileShow);
        setProfileImage();

        intOptions();
        setImgCarShow();

        //endregion

    }


    private void intOptions(){
        imgOption0Show = (ImageView) findViewById(R.id.imgOption0Show);
        imgOption1Show = (ImageView) findViewById(R.id.imgOption1Show);
        if (!MyVars.option0.equalsIgnoreCase("true")) imgOption0Show.setVisibility(View.INVISIBLE);
        if (!MyVars.option1.equalsIgnoreCase("true")) imgOption1Show.setVisibility(View.INVISIBLE);

    }

    private void setImgCarShow() {
        imgCarShow = (ImageView) findViewById(R.id.imgCarShow);
        Context context = imgCarShow.getContext();
        int id = context.getResources().getIdentifier(MyVars.car, "drawable", context.getPackageName());
        imgCarShow.setImageResource(id);
    }
    private void setProfileImage(){
        switch (MyVars.avatar) {
            case "0":
                imgProfileShow.setImageResource(R.drawable.avatar0);
                break;
            case "1":
                imgProfileShow.setImageResource(R.drawable.avatar1);
                break;
            case "2":
                imgProfileShow.setImageResource(R.drawable.avatar2);
                break;
            case "3":
                imgProfileShow.setImageResource(R.drawable.avatar3);
                break;
            case "4":
                imgProfileShow.setImageResource(R.drawable.avatar4);
                break;
            case "5":
                imgProfileShow.setImageResource(R.drawable.avatar5);
                break;
            case "6":
                imgProfileShow.setImageResource(R.drawable.avatar6);
                break;
            case "7":
                imgProfileShow.setImageResource(R.drawable.avatar7);
                break;
            default:
                imgProfileShow.setImageResource(R.drawable.avatar00);
                break;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent newIntent = new Intent(ShowActivity.this, MenuActivity.class);
        finish();
        startActivity(newIntent);
    }
}
