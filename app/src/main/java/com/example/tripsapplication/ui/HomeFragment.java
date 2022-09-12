package com.example.tripsapplication.ui;

import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;

import com.example.tripsapplication.R;
import com.example.tripsapplication.base.BaseMVVMFragment;
import com.example.tripsapplication.databinding.FragmentHomeBinding;
import com.example.tripsapplication.utils.ViewUtils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;


public class HomeFragment extends BaseMVVMFragment<FragmentHomeBinding, HomeViewModel> {

    private boolean isAllFieldsChecked = false;
    final Calendar myCalendar = Calendar.getInstance();


    @Override
    protected Class<HomeViewModel> getViewModelClass() {
        return HomeViewModel.class;
    }

    @Override
    protected FragmentHomeBinding getLayoutBinding() {
        return FragmentHomeBinding.inflate(getLayoutInflater());
    }

    @Override
    protected void initialize() {
        handleDataOfTrips();
        handleSubmitButton();
    }

    private void handleDataOfTrips() {
        DatePickerDialog.OnDateSetListener date = (view, year, month, day) -> {
            myCalendar.set(Calendar.YEAR, year);
            myCalendar.set(Calendar.MONTH, month);
            myCalendar.set(Calendar.DAY_OF_MONTH, day);
            updateLabel();
        };


        getViewBinding().edtDataOfTrip.setClickable(true);
        getViewBinding().edtDataOfTrip.setOnClickListener(view ->
                new DatePickerDialog(requireContext(), date, myCalendar.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH), myCalendar.get(Calendar.DAY_OF_MONTH)).show());
    }

    private void updateLabel() {
        String myFormat = "MM/dd/yy";
        SimpleDateFormat dateFormat = new SimpleDateFormat(myFormat, Locale.US);
        getViewBinding().edtDataOfTrip.setText(dateFormat.format(myCalendar.getTime()));
    }

    private void handleSubmitButton() {
        getViewBinding().btnDatabase.setOnClickListener(view -> {
            isAllFieldsChecked = CheckAllFields();
            if (isAllFieldsChecked) {
                showToast("True");
            }
        });

    }

    private boolean CheckAllFields() {
        if (getViewBinding().edtName.length() == 0) {
            getViewBinding().edtName.setError("This field is required");
            return false;
        }

        if (getViewBinding().edtDestination.length() == 0) {
            getViewBinding().edtDestination.setError("This field is required");
            return false;
        }

        if (getViewBinding().edtDataOfTrip.length() == 0) {
            getViewBinding().edtDataOfTrip.setError("This field is required");
            return false;
        }


        // after all validation return true.
        return true;
    }

    @Override
    protected void registerViewEvent() {

    }

    @Override
    protected void registerViewModelObs() {

    }
}