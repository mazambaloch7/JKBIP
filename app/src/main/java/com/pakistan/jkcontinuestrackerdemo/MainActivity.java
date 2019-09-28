package com.pakistan.jkcontinuestrackerdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.bluetooth.BluetoothAdapter;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.telephony.AccessNetworkConstants;
import android.widget.TextView;

import com.pakistan.jkcontinuestrackerlibrary.callbacks.BluetoothStateListener;
import com.pakistan.jkcontinuestrackerlibrary.callbacks.InternetStateListener;
import com.pakistan.jkcontinuestrackerlibrary.receivers.BTSDR;
import com.pakistan.jkcontinuestrackerlibrary.receivers.ISDR;
import com.pakistan.jkcontinuestrackerlibrary.utils.ContextUtils;

public class MainActivity extends AppCompatActivity
        implements BluetoothStateListener, InternetStateListener {

    private BTSDR mBluetoothState;
    private ISDR mInternetState;

    private TextView mBluetooth, mInternet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initRef();
    }

    private void initView() {
        mBluetooth = findViewById(R.id.bluetooth_tv);
        mInternet = findViewById(R.id.internet_tv);
    }

    private void initRef() {
        mBluetoothState = new BTSDR(this, this);
        mInternetState = new ISDR(this, this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        registerReceiver(mBluetoothState, new IntentFilter(BluetoothAdapter.ACTION_STATE_CHANGED));
        registerReceiver(mInternetState, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
    }

    @Override
    protected void onStop() {
        super.onStop();
        unregisterReceiver(mBluetoothState);
        unregisterReceiver(mInternetState);
    }

    @Override
    public void onBluetoothOFF() {
        mBluetooth.setText("Bluetooth is OFF");
    }

    @Override
    public void onBluetoothON() {
        mBluetooth.setText("Bluetooth is ON");
    }

    @Override
    public void onBluetoothFailure() {
        mBluetooth.setText("Bluetooth Failure occurred");
    }

    @Override
    public void onInternetConnected() {
        mInternet.setText("Internet Connected");
    }

    @Override
    public void onInternetDisconnected() {
        mInternet.setText("Internet Disconnected");
    }

    @Override
    public void onInternetFailure(String error) {
        mInternet.setText("Internet Failure ERROR: "+error);
    }
}
