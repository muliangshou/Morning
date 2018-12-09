package com.example.frank.morning;

import android.content.Context;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class AchievementTaskListAdapter extends RecyclerView.Adapter<AchievementTaskListAdapter.TaskHolder> {
    private Context mCtx;
    private List<Task> taskList;
    private AchievementFragment frag;

    public AchievementTaskListAdapter(Context mCtx, List<Task> taskList, AchievementFragment fragment) {
        this.mCtx = mCtx;
        this.taskList = taskList;
        this.frag = fragment;
    }

    @NonNull
    @Override
    public TaskHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View v = inflater.inflate(R.layout.achievement_card_view, null);
        TaskHolder holder = new TaskHolder(v);
        return holder;
    }



    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onBindViewHolder(@NonNull TaskHolder taskHolder, int idx) {
        final Task task = taskList.get(idx);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        ZonedDateTime taskBeginTime = task.getTaskBeginTime();
        String taskTime = taskBeginTime.format(formatter);

        taskHolder.taskCardName.setText(task.getTaskName());
        taskHolder.taskCardTime.setText(taskTime);




        // TODO: change picture according to Category
        switch (task.getTaskName()) {
            case "Go to Gym":
                Picasso.get().load(R.drawable.gym).into(taskHolder.imgView);
                break;
            case "Basketball":
                Picasso.get().load(R.drawable.basketball).into(taskHolder.imgView);
                break;
            case "Running":
                Picasso.get().load(R.drawable.running).into(taskHolder.imgView);
                break;
            case "Yoga":
                Picasso.get().load(R.drawable.yoga).into(taskHolder.imgView);
                break;
            case "Deep Beath":
                Picasso.get().load(R.drawable.breathe).into(taskHolder.imgView);
                break;
//            case "Lunch with Friends":
//                Picasso.get().load(R.drawable.lunch).into(taskHolder.imgView);
//                break;
            case "Movie Night":
                Picasso.get().load(R.drawable.movie).into(taskHolder.imgView);
                break;
            case "Grocery Shopping":
                Picasso.get().load(R.drawable.grocery).into(taskHolder.imgView);
                break;
            case "Cook Dinner":
                Picasso.get().load(R.drawable.cook).into(taskHolder.imgView);
                break;


            default:
                Picasso.get().load(R.drawable.laundry).into(taskHolder.imgView);
                break;
        }


    }

    @Override
    public int getItemCount() {
        return taskList.size();
    }

    class TaskHolder extends RecyclerView.ViewHolder {
        // Refer to task_card_view.xml
        ImageView imgView;
        CheckBox checkBox;
        TextView taskCardName, taskCardTime;

        TaskHolder(@NonNull View itemView) {
            super(itemView);

            imgView = itemView.findViewById(R.id.achievementPicture);
            taskCardName = itemView.findViewById(R.id.achievementName);
            taskCardTime = itemView.findViewById(R.id.achievementTime);

        }
    }
}

