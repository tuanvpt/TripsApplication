package com.example.tripsapplication.utils;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.TimePicker;

import java.util.Calendar;

/**
 * Utilities of View
 */
public class ViewUtils {
    /**
     * Set click with delay of view
     *
     * @param view            view need to handle click
     * @param onClickListener response function when user clicked on view
     */
    public static void setOnDelayClick(View view, View.OnClickListener onClickListener) {
        view.setOnClickListener(v -> {
            /*Disable click event of view*/
            view.setEnabled(false);

            /*Handle click event*/
            onClickListener.onClick(view);

            /*Open view click */
            view.postDelayed(() -> view.setEnabled(true), 500);
        });
    }

    /**
     * Change visibility of view to Visible
     *
     * @param view view need to change visibility
     */
    public static void show(View view) {
        view.setVisibility(View.VISIBLE);
    }

    /**
     * Change visibility of view to Visible
     *
     * @param view   view need to change visibility
     * @param isShow is showing view or not
     */
    public static void changeVisibility(View view, boolean isShow) {
        if (isShow) {
            view.setVisibility(View.VISIBLE);
        } else {
            view.setVisibility(View.GONE);
        }
    }

    /**
     * Change visibility of view to Visible
     *
     * @param view view need to change visibility
     */
    public static void hide(View view) {
        view.setVisibility(View.GONE);
    }

    /**
     * Show dialog
     *
     * @param dialog dialog need to show
     */
    public static void showDialog(Dialog dialog) {
        /*Only show if dialog not showing*/
        if (!dialog.isShowing()) {
            dialog.show();
        }
    }

    /**
     * Hide keyboard
     *
     * @param context context
     * @param view    view need focus
     */
    public static void hideKeyboardFrom(Context context, View view) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Activity.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}
