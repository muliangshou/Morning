

package com.example.frank.morning;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AchievementFragment extends Fragment {
    RecyclerView recyclerView;
    List<Task> achievementList = new ArrayList<>();
    AchievementTaskListAdapter adapter;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);
        View root = inflater.inflate(R.layout.frag_achievement, container, false);
//        populateTaskList();
        fetchTasksFromGlobal();
        layout_UI(root);
        return root;
    }

    private void layout_UI(View root) {
        recyclerView = root.findViewById(R.id.achievement_recyclerView);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new AchievementTaskListAdapter(getContext(), achievementList, this);
        recyclerView.setAdapter(adapter);
        itemTouchHelper.attachToRecyclerView(recyclerView);

    }

    private void fetchTasksFromGlobal() {
        for (Map.Entry<Integer, Task> entry : MainActivity.globalTaskMap.entrySet()) {
            Task t = entry.getValue();
            if (t.isCompleted()) {
                achievementList.add(t);
            }
        }
    }


    ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder viewHolder1) {
            Toast.makeText(getContext(), getString(R.string.achievement_tasks_moved), Toast.LENGTH_SHORT).show();
            return false;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
            Toast.makeText(getContext(), getString(R.string.achievement_tasks_swiped), Toast.LENGTH_SHORT).show();
            final int position = viewHolder.getAdapterPosition();
//            adapter.notifyItemRemoved(position);

            Task toDelete = achievementList.get(position);
            MainActivity.deleteTask(toDelete.getmData());
            achievementList.remove(position);
            adapter.notifyDataSetChanged();
        }


    });
}
