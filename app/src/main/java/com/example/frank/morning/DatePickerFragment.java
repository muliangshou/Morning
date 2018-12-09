package com.example.frank.morning;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.app.DatePickerDialog;
import android.widget.DatePicker;
import android.widget.Toast;

public class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {

    int year, month, day;


    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        // Current date as default option, if nothing is restored from bundle
        boolean isPreviouslySavedState = savedInstanceState != null;
        year =  isPreviouslySavedState ? savedInstanceState.getInt("YEAR") : MainActivity.year;
        month = isPreviouslySavedState ? savedInstanceState.getInt("MONTH") : MainActivity.month;
        day = isPreviouslySavedState ? savedInstanceState.getInt("DAY") : MainActivity.day;

        return new DatePickerDialog(getActivity(), this, year, month, day);
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        // Trigger timePicker
        TimePickerFragment timePicker = MainActivity.timePicker;
        Toast.makeText(getActivity(), String.format("%d-%d-%d", year, month+1, dayOfMonth), Toast.LENGTH_SHORT).show();

        setMainActivityStateVar(year, month, dayOfMonth);

        timePicker.show(getActivity().getSupportFragmentManager(), "timePicker");
    }



    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("YEAR", year);
        outState.putInt("MONTH", month);
        outState.putInt("DAY", day);
    }

    private void setMainActivityStateVar(int year, int month, int dayOfMonth) {
        MainActivity.year = year;
        MainActivity.month = month;
        MainActivity.day = dayOfMonth;
    }
}
