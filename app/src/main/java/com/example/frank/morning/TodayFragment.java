package com.example.frank.morning;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TodayFragment extends Fragment {
    RecyclerView recyclerView;
    List<Task> globalTaskList = new ArrayList<>();
    TodayTaskListAdapter adapter;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);
        View root = inflater.inflate(R.layout.frag_todo, container, false);
//        populateTaskList();
        fetchTasksFromGlobal();
        layout_UI(root);
        return root;
    }

    // Dummy method for DEBUG
    @RequiresApi(api = Build.VERSION_CODES.O)
    private void populateTaskList() {
        globalTaskList = new ArrayList<>();
        globalTaskList.add(new Task(0, ZonedDateTime.now(MainActivity.ZONEID), ZonedDateTime.now(MainActivity.ZONEID), "taskName", "taskCategory", true));
        globalTaskList.add(new Task(1, ZonedDateTime.now(MainActivity.ZONEID), ZonedDateTime.now(MainActivity.ZONEID), "taskName", "taskCategory", true));
        globalTaskList.add(new Task(2, ZonedDateTime.now(MainActivity.ZONEID), ZonedDateTime.now(MainActivity.ZONEID), "taskName", "taskCategory", true));
    }

    // populate recyclerView
    private void layout_UI(View root) {
        recyclerView = root.findViewById(R.id.todo_recyclerView);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new TodayTaskListAdapter(getContext(), globalTaskList, this);
        recyclerView.setAdapter(adapter);

        itemTouchHelper.attachToRecyclerView(recyclerView);
    }

    // Get Tasks from the global task map located in MainActivity
    private void fetchTasksFromGlobal() {
        for (Map.Entry<Integer, Task> entry : MainActivity.globalTaskMap.entrySet()) {
            Task t = entry.getValue();
            if (!t.isCompleted()) {
                globalTaskList.add(t);
            }
        }
    }

    /* Task Delete-on-Swipe */
    ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder viewHolder1) {
            //Toast.makeText(getContext(), getString(R.string.achievement_tasks_moved), Toast.LENGTH_SHORT).show();
            return false;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
            //Toast.makeText(getContext(), getString(R.string.achievement_tasks_swiped), Toast.LENGTH_SHORT).show();
            final int position = viewHolder.getAdapterPosition();

            Task toDelete = globalTaskList.get(position);
            MainActivity.deleteTask(toDelete.getmData());
            globalTaskList.remove(position);
            adapter.notifyDataSetChanged();
        }


    });

    public void pushToAchievement(Task toDelete) {
        toDelete.setCompleted(true);
        globalTaskList.remove(toDelete);
        adapter.notifyDataSetChanged();
    }

    // TODO: change accroding to new Date selection system
    public boolean isSameDay(ZonedDateTime d) {
//        ZonedDateTime now = ZonedDateTime.now();
//        LocalDateTime atStartOfToday = now.toLocalDate().atStartOfDay();
//        LocalDateTime atStartOfDay = d.toLocalDate().atStartOfDay();
//        return atStartOfDay.equals(atStartOfToday);
        return true;
    }
}
