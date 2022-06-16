package com.example.customelistview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class user_profile extends AppCompatActivity {
    ImageView img;
    TextView uname , message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
            img=findViewById(R.id.profileimage);
            uname=findViewById(R.id.username);
            message=findViewById(R.id.usermessage);

            Intent intent=this.getIntent();

            String username=intent.getStringExtra("name");
            String usermessage=intent.getStringExtra("message");
            int imageid=intent.getIntExtra("image",R.drawable.a1);

            uname.setText(username);
            message.setText(usermessage);
            img.setImageResource(imageid);


        }
    }
