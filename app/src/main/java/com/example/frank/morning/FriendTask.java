package com.example.frank.morning;

public class FriendTask {
    String taskName;
    String friendName;
    String activityTime;

    public FriendTask(String name, String friend, String time) {
        this.taskName = name;
        this.friendName = friend;
        this.activityTime = time;
    }

    String getTaskName() {
        return taskName;
    }

    String getFriendName() {
        return friendName;
    }

    String getActivityTime() {
        return this.activityTime;
    }
}
