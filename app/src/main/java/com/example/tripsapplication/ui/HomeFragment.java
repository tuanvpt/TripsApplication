package com.example.tripsapplication.ui;

import android.app.DatePickerDialog;

import com.example.tripsapplication.R;
import com.example.tripsapplication.base.BaseMVVMFragment;
import com.example.tripsapplication.database.DatabaseManger;
import com.example.tripsapplication.databinding.FragmentHomeBinding;
import com.example.tripsapplication.utils.ViewUtils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;


public class HomeFragment extends BaseMVVMFragment<FragmentHomeBinding, HomeViewModel> {

    private boolean isAllFieldsChecked = false;
    private final Calendar myCalendar = Calendar.getInstance();
    private DatabaseManger dbManager;


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
        /*Hide keyboard when user press out of edittext*/
        getViewBinding().getRoot().setOnClickListener(view -> ViewUtils.hideKeyboardFrom(getContext(), getViewBinding().getRoot()));

        dbManager = new DatabaseManger(requireContext());
        dbManager.open();

        handleDataOfTrips();
        handleSubmitButton();
        handleCheckBox();
    }

    /*Handle only one can select*/
    private void handleCheckBox() {
        getViewBinding().cbYes.setOnCheckedChangeListener((buttonView, isChecked)
                -> getViewBinding().cbNo.setChecked(false));

        getViewBinding().cbNo.setOnCheckedChangeListener((buttonView, isChecked)
                -> getViewBinding().cbYes.setChecked(false));
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
                // Save data in SQLite
                SaveDataInSQLite();

                showToast("Saved");
            }
        });

    }

    private void SaveDataInSQLite() {

        final String name = getViewBinding().edtName.getText().toString();
        final String destination = getViewBinding().edtDestination.getText().toString();
        final String dataOfTrips = getViewBinding().edtDataOfTrip.getText().toString();
        final Boolean isCheck = getViewBinding().cbYes.isChecked();
        final String description = getViewBinding().edtDescription.getText().toString();

        dbManager.insert(name, destination, dataOfTrips, String.valueOf(isCheck), description);

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

/*        if (getViewBinding().cbYes.isChecked() || getViewBinding().cbNo.isChecked()) {
            ViewUtils.show(getViewBinding().tvErrorCheckRisk);
            getViewBinding().tvErrorCheckRisk.setText(R.string.label_validation_risk);
            return false;
        }*/

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