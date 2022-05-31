package com.example.customelistview;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    ListView listView;

    String arrname[] = {"Alis", "Naina", "Bob", "Julia", "Marry", "Tony Stark", "Chris", "Tom Holland", "Thor", "Elivis", "Leon", "Captain America",
            "Dr Strange", "Ant Man", "Charles", "Hulk"};

    String arrmessage[] = {"Hi", "Hello", "How are You", "Hello,How are You", "I am fine", "hi,what about you", "your name is thor", "No ",
            "is you are a studen ", "yes i am ", "ok ", "Good night", "Good Morning", "Good AfterNoon", "hello, Sir", "Hi,Maam"};

    int arrimage[] = {R.drawable.a1, R.drawable.a2, R.drawable.a3, R.drawable.a4, R.drawable.a5, R.drawable.a10, R.drawable.a7, R.drawable.a8
            , R.drawable.a9, R.drawable.a6, R.drawable.a11, R.drawable.a12, R.drawable.a13, R.drawable.a14, R.drawable.a15, R.drawable.a16};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listview);
        myadapter adapter = new myadapter(this, arrname, arrmessage, arrimage);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent=new Intent(MainActivity.this,user_profile.class);
                intent.putExtra("name",arrname[position]);
                intent.putExtra("message",arrmessage[position]);
                intent.putExtra("image",arrimage[position]);
                startActivity(intent);

            }
        });
    }

    class myadapter extends ArrayAdapter<String> {

        Context context;
        String rname[];
        String rmsg[];
        int rimg[];

        public myadapter( Context context, String[] rname, String[] rmsg, int[] rimg) {
            super(context,R.layout.row,R.id.imageView,rname);

            this.rname = rname;
            this.rmsg = rmsg;
            this.rimg = rimg;
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

            LayoutInflater inflater=(LayoutInflater)getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View view=inflater.inflate(R.layout.row,parent,false);

            ImageView img=view.findViewById(R.id.imageView);
            TextView username=view.findViewById(R.id.name);
            TextView usermessage=view.findViewById(R.id.message);

            username.setText(rname[position]);
            usermessage.setText(rmsg[position]);
            img.setImageResource(rimg[position]);

            return view;
        }
    }

}