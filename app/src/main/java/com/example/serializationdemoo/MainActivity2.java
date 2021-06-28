package com.example.serializationdemoo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity2 extends AppCompatActivity {
    TextView txt1,txt2,txt3,txt4,txt5;
    CircleImageView circleImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        txt1=findViewById(R.id.getname);
        txt2=findViewById(R.id.getemail);
        txt3=findViewById(R.id.getphone);
        txt4=findViewById(R.id.Addr);
        txt5=findViewById(R.id.getclg);
        circleImageView=findViewById(R.id.getpic);

        Userinfo userinfo=(Userinfo)getIntent().getSerializableExtra("Userinfo");

        byte[] bytes=userinfo.getProfile();
        Bitmap bitmap= BitmapFactory.decodeByteArray(bytes,0,bytes.length);
        String myname=userinfo.getName();
        String myemail=userinfo.getEmail();
        String myphone=userinfo.getContact();
        String myadd=userinfo.getAddress();
        String myclg=userinfo.getClgname();

        circleImageView.setImageBitmap(bitmap);
        txt1.setText(myname);
        txt2.setText(myemail);
        txt3.setText(myphone);
        txt4.setText(myadd);
        txt5.setText(myclg);

        txt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity2.this,"You Clicked on Mobile number",Toast.LENGTH_LONG).show();
                Intent calling=new Intent(Intent.ACTION_CALL);
                calling.setData(Uri.parse("tel:"+myphone));
                startActivity(calling);
            }
        });

        txt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity2.this,"You Clicked on Email",Toast.LENGTH_LONG).show();
                Intent intent=new Intent(Intent.ACTION_SEND);
                intent.putExtra(Intent.EXTRA_EMAIL,new String[]{txt2.getText().toString()});
                intent.setType("message/rfc822");
                startActivity(intent);
            }
        });

    }
}