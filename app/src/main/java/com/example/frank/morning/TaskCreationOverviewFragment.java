package com.example.frank.morning;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class TaskCreationOverviewFragment extends DialogFragment {
    TextView taskOverviewHeaderTextView, taskOverviewCategoryTextView,
            taskOverviewNameTextView, taskOverviewBeginTimeTextView,
            taskOverviewEndTimeTextView;

    ZonedDateTime overviewBeginZDT,overviewEndZDT;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View alertView = inflater.inflate(R.layout.frag_task_created_overview, null);
        builder.setView(alertView)
                .setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // TODO: Create the task
                        MainActivity.addTaskFromStaticVar(false);
                    }
                })
                .setNeutralButton("Configure", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Go back to square one
                        MainActivity.datePicker.show(getActivity().getSupportFragmentManager(), "datePicker");
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Exit the overview
                        TaskCreationOverviewFragment.this.getDialog().cancel();
                    }
                })
        ;

        // Set the texts
        overviewBeginZDT = ZonedDateTime.of(MainActivity.year, MainActivity.month+1, MainActivity.day, MainActivity.hour,
                MainActivity.minute, 0, 0, MainActivity.ZONEID);
        overviewEndZDT = overviewBeginZDT.plusMinutes(MainActivity.duration);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd, hh:mma");


        taskOverviewCategoryTextView = alertView.findViewById(R.id.taskOverviewCategoryTextView);
        taskOverviewCategoryTextView.setText("Category: " + MainActivity.taskCreationCategory);
//        taskOverviewCategoryTextView.setText("Category: " + );

        taskOverviewNameTextView = alertView.findViewById(R.id.taskOverviewNameTextView);
        taskOverviewNameTextView.setText("Name: " + MainActivity.taskCreationName);
//        taskOverviewNameTextView.setText("Name: ");

        taskOverviewBeginTimeTextView = alertView.findViewById(R.id.taskOverviewBeginTimeTextView);
        taskOverviewBeginTimeTextView.setText("Begins: " + overviewBeginZDT.format(formatter));

        taskOverviewEndTimeTextView = alertView.findViewById(R.id.taskOverviewEndTimeTextView);
        taskOverviewEndTimeTextView.setText("Ends   : " + overviewEndZDT.format(formatter));

        return builder.create();
    }
}
