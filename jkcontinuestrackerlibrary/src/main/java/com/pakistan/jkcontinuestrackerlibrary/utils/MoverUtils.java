package com.pakistan.jkcontinuestrackerlibrary.utils;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import java.io.Serializable;

public class MoverUtils {

    private MoverUtils(){}

    public static void moveTo(Context fromContext, Class toContext){
        Intent intent = new Intent(fromContext, toContext);
        if(intent.resolveActivity(fromContext.getPackageManager()) != null)
            fromContext.startActivity(intent);
    }

    public static void moveTo(Context fromContext, Class toContext, String bundleKey, String dataKey, Object data){
        Intent intent = new Intent(fromContext, toContext);
        Bundle bundle = new Bundle();
        bundle.putSerializable(dataKey, (Serializable) data);
        intent.putExtra(bundleKey, bundle);
        if(intent.resolveActivity(fromContext.getPackageManager()) != null)
            fromContext.startActivity(intent);
    }

}
