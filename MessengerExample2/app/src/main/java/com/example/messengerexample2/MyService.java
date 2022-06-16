package com.example.messengerexample2;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;

import java.util.Locale;

public class MyService extends Service {
    public Messenger myMessengerIn = new Messenger(new IncomingHandler());
    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return myMessengerIn.getBinder();
    }
    class  IncomingHandler extends  Handler {
        @Override
        public void handleMessage(@NonNull Message msg) {
            Message MSG;
            super.handleMessage(msg);
            //Bundle bundle = msg.getData();
            MSG = Message.obtain();
            String message = msg.getData().getString("inputExtra");
            Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
            Log.i("REMote_TAG", "Received Data: " + message);
            Bundle bundle1 = new Bundle();
            bundle1.putString("MSG_KEY1", message.toUpperCase(Locale.ROOT));

            MSG.setData(bundle1);
            try {
                msg.replyTo.send(MSG);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}