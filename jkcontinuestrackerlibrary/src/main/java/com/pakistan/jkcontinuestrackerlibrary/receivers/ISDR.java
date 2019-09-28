package com.pakistan.jkcontinuestrackerlibrary.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.util.Log;

import com.pakistan.jkcontinuestrackerlibrary.callbacks.InternetStateListener;
import com.pakistan.jkcontinuestrackerlibrary.utils.SystemUtils;


public class ISDR extends BroadcastReceiver {

    private static final String TAG = "InternetDetector";
    private Context mContext;
    private InternetStateListener mInternetStateListener;

    public ISDR(Context mContext, InternetStateListener listener) {
        this.mContext = mContext;
        this.mInternetStateListener = listener;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();

        if(action != null && action.equals(ConnectivityManager.CONNECTIVITY_ACTION)){
            if(mInternetStateListener != null){
                if(SystemUtils.isConnectedToNetwork(mContext)){
                    mInternetStateListener.onInternetConnected();
                }else
                    mInternetStateListener.onInternetDisconnected();
            }else {
                Log.d(TAG, "onInternetDetector: mInternetStateListener is NULL");
            }
        }else {
            mInternetStateListener.onInternetFailure("ConnectivityManager.CONNECTIVITY_ACTION is not set as action in Intent filter");
        }

    }

}
