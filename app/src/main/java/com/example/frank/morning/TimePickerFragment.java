package com.example.frank.morning;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.app.TimePickerDialog;
import android.widget.TimePicker;

import android.text.format.DateFormat;
import android.widget.Toast;

public class TimePickerFragment extends DialogFragment implements TimePickerDialog.OnTimeSetListener {

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        // Use the current time as default values for the picker

        int hour = MainActivity.hour;
        int minute = MainActivity.minute;
        return new TimePickerDialog(getActivity(), this, hour, minute, DateFormat.is24HourFormat(getActivity()));
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        // TODO: trigger task duration fragment
        DurationPickerFragment durationPickerFragment = MainActivity.durationPicker;
        Toast.makeText(getActivity(), String.format("%d:%d", hourOfDay, minute), Toast.LENGTH_SHORT).show();
        setMainActivityStateVar(hourOfDay, minute);
        durationPickerFragment.show(getActivity().getSupportFragmentManager(), "durationPicker");
    }

    @Override
    public void onCancel(DialogInterface dialog) {
        super.onCancel(dialog);
        MainActivity.datePicker.show(getActivity().getSupportFragmentManager(), "datePicker");
    }

    private void setMainActivityStateVar(int hourOfDay, int minute) {
        MainActivity.hour = hourOfDay;
        MainActivity.minute = minute;
    }
}
