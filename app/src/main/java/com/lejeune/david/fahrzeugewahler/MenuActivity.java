package com.lejeune.david.fahrzeugewahler;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MenuActivity extends AppCompatActivity {

    private ImageView imgProfileMenu;
    private TextView txtUsernameMenu;
    private TextView txtInfoMenu;

    private ImageView imgSelectVehicle;

    Context cntx;
    MyTools myTools;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        //region init
        //Setting the background color to white
        getWindow().getDecorView().setBackgroundColor(Color.WHITE);

        //initializations
        cntx = getApplicationContext();
        myTools= new MyTools();
        myTools.retrieveSharedPref(cntx);

        //setting info text
        txtInfoMenu = (TextView) findViewById(R.id.txtInfoMenu);
        txtInfoMenu.setText("The name of the game is Fahrzeuge Wähler .\n\n"+
                            "You can choose your own amazing vehicle AND even customize it..\n\n" +
                            "Have fun !!!");

        //displaying username
        txtUsernameMenu = (TextView) findViewById(R.id.txtUsernameMenu);
        txtUsernameMenu.setText(MyVars.username);

        //setting profile image
        imgProfileMenu = (ImageView) findViewById(R.id.imgProfileMenu);
        setProfileImage();

        //endregion

        //region imgSelectVehicle action
        imgSelectVehicle = (ImageView) findViewById(R.id.imgSelectVehicle);
        imgSelectVehicle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent newIntent = new Intent(MenuActivity.this, ShowActivity.class);
                finish();
                startActivity(newIntent);
            }
        });
        if (MyVars.car.equalsIgnoreCase("")) imgSelectVehicle.setVisibility(View.INVISIBLE);
        //endregion

        //region imgCreateVehicle action
        ImageView imgCreateVehicle = (ImageView) findViewById(R.id.imgCreateVehicle);
        imgCreateVehicle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent newIntent = new Intent(MenuActivity.this, CreateActivity.class);
                finish();
                startActivity(newIntent);

            }
        });
        //endregion

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        MyTools.deleteFolders();
    }

    private void setProfileImage(){
        switch (MyVars.avatar) {
            case "0":
                imgProfileMenu.setImageResource(R.drawable.avatar0);
                break;
            case "1":
                imgProfileMenu.setImageResource(R.drawable.avatar1);
                break;
            case "2":
                imgProfileMenu.setImageResource(R.drawable.avatar2);
                break;
            case "3":
                imgProfileMenu.setImageResource(R.drawable.avatar3);
                break;
            case "4":
                imgProfileMenu.setImageResource(R.drawable.avatar4);
                break;
            case "5":
                imgProfileMenu.setImageResource(R.drawable.avatar5);
                break;
            case "6":
                imgProfileMenu.setImageResource(R.drawable.avatar6);
                break;
            case "7":
                imgProfileMenu.setImageResource(R.drawable.avatar7);
                break;
            default:
                imgProfileMenu.setImageResource(R.drawable.avatar00);
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        myTools.retrieveSharedPref(cntx);
        if (MyVars.car.equalsIgnoreCase("")) imgSelectVehicle.setVisibility(View.INVISIBLE);
    }

    @Override
    protected void onStart() {
        super.onStart();
        myTools.retrieveSharedPref(cntx);
        if (MyVars.car.equalsIgnoreCase("")) imgSelectVehicle.setVisibility(View.INVISIBLE);
    }
}
