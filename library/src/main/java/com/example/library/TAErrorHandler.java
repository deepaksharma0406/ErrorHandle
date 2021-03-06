package com.example.library;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.Toast;

import com.example.library.Enum.AlertType;
import com.example.library.FileError.FileWrite;
import com.example.library.interfaces.PermissionListener;
import com.example.library.permission.PermissionActivity;

import java.text.SimpleDateFormat;

import static com.example.library.FileError.FileWrite.writeFile;

public class TAErrorHandler implements PermissionListener {
//    public static @NonNull
//    ErrorEnable mErrorEnable;
    public static @NonNull
    AlertType mAlertType;
    public static @NonNull
    Context appContext;
    public static String strException = null;

    public static void AlertType(@NonNull AlertType alertType) {//, @NonNull ErrorEnable errorEnable) {
        mAlertType = alertType;
//        mErrorEnable = errorEnable;
    }

    //    Toast & PopUp Type Alert
    public static void handler(@NonNull Context context, @NonNull Exception exception) {
        String expMsg = exception.getMessage();
        if (context != null && appContext != null) {
//        if (mErrorEnable != null && mErrorEnable.equals(ErrorEnable.ENABLE)) {
            if (mAlertType != null && mAlertType.equals(AlertType.TOAST)) {
                Toast.makeText(context, expMsg, Toast.LENGTH_SHORT).show();
            } else if (mAlertType != null && mAlertType.equals(AlertType.POP_UP)) {
                alertDialog(context, expMsg);
            }
            writeFile(context, expMsg);
        }
    }

    //      SNACKBAR Alert
    public static void handler(@NonNull View view, @NonNull Exception exception) {
        String expMsg = exception.getMessage();
        if (appContext != null) {
//        if (mErrorEnable != null && mErrorEnable.equals(ErrorEnable.ENABLE)) {
            if (mAlertType != null && mAlertType.equals(AlertType.SNACKBAR)) {
                if (view != null) {
//                    logToFile(exceptionMsg);
                    FileWrite.writeFile(appContext, expMsg);
                    Snackbar.make(view, expMsg, Snackbar.LENGTH_LONG).show();
                }
            }
        }
    }

    //      POP_UP Alert Dialog
    private static void alertDialog(Context context, String exceptionMsg) {
        AlertDialog builder = new AlertDialog.Builder(context).create();
        builder.setTitle(context.getResources().getString(R.string.EXP_TITLE));
        builder.setMessage(exceptionMsg);
        builder.setCancelable(true);
        builder.setButton(
                context.getResources().getString(R.string.OK),
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        builder.show();
    }

    public void permissionInit() {
        PermissionActivity.checkListener(this);
    }

    @Override
    public void onAccepted() {
        if(strException != null)
            FileWrite.logFile(appContext, strException);
    }

    @Override
    public void onDenied() {

    }

//    module initialize & check permission.
    public static void init(Context context) {
        appContext = context;
        if(appContext!=null && Build.VERSION.SDK_INT >= 23){
            Intent intent = new Intent(context, PermissionActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
            new TAErrorHandler().permissionInit();
        }

    }
}
