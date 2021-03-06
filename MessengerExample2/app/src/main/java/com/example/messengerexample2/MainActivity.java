package com.example.messengerexample2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Messenger myService=null;
    Messenger mClient;
    Boolean isConnected=false;
    TextView textView;
    EditText editText;//= (EditText) findViewById(R.id.editText);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = new Intent(getApplicationContext(),MyService.class);
        bindService(intent,connection,Context.BIND_AUTO_CREATE);
        textView=(TextView) findViewById(R.id.textView);

    }

    public void sendMessage(View view)
    {
        EditText editText= (EditText) findViewById(R.id.editText);
        Bundle bundle= new Bundle();
        String message = editText.getText().toString();
        bundle.putString("inputExtra",message);
       // bundle.putString("inputExtra",mess);
        Message msg = Message.obtain();
        msg.replyTo=new Messenger(new ResponceHandler());
        msg.setData(bundle);
        Log.i("Remote_Tag","messae sent"+msg);
        try {

            myService.send(msg);
        }
        catch (Exception e) {
            e.printStackTrace();
        }

    }

    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            myService=new Messenger(iBinder);
            mClient=new Messenger(new ResponceHandler());
            isConnected=true;
        }
        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            isConnected=false;
        }
    };
    @Override
    protected void onStop() {
        super.onStop();
        unbindService(connection);
        myService=null;
        isConnected=false;
    }

    class ResponceHandler extends Handler{
        @Override
        public void handleMessage(@NonNull Message msg) {
            String message;
            message = msg.getData().getString("MSG_KEY1");
            Toast.makeText(getApplicationContext(),message,Toast.LENGTH_LONG).show();
            Log.i("Client","Received Data: from Service "+message);
            textView.setText("message from"+message);
            super.handleMessage(msg);
        }
    }
}