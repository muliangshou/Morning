//package com.example.frank.mshou.mshou.morning;
//
//import android.os.Bundle;
//import android.support.annotation.NonNull;
//import android.support.annotation.Nullable;
//import android.support.v4.app.Fragment;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ExpandableListView;
//
//import java.time.LocalDateTime;
//import java.time.ZonedDateTime;
//
//public class TodayFragment extends Fragment {
//    ExpandableListView expandableListView;
//
//    @Nullable
//    @Override
//    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        View todayView = inflater.inflate(R.layout.frag_schedule, null);
//        expandableListView = todayView.findViewById(R.id.activityList);
//        SchedulingTaskListAdapter adapter = new SchedulingTaskListAdapter(getContext());
//        expandableListView.setAdapter(adapter);
//        return todayView;
//    }
//
//
//}
