package com.example.messengerexample2;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

public class MyService extends Service {

   IncomingHandler incomingHandler = new IncomingHandler();
    OutgoingHandler outgoingHandler = new OutgoingHandler();
    public Messenger myMessengerIn = new Messenger(incomingHandler);
    public Messenger myMessengerOut = new Messenger(outgoingHandler);
    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return myMessengerOut.getBinder();
    }

    class  IncomingHandler extends  Handler{
        @Override
        public void handleMessage(@NonNull Message msg) {
            Message MSG;
            super.handleMessage(msg);
            Bundle bundle = msg.getData();
             String message = bundle.getString("MSG_KEY");
           // String message = "ram are you okay";
            Toast.makeText(getApplicationContext(),message,Toast.LENGTH_LONG).show();
            Log.i("REMote_TAG","Received Data: "+message);


        }
    }
    class OutgoingHandler extends Handler{
        @Override
        public void handleMessage(@NonNull Message msg) {
            Message MSG;
            super.handleMessage(msg);
            String message = "messege from service";
            Toast.makeText(getApplicationContext(),message,Toast.LENGTH_LONG).show();
            Log.i("REMote_TAG","Received Data: "+message);
            Bundle bundle1 = new Bundle();
            bundle1.putString("MSG_KEY1",message);
            MSG= Message.obtain();
            MSG.setData(bundle1);
            try {
                msg.replyTo.send(MSG);
            }catch (Exception e)
            {
                e.printStackTrace();
            }

        }
    }
}