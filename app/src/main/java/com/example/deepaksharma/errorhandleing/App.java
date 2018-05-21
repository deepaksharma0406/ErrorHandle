package com.example.deepaksharma.errorhandleing;

import android.app.Application;

import com.example.library.AlertType;
import com.example.library.ErrorEnable;
import com.example.library.TAErrorHandler;

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        TAErrorHandler.AlertType(AlertType.POP_UP, ErrorEnable.ENABLE);
    }
}
