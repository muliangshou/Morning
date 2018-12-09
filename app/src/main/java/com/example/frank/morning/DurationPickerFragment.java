package com.example.frank.morning;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;

public class DurationPickerFragment extends DialogFragment {
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("How long do you want your activity to be?")
                .setItems(R.array.durationArray, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // 'which' arg contains positions
                        // TODO: change MainActivity.duration according to options
                        boolean isCancellingTransaction = false;

                        switch (which) {
                            case 0:
                                MainActivity.duration = 15;
                                break;
                            case 1:
                                MainActivity.duration = 30;
                                break;
                            case 2:
                                MainActivity.duration = 45;
                                break;
                            case 3:
                                MainActivity.duration = 90;
                                break;
                            case 4:
                                // TODO: custom duration
                                break;
                            default:
                                // the last option, go back to TimePicker
                                isCancellingTransaction = true;
                                break;
                        }
                        if (isCancellingTransaction) {
                            MainActivity.timePicker.show(getActivity().getSupportFragmentManager(), "timePicker");
                        } else {
                            MainActivity.taskCreationOverviewFragment.show(getActivity().getSupportFragmentManager(), "taskCreationOverview");
                        }

                    }
                });
        return builder.create();
    }
}
