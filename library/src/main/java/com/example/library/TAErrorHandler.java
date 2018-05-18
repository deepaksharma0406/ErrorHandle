package com.example.library;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.Toast;

public class TAErrorHandler {
   
    public static void AlertType(@NonNull Activity activity,@NonNull String strAlertType, @NonNull String exceptionMsg, @NonNull View view) {
        if (strAlertType.equals(activity.getResources().getString(R.string.TOAST))) {
            Toast.makeText(activity, exceptionMsg, Toast.LENGTH_SHORT).show();
        } else if (strAlertType.equals(activity.getResources().getString(R.string.POP_UP))) {
            alertDialog(activity, exceptionMsg);
        } else if (strAlertType.equals(activity.getResources().getString(R.string.SNACKBAR))) {
            if (view != null) {
                Snackbar.make(view, exceptionMsg, Snackbar.LENGTH_LONG).show();
            } else {
                Toast.makeText(activity, activity.getResources().getString(R.string.SNACKBAR_ERROR), Toast.LENGTH_SHORT).show();
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
