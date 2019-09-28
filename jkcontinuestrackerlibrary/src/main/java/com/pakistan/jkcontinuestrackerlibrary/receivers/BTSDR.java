package com.pakistan.jkcontinuestrackerlibrary.receivers;

import android.bluetooth.BluetoothAdapter;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.pakistan.jkcontinuestrackerlibrary.callbacks.BluetoothStateListener;


public class BTSDR extends BroadcastReceiver {

    private static final String TAG = "BluetoothDetector";
    private Context mContext;
    private BluetoothStateListener mBluetoothStateListener;

    public BTSDR(Context mContext, BluetoothStateListener listener) {
        this.mContext = mContext;
        this.mBluetoothStateListener = listener;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();

        if(action != null){

            if(action.equals(BluetoothAdapter.ACTION_STATE_CHANGED)){
                final int state = intent.getIntExtra(BluetoothAdapter.EXTRA_STATE, BluetoothAdapter.ERROR);

                switch(state){
                    case BluetoothAdapter.STATE_OFF:{
                        if(mBluetoothStateListener != null)
                            mBluetoothStateListener.onBluetoothOFF();
                        else
                            Log.d(TAG, "onBluetoothStateOFF: Listener Reference is NULL.");
                        break;
                    }
                    case BluetoothAdapter.STATE_ON:{
                        if(mBluetoothStateListener != null)
                            mBluetoothStateListener.onBluetoothON();
                        else
                            Log.d(TAG, "onBluetoothStateON: Listener Reference is NULL.");
                        break;
                    }
                    default: {
                        break;
                    }

                }
            }
        }else
            mBluetoothStateListener.onBluetoothFailure();

    }
}
