package com.jarvis.flash.armoury;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;

public class Armoury {

    public static String TAG = Armoury.class.getName();
    public static String ERROR_MESSAGE = "Some error occured , please visit in some time!";

    private static ProgressDialog sProgressDialog;

    public static void showProgressDialog(Context context) {
        if ((context instanceof AppCompatActivity || context instanceof Activity)) {
            if (sProgressDialog == null) {
                sProgressDialog = new ProgressDialog(context);
                sProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                sProgressDialog.setCancelable(false);
            }
            sProgressDialog.setMessage("Loading...");
            if (context instanceof AppCompatActivity) {
                if (!((Activity) context).isFinishing())
                    sProgressDialog.show();
            }
        }
    }

    public static void hideProgressDialog() {
        try {
            if ((sProgressDialog != null) && sProgressDialog.isShowing())
                sProgressDialog.dismiss();
            sProgressDialog = null;
        } catch (Throwable ignored) {
        }
    }

}
