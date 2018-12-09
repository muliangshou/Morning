package com.example.frank.morning;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import java.time.ZonedDateTime;

public class SchedulingTaskListAdapter extends BaseExpandableListAdapter {


    Context context;
    // Change the child size if there's more tasks to be added
    Integer[][] selectedItems = new Integer[Task.CATEGORIES.length][5];

    FragmentManager supportFragmentManager;

    public SchedulingTaskListAdapter(Context context, FragmentManager supportFragmentManager) {
        this.context = context;
        this.supportFragmentManager = supportFragmentManager;
    }

    @Override
    public int getGroupCount() {
        return Task.CATEGORIES.length;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return Task.TASKNAMES[groupPosition].length;
    }

    @Override
    public String getGroup(int groupPosition) {
        return Task.CATEGORIES[groupPosition];
    }

    @Override
    public String getChild(int groupPosition, int childPosition) {
        return Task.TASKNAMES[groupPosition][childPosition];
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return  childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        View v = convertView;
        if (v == null) {
            LayoutInflater inflater = LayoutInflater.from(context);
            v = inflater.inflate(R.layout.parent_card_view, null);
            TextView categoryTitle = v.findViewById(R.id.categoryName);
            categoryTitle.setText(Task.CATEGORIES[groupPosition]);
        }
        return v;
    }

    @Override
    public View getChildView(final int groupPosition, final int childPosition, final boolean isLastChild, View convertView, ViewGroup parent) {

        final String item = getChild(groupPosition, childPosition);

        View v;
        final TextView txtView = new TextView(context);
        v = txtView;
        txtView.setText(item);
        txtView.setTextSize(20);
        txtView.setPadding(100, 0,0,0);

        final Integer groupTag = groupPosition;
        final Integer childTag = childPosition;

        v.setOnClickListener(new View.OnClickListener() {
                                       @Override
                                       public void onClick(View v) {
                                           // TODO: Needs a new interface so that user can select time for different new tasks.
                                           // Below is code to enable multi-choice.
//                                           Boolean isActivated = v.isActivated();
//
//                                           if (!isActivated) {
//                                               selectedItems[groupPosition][childPosition] = 1;
//                                               Toast.makeText(context, Task.TASKNAMES[groupPosition][childPosition], Toast.LENGTH_SHORT).show();
//                                               v.setBackgroundResource(R.color.child);
//                                               v.setActivated(true);
//                                           } else {
//                                               selectedItems[groupPosition][childPosition] = 0;
//                                               v.setBackgroundResource(0);
//                                               v.setActivated(false);
//                                               Toast.makeText(context, Task.TASKNAMES[groupPosition][childPosition] + " Deselected", Toast.LENGTH_SHORT).show();
//                                           }

                                           MainActivity.taskCreationCategory = getGroup(groupTag);
                                           MainActivity.taskCreationName = getChild(groupTag, childPosition);
                                           MainActivity.datePicker.show(supportFragmentManager, "datePicker");
                                       }
                                   });

        return v;
    }



    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    // TODO: change according to date selector
    void createNewTask(int groupPosition, int childPosition) {
        ZonedDateTime taskBeginTime, taskEndTime;
        taskBeginTime = getTaskBeginTime();
        taskEndTime = getTaskEndTime();
        String category = Task.CATEGORIES[groupPosition];
        String taskName = Task.TASKNAMES[groupPosition][childPosition];
        MainActivity.addTask(taskBeginTime, taskEndTime, taskName, category, true);
    }

    void populateNewTask() {
        for (int row = 0; row < selectedItems.length; row++) {
            for (int column = 0; column < selectedItems[row].length; column++) {
                if (selectedItems[row][column] == null || selectedItems[row][column] == 0) {
                    continue;
                }
                if (selectedItems[row][column] == 1) {
                    createNewTask(row, column);
                }
            }
        }
    }



    // TODO: integrate with an actual interface to select time and date
    ZonedDateTime getTaskBeginTime() {
        return ZonedDateTime.now(MainActivity.ZONEID);
    }

    ZonedDateTime getTaskEndTime() {
        return ZonedDateTime.now(MainActivity.ZONEID);
    }


}
