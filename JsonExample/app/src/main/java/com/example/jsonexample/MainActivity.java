package com.example.jsonexample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String jsonStr = getListData();
        try{
            ArrayList<HashMap<String, String>> userList = new ArrayList<>();
            ListView lv = (ListView) findViewById(R.id.user_list);
            JSONObject jObj = new JSONObject(jsonStr);
            JSONArray jsonArry = jObj.getJSONArray("users");
            for(int i=0;i<jsonArry.length();i++){
                HashMap<String,String> user = new HashMap<>();
                JSONObject obj = jsonArry.getJSONObject(i);
                user.put("name",obj.getString("name"));
                user.put("designation",obj.getString("designation"));
                user.put("location",obj.getString("location"));
                userList.add(user);
            }
            ListAdapter adapter = new SimpleAdapter(MainActivity.this, userList, R.layout.list_row,new String[]{"name","designation","location"}, new int[]{R.id.name, R.id.designation, R.id.location});
            lv.setAdapter(adapter);
        }
        catch (JSONException ex){
            Log.e("JsonParser Example","unexpected JSON exception", ex);
        }
    }
    private String getListData() {
        String jsonStr = "{ \"users\" :[" +
                "{\"name\":\"Babu Badugu\",\"designation\":\"Team Leader\",\"location\":\"Hyderabad\"}" +
                ",{\"name\":\"Ram B\",\"designation\":\"Agricultural Officer\",\"location\":\"T.nasrapuram\"}" +
                ",{\"name\":\"Rambabu Badugu\",\"designation\":\"Charted Accountant\",\"location\":\"Gandigudem\"}] }";
        return jsonStr;
    }
}
