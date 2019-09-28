package com.pakistan.jkcontinuestrackerlibrary.callbacks;

public interface InternetStateListener {
    void onInternetConnected();
    void onInternetDisconnected();
    void onInternetFailure(String error);
}
