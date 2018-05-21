package com.example.deepaksharma.errorhandleing;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.example.library.TAErrorHandler;

public class MainActivity extends AppCompatActivity {
    LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        linearLayout = (LinearLayout) findViewById(R.id.linearLayout);
    }

    public void expHandleToast(View v) {
        TAErrorHandler.handler(MainActivity.this,  "Error");
    }

    public void expHandleSnackbar(View v) {
        TAErrorHandler.handler(linearLayout,  "Error");
    }


}
