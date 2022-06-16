package com.example.aidldemo1;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

public class AdditionService extends Service {
    private static final String TAG = "AdditionService";
    public AdditionService(){

    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate()");
    }

    @Override
    public IBinder onBind(Intent intent) {

        return iAdditionService.asBinder();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy()");
    }

    IAdditionService.Stub iAdditionService = new IAdditionService.Stub() {
        /**
         * Implementation of the add() method
         */
        public int add(int value1, int value2) throws RemoteException {
            Log.d(TAG, String.format("AdditionService.add(%d, %d)", value1, value2));
            return value1 + value2;
        }

    };
}
