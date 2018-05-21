package com.example.library;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class TAErrorHandler {
    public static @NonNull ErrorEnable mErrorEnable;
    public static @NonNull AlertType mAlertType;

    public static void AlertType(@NonNull AlertType alertType, @NonNull ErrorEnable errorEnable) {
        mAlertType = alertType;
        mErrorEnable = errorEnable;
    }

    public static void handler(@NonNull Activity activity, @NonNull String exceptionMsg) {
        if (mErrorEnable!=null && mErrorEnable.equals(ErrorEnable.ENABLE)) {
            if (mAlertType!=null && mAlertType.equals(AlertType.TOAST)) {
                Toast.makeText(activity, exceptionMsg, Toast.LENGTH_SHORT).show();
            } else if (mAlertType!=null && mAlertType.equals(AlertType.POP_UP)) {
                alertDialog(activity, exceptionMsg);
            }
        }
    }

    public static void handler(@NonNull View view, @NonNull String exceptionMsg) {
        if (mErrorEnable!=null && mErrorEnable.equals(ErrorEnable.ENABLE)) {
            if (mAlertType!=null && mAlertType.equals(AlertType.SNACKBAR)) {
                if (view != null) {
                    Snackbar.make(view, exceptionMsg, Snackbar.LENGTH_LONG).show();
                }
            }
        }
    }

    private static void alertDialog(Activity activity, String exceptionMsg) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setTitle(activity.getResources().getString(R.string.EXP_TITLE));
        builder.setMessage(exceptionMsg);
        builder.setCancelable(true);

        builder.setPositiveButton(
                activity.getResources().getString(R.string.OK),
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        builder.show();
    }


}
