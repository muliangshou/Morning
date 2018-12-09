package com.example.frank.morning;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public class FriendActivityListAdapter extends RecyclerView.Adapter<FriendActivityListAdapter.TaskHolder>  {
    List<FriendTask> taskList;
    Context mCtx;
    SocialFragment frag;


    public FriendActivityListAdapter(Context mCtx, List<FriendTask> taskList, SocialFragment fragment) {
        this.mCtx = mCtx;
        this.taskList = taskList;
        this.frag = fragment;
    }

    @NonNull
    @Override
    public TaskHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View v = inflater.inflate(R.layout.friend_task_cardview, null);
        TaskHolder holder = new TaskHolder(v);
        return holder;
    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onBindViewHolder(@NonNull FriendActivityListAdapter.TaskHolder taskHolder, int idx) {
        final FriendTask task = taskList.get(idx);
        String taskName = task.getTaskName();
        String friendName = task.getFriendName();
        taskHolder.taskNameInfo.setText(friendName + " is also doing " + taskName);
        taskHolder.inviteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent popUpWarning = new Intent(mCtx, InvitePopUpWindow.class);
                popUpWarning.putExtra("time", task.getActivityTime());
                popUpWarning.putExtra("taskName", task.getTaskName());
                popUpWarning.putExtra("friend", task.getFriendName());
                mCtx.startActivity(popUpWarning);
            }
        });
    }

    @Override
    public int getItemCount() {
        return taskList.size();
    }

    class TaskHolder extends RecyclerView.ViewHolder {
        // Refer to task_card_view.xml
        Button inviteButton;
        TextView taskNameInfo;


        TaskHolder(@NonNull View itemView) {
            super(itemView);
            inviteButton = itemView.findViewById(R.id.inviteButton);
            taskNameInfo = itemView.findViewById(R.id.userInfo);

        }
    }
}
