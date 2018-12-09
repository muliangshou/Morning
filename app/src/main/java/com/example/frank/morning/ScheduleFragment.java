package com.example.frank.morning;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

public class ScheduleFragment extends Fragment {
    ExpandableListView expandableListView;
//    Button button;
//    Button buttonSelectTime;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View todayView = inflater.inflate(R.layout.frag_schedule, null);
//        button = todayView.findViewById(R.id.nextButton);
        expandableListView = todayView.findViewById(R.id.activityList);
        final SchedulingTaskListAdapter adapter = new SchedulingTaskListAdapter(getContext(), getActivity().getSupportFragmentManager());
        expandableListView.setAdapter(adapter);

//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                adapter.populateNewTask();
//                Fragment todayFragment = new TodayFragment();
//                loadFragment(todayFragment);
//            }
//        });
//
//        buttonSelectTime = todayView.findViewById(R.id.buttonSelectTime);
//        buttonSelectTime.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                showTimePickerDialog(v);
//            }
//        });
        return todayView;
    }

    private boolean loadFragment(Fragment fragment){
        if (fragment != null) {
            getActivity().getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragmentContainer, fragment)
                    .commit();
            BottomNavigationView navView = getActivity().findViewById(R.id.navigation);
            navView.getMenu().getItem(1).setChecked(true);
            return true;
        }
        return false;
    }

    void showTimePickerDialog(View v) {
//        DialogFragment newF = new TimePickerFragment();
//        newF.show(getActivity().getSupportFragmentManager(), "timePicker");

        DialogFragment newF = new DatePickerFragment();
        newF.show(getActivity().getSupportFragmentManager(), "datePicker");
    }
}
