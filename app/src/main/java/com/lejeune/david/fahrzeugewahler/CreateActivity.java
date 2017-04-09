package com.lejeune.david.fahrzeugewahler;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class CreateActivity extends AppCompatActivity {

    private ImageView imgProfileCreate , imgCar , imgOption0 , imgOption1;
    private TextView txtUsernameCreate , txtPrice , txtCarName;

    private String price;

    private int indexImage , sizeArr , carPrice;

    Context cntx;
    MyTools myTools;

    private boolean boolOp0 = false;
    private boolean boolOp1 = false;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);

        //region init
        //Setting the background color to white
        getWindow().getDecorView().setBackgroundColor(Color.WHITE);

        //initializations
        cntx = getApplicationContext();
        myTools= new MyTools();
        myTools.retrieveSharedPref(cntx);

        //displaying username
        txtUsernameCreate = (TextView) findViewById(R.id.txtUsernameCreate);
        txtUsernameCreate.setText(MyVars.username);

        txtPrice = (TextView) findViewById(R.id.txtPrice);
        txtCarName = (TextView) findViewById(R.id.txtCarName);

        //setting profile image
        imgProfileCreate = (ImageView) findViewById(R.id.imgProfileCreate);
        setProfileImage();

        intOption();


        //endregion

        //region Create car images list
        MyTools.createArrayListAvailableCars();
        indexImage = 0;
        sizeArr = MyVars.imgArr.size();
        System.out.println("size : " + sizeArr);

        //setting first car image
        imgCar = (ImageView) findViewById(R.id.imgCar);
        // show me the first value
        //System.out.println("first value : " + MyVars.imgArr.get(0));

        setCar();

        //endregion

        //region imgRight action
        ImageView imgRight = (ImageView) findViewById(R.id.imgRight);
        imgRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                goRightCar();
            }
        });
        //endregion

        //region imgLeft action
        ImageView imgLeft = (ImageView) findViewById(R.id.imgLeft);
        imgLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                goLeftCar();

            }
        });
        //endregion

        //region imgBuy action
        ImageView imgBuy = (ImageView) findViewById(R.id.imgBuy);
        imgBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                buyCar();
            }
        });
        //endregion

    }

    private void setProfileImage(){
        switch (MyVars.avatar) {
            case "0":
                imgProfileCreate.setImageResource(R.drawable.avatar0);
                break;
            case "1":
                imgProfileCreate.setImageResource(R.drawable.avatar1);
                break;
            case "2":
                imgProfileCreate.setImageResource(R.drawable.avatar2);
                break;
            case "3":
                imgProfileCreate.setImageResource(R.drawable.avatar3);
                break;
            case "4":
                imgProfileCreate.setImageResource(R.drawable.avatar4);
                break;
            case "5":
                imgProfileCreate.setImageResource(R.drawable.avatar5);
                break;
            case "6":
                imgProfileCreate.setImageResource(R.drawable.avatar6);
                break;
            case "7":
                imgProfileCreate.setImageResource(R.drawable.avatar7);
                break;
            default:
                imgProfileCreate.setImageResource(R.drawable.avatar00);
                break;
        }
    }

    private void resetBools(){
        boolOp0 = false;
        boolOp1 = false;
    }

    private void intOption(){
        imgOption0 = (ImageView) findViewById(R.id.imgOption0);
        imgOption1 = (ImageView) findViewById(R.id.imgOption1);
        imgOption0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleOption0();
            }
        });
        imgOption1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleOption1();
            }
        });

    }
    private void handleOption0(){
        boolOp0 = !boolOp0;
        System.out.println("0 : " + boolOp0);
        handleOptions();
    }
    private void handleOption1(){
        boolOp1 = !boolOp1;
        System.out.println("1 : " + boolOp1);
        handleOptions();
    }
    private void handleOptions(){
        setColorOptions();
        getTotal();
    }

    private void goLeftCar(){
        if (indexImage > 0) indexImage -= 1;
        setCar();
    }
    private void goRightCar(){
        if (indexImage < (sizeArr - 1)) indexImage += 1;
        setCar();
    }

    private void setCar(){
        resetBools();
        resettingPrice();
        setCarImage(indexImage);
        getPriceCar();
        getCarName();
        setOptionsForCar();
        setColorOptions();
    }

    private void resettingPrice(){
        price = "0 $";
        txtPrice.setText(price);
    }
    private void setCarImage(int index){
        String picture = MyVars.imgArr.get(index);
        Context context = imgCar.getContext();
        int id = context.getResources().getIdentifier(picture, "drawable", context.getPackageName());
        imgCar.setImageResource(id);
    }
    private void getPriceCar()  {
        String picture = MyVars.imgArr.get(indexImage);
        String priceCar = MyTools.queryValueCars("car_types" , picture , 4);
        System.out.println("price : " + priceCar);
        price = txtPrice.getText().toString();
        price = price.replace(" $", "");
        carPrice = Integer.parseInt(priceCar);
        int totalPrice = Integer.parseInt(priceCar) + Integer.parseInt(price);
        txtPrice.setText(totalPrice + " $");
    }
    private void getCarName(){
        String picture = MyVars.imgArr.get(indexImage);
        String nameCar = MyTools.queryValueCars("car_types" , picture , 1);
        System.out.println("nameCar : " + nameCar);
        txtCarName.setText(nameCar);
    }
    private void setOptionsForCar(){
        MyVars.boolOption0 = false;
        MyVars.boolOption1 = false;

        String car = MyVars.imgArr.get(indexImage);
        System.out.println("car : " + car);
        MyTools.queryValueOptions("options_" + car);
        System.out.println(MyVars.boolOption0);
        System.out.println(MyVars.boolOption1);
    }
    private void setColorOptions(){
        if (!MyVars.boolOption0) {
            imgOption0.setBackgroundColor(Color.RED);
        }
        else {
            imgOption0.setBackgroundColor(Color.WHITE);
            if (boolOp0)
            {
                imgOption0.setBackgroundColor(Color.YELLOW);
            }
        }

        if (!MyVars.boolOption1) {
            imgOption1.setBackgroundColor(Color.RED);
        }
        else {
            imgOption1.setBackgroundColor(Color.WHITE);
            if (boolOp1)
            {
                imgOption1.setBackgroundColor(Color.YELLOW);
            }
        }
    }
    private void getTotal()  {

        //easy way to calc prices : just the price of the actual car added with selected items
        int totalPrice = carPrice;

        // getting the prices
        MyVars.priceOp0 = "0";
        MyVars.priceOp1 = "0" ;
        MyTools.queryPriceOption("options");

        System.out.println("getting the prices : " + MyVars.priceOp0);
        if (MyVars.boolOption0) {
            if (boolOp0)
            {
                int priceOption0 = Integer.parseInt(MyVars.priceOp0);
                totalPrice = totalPrice + priceOption0;
            }
        }

        if (MyVars.boolOption1) {
            if (boolOp1)
            {
                int priceOption1 = Integer.parseInt(MyVars.priceOp1);
                totalPrice = totalPrice + priceOption1;

            }
        }
        txtPrice.setText(totalPrice + " $");
    }

    private void buyCar(){
        System.out.println("buy the car");

        // save the values for car, option0 and option1 to shared pref
        String picture = MyVars.imgArr.get(indexImage);
        System.out.println("picture " + picture);
        SharedPrefHelper.getInstance().save(CreateActivity.this,"" + picture ,"car");
        SharedPrefHelper.getInstance().save(CreateActivity.this,"" + txtCarName.getText().toString() ,"carname");
        SharedPrefHelper.getInstance().save(CreateActivity.this,"" + txtPrice.getText().toString() ,"fullprice");

        if (MyVars.boolOption0) {
            if (boolOp0)
            {
                SharedPrefHelper.getInstance().save(CreateActivity.this,"true" ,"option0");
            }
            else
            {
                SharedPrefHelper.getInstance().save(CreateActivity.this,"false" ,"option0");
            }
        }
        else
        {
            SharedPrefHelper.getInstance().save(CreateActivity.this,"false" ,"option0");
        }

        if (MyVars.boolOption1) {
            if (boolOp1)
            {
                SharedPrefHelper.getInstance().save(CreateActivity.this,"true"  ,"option1");
            }
            else
            {
                SharedPrefHelper.getInstance().save(CreateActivity.this,"false" ,"option1");
            }
        }
        else
        {
            SharedPrefHelper.getInstance().save(CreateActivity.this,"false" ,"option1");
        }
        gotoCongratulations();
    }

    public void gotoCongratulations(){
        Intent newIntent = new Intent(CreateActivity.this, CongratsActivity.class);
        finish();
        startActivity(newIntent);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent newIntent = new Intent(CreateActivity.this, MenuActivity.class);
        finish();
        startActivity(newIntent);
    }
}
