package com.example.serializationdemoo;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.io.ByteArrayOutputStream;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity {
    EditText edtname,edtmail,edtphone,edtaddress,edtclgname;
    CircleImageView circleImageView;
    int CAMERA_REQUEST_CODE=101;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edtname=findViewById(R.id.name);
        edtmail=findViewById(R.id.Email);
        edtphone=findViewById(R.id.phone);
        edtaddress=findViewById(R.id.Address);
        edtclgname=findViewById(R.id.clgname);
        circleImageView=findViewById(R.id.profile);

        circleImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCamera();
                Toast.makeText(MainActivity.this,"You Clicked on Camera",Toast.LENGTH_LONG).show();
            }
        });

    }
    void openCamera()
    {
        Intent intent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent,CAMERA_REQUEST_CODE);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        Bitmap imgdata=(Bitmap)data.getExtras().get("data");

        circleImageView.setImageBitmap(imgdata);
        //get data from
    }

    public byte[] convert_to_byteArray(ImageView imageView){
        //to get image
        Bitmap myimg=((BitmapDrawable)imageView.getDrawable()).getBitmap();

        //convert into byteArray
        ByteArrayOutputStream byteArrayOutputStream=new ByteArrayOutputStream();

        //compression,quality,where to store
        myimg.compress(Bitmap.CompressFormat.PNG,100,byteArrayOutputStream);

        byte[] bytearr=byteArrayOutputStream.toByteArray();
        return bytearr;
    }
    public void gotonext(View view) {

        Userinfo userinfo=new Userinfo();

        byte[] bytearray=new byte[0];
        try {
            bytearray=convert_to_byteArray(circleImageView);
        }
        catch (Exception e){

        }

        userinfo.setProfile(bytearray);
        userinfo.setName(edtname.getText().toString());
        userinfo.setEmail(edtmail.getText().toString());
        userinfo.setContact(edtphone.getText().toString());
        userinfo.setAddress(edtaddress.getText().toString());
        userinfo.setClgname(edtclgname.getText().toString());
        byte[] bytearry=convert_to_byteArray(circleImageView);

        userinfo.setProfile(bytearry);

        Intent intent=new Intent(MainActivity.this,MainActivity2.class);

        Bundle bundle=new Bundle();
        bundle.putSerializable("Userinfo",userinfo);
        intent.putExtras(bundle);
        startActivity(intent);



    }
}