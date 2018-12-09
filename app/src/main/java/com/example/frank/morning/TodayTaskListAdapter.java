package com.example.frank.morning;

import android.content.Context;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.time.format.DateTimeFormatter;
import java.util.List;

public class TodayTaskListAdapter extends RecyclerView.Adapter<TodayTaskListAdapter.TaskHolder> {
    private Context mCtx;
    private List<Task> taskList;
    private TodayFragment frag;

    public TodayTaskListAdapter(Context mCtx, List<Task> taskList, TodayFragment fragment) {
        this.mCtx = mCtx;
        this.taskList = taskList;
        this.frag = fragment;
    }

    @NonNull
    @Override
    public TaskHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View v = inflater.inflate(R.layout.task_card_view, null);
        TaskHolder holder = new TaskHolder(v);
        return holder;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onBindViewHolder(@NonNull TaskHolder taskHolder, int idx) {
        final Task task = taskList.get(idx);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd, hh:mma");

        taskHolder.taskCardName.setText(task.getTaskName());
        taskHolder.taskCardEndTime.setText("End:    " + task.getTaskEndTime().format(formatter));
        taskHolder.taskCardBeginTime.setText("Begin: " + task.getTaskBeginTime().format(formatter));
        taskHolder.checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (((CheckBox) v).isChecked()) {
                    Log.i("msg", "selected");
//                    taskList.remove(task);
                    frag.pushToAchievement(task);
                    notifyDataSetChanged();
                    ((CheckBox) v).setChecked(false);
                }
            }
        });
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
        TextView taskCardName, taskCardBeginTime, taskCardEndTime;

        TaskHolder(@NonNull View itemView) {
            super(itemView);

            imgView = itemView.findViewById(R.id.taskCardPicture);
            checkBox = itemView.findViewById(R.id.taskCardCheckbox);
            taskCardName = itemView.findViewById(R.id.taskCardName);
            taskCardBeginTime = itemView.findViewById(R.id.taskCardBeginTime);
            taskCardEndTime = itemView.findViewById(R.id.taskCardEndTime);
        }
    }
}

