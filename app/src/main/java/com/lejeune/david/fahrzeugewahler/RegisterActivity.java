package com.lejeune.david.fahrzeugewahler;

import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;

public class RegisterActivity extends AppCompatActivity {

    private GridView gridView;
    private GridViewAdapter gridAdapter;

    private ImageView imgProfileRegister;
    private EditText txtUsernameRegister;

    Context cntx;
    MyTools myTools;

    private int intAvatar=666;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //region init
        //Setting the background color to white
        getWindow().getDecorView().setBackgroundColor(Color.WHITE);

        imgProfileRegister = (ImageView) findViewById(R.id.imgProfileRegister);
        txtUsernameRegister = (EditText) findViewById(R.id.txtUsernameRegister) ;

        cntx = getApplicationContext();

        myTools= new MyTools();
        myTools.retrieveSharedPref(cntx);
        txtUsernameRegister.setText(MyVars.username);
        setProfileImage();

        System.out.println("username : " + MyVars.username);
        System.out.println("avatar : " + MyVars.avatar);
        //endregion

        //region gridView
        gridView = (GridView) findViewById(R.id.gridView);
        gridAdapter = new GridViewAdapter(this, R.layout.grid_item_layout, getData());
        gridView.setAdapter(gridAdapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                System.out.println("position : "+ position);
                intAvatar = position;
                MyVars.avatar = intAvatar + "";
                setProfileImage();
            }
        });
        //endregion

        //region imgRegister action
        ImageView imgRegister = (ImageView) findViewById(R.id.imgRegister);
        imgRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUser();
            }
        });
        //endregion


    }

    private void registerUser(){
        String username = txtUsernameRegister.getText().toString();
        //first check if a username is present
        if (username.length()>1)
        {
            SharedPrefHelper.getInstance().save(RegisterActivity.this,username,"username");

            //next we need an avatar image
            if (intAvatar<10)
            {
                SharedPrefHelper.getInstance().save(RegisterActivity.this,"" + intAvatar ,"avatar");
                gotoMenu();
            }
            else
            {
                Toast.makeText(RegisterActivity.this,
                        "Please choose an avatar",
                        Toast.LENGTH_LONG).show();
            }
        }
        else
        {
            Toast.makeText(RegisterActivity.this,
                    "You need to enter a name",
                    Toast.LENGTH_LONG).show();
        }
    }

    private ArrayList<ImageItem> getData() {
        final ArrayList<ImageItem> imageItems = new ArrayList<>();
        TypedArray imgs = getResources().obtainTypedArray(R.array.image_ids);
        for (int i = 0; i < imgs.length(); i++) {
            Bitmap bitmap = BitmapFactory.decodeResource(getResources(), imgs.getResourceId(i, -1));
            imageItems.add(new ImageItem(bitmap, ""));
        }
        return imageItems;
    }

    private void gotoMenu(){
        Toast toast = Toast.makeText(RegisterActivity.this,
                    "Welcome " + txtUsernameRegister.getText().toString() + " to the ZORA family", Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();

        final Intent menuIntent = new Intent(RegisterActivity.this, MenuActivity.class);
        RegisterActivity.this.startActivity(menuIntent);
        RegisterActivity.this.finish();
    }

    private void setProfileImage(){
        switch (MyVars.avatar) {

            case "0":
                imgProfileRegister.setImageResource(R.drawable.avatar0);
                break;
            case "1":
                imgProfileRegister.setImageResource(R.drawable.avatar1);
                break;
            case "2":
                imgProfileRegister.setImageResource(R.drawable.avatar2);
                break;
            case "3":
                imgProfileRegister.setImageResource(R.drawable.avatar3);
                break;
            case "4":
                imgProfileRegister.setImageResource(R.drawable.avatar4);
                break;
            case "5":
                imgProfileRegister.setImageResource(R.drawable.avatar5);
                break;
            case "6":
                imgProfileRegister.setImageResource(R.drawable.avatar6);
                break;
            case "7":
                imgProfileRegister.setImageResource(R.drawable.avatar7);
                break;
            default:
                imgProfileRegister.setImageResource(R.drawable.avatar00);
                break;
        }
    }

}
