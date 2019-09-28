package com.pakistan.jkcontinuestrackerlibrary.utils;

import android.content.Context;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;


public abstract class ContextUtils {

    private ContextUtils() throws IllegalAccessException {
        throw new IllegalAccessException("Cannot instantiate Abstract Class");
    }

    public static void toast(Context context, String msg){
        Toast.makeText(context, msg, Toast.LENGTH_LONG).show();
    }

    public static void setUpToolbar(AppCompatActivity context, Toolbar toolbar, String title, boolean goBack) {
        context.setSupportActionBar(toolbar);

        if(context.getSupportActionBar() != null){
            context.getSupportActionBar().setTitle(title);

            if(goBack){
                context.getSupportActionBar().setHomeButtonEnabled(true);
                context.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            }

        }

    }

}
