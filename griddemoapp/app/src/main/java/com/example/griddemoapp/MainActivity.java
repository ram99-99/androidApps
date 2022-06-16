package com.example.griddemoapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

public class MainActivity extends AppCompatActivity
{
  GridView grid;

    int icons[]=
            {
                    R.drawable.a,
                    R.drawable.b,
                    R.drawable.c,
                    R.drawable.d,
                    R.drawable.e,
                    R.drawable.f,
                    R.drawable.g,
                    R.drawable.h,
                    R.drawable.i
            };
    String name[]={"HTML","JAVA","JSP","PHP","PYTHON","SQL","ANDROID","ANGULAR","C++"};


    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("GridView Demo");

        grid=(GridView)findViewById(R.id.datagrid);
        gridAdapter obj=new gridAdapter(this,name,icons);
        grid.setAdapter(obj);



    }
}
