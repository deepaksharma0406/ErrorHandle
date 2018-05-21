package com.example.library;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;

public class Permissions extends Activity {
    private static final int MY_PERMISSIONS_REQUEST = 1;
    static String[] permissions = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE};
    static Activity mActivity;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        checkPermission(Permissions.this);


    }
    public static void checkPermission(Activity context) {
        mActivity = context;
        if (Build.VERSION.SDK_INT >= 23)
            permissions();
//            mPermissionListener.onAccept();
    }

    public static void permissions() {
        if (ActivityCompat.checkSelfPermission(mActivity, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            // Check Permissions Now
            ActivityCompat.requestPermissions(mActivity,
                    permissions,
                    MY_PERMISSIONS_REQUEST);
        } else {
//            mPermissionListener.onAccept();
//            tabViewModel.setupViewPager(getSupportFragmentManager(), binding.viewpager);
        }
    }

    public void onRequestPermissionsResult(int requestCode, final String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                } else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(Permissions.this);
                    builder.setTitle("Need Permission");
                    builder.setMessage("This app needs permission");
                    builder.setCancelable(false);
                    builder.setPositiveButton("Grant", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                            ActivityCompat.requestPermissions(Permissions.this, permissions, MY_PERMISSIONS_REQUEST);
                        }
                    });
                    builder.show();
                }
                return;
            }
        }
    }
}
