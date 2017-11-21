package com.playlistapp.utils;


import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.playlistapp.R;

import java.util.regex.Pattern;

/**
 * Utils class for common methods of application.
 */
public final class CommonUtils {

    private static final String EMAIL_PATTERN = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])";

    private CommonUtils() {
        // The utility class is not publicly instantiable
    }

    public static ProgressDialog showLoadingDialog(Context context) {
        ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.show();
        if (progressDialog.getWindow() != null) {
            progressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }
        progressDialog.setContentView(R.layout.dialog_progress);
        progressDialog.setIndeterminate(true);
        progressDialog.setCancelable(false);
        progressDialog.setCanceledOnTouchOutside(false);
        return progressDialog;
    }

    /**
     * Method for checking email validation.
     */
    public static boolean validateEmail(CharSequence email) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    /**
     * Method for checking email validation with custom pattern.
     */
    public static boolean validateEmailPattern(CharSequence email) {
        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        return pattern.matcher(email).matches();
    }

    /**
     * Method for checking password validation.
     */
    public static boolean validatePassword(String password) {
        return password.length() > 5;
    }

    /**
     * Method for checking password confirmation.
     */
    public static boolean validatePasswordConfirmation(String password, String confirmPwd) {
        return password.equals(confirmPwd);
    }

    /**
     * Method for showing toast.
     */
    public static void toast(Activity context, String toastText) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View toastView = inflater.inflate(R.layout.layout_toast, (ViewGroup) context.findViewById(R.id.layoutToast), false);

        TextView textViewToast = (TextView) toastView.findViewById(R.id.textViewToast);
        textViewToast.setText(toastText);

        Toast toast = new Toast(context);
        toast.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.BOTTOM, 0, ScreenUtils.dpToPx(64));
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(toastView);
        toast.show();
    }

    /**
     * Method for calling
     * @param context - Context
     * @param phoneNumber - Phone number
     */
    public static void call(Context context, String phoneNumber) {
        try {
            Intent intent = new Intent(Intent.ACTION_DIAL);
            intent.setData(Uri.parse("tel:" + phoneNumber));
            context.startActivity(intent);
        } catch (ActivityNotFoundException e) {
            e.printStackTrace();
            Toast.makeText(context, "There are no phone clients installed.", Toast.LENGTH_SHORT).show();
        }
    }
}
