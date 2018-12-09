package com.example.frank.morning;

import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.RequiresApi;

import java.time.ZonedDateTime;

public class Task implements Parcelable {
    private int mData;
    private ZonedDateTime taskBeginTime, taskEndTime;
    private String taskName, taskCategory;
    boolean isInToday = false;
    boolean isCompleted = false;

    static String[] CATEGORIES = {"Outdoors", "Meditation", "Social", "Life Essentials"};
    static String[][] TASKNAMES = {
            {"Go to Gym", "Basketball", "Running", "Breathe the Smoke"},
            {"Yoga", "Deep Breath"},
            {"Lunch with Friends", "Movie Night"},
            {"Do Laundry", "Grocery Shopping", "Cook Dinner"}
    };

    public Task(int mData, ZonedDateTime taskBeginTime, ZonedDateTime taskEndTime, String taskName, String taskCategory, boolean isInToday) {
        this.mData = mData;
        this.taskBeginTime = taskBeginTime;
        this.taskEndTime = taskEndTime;
        this.taskName = taskName;
        this.taskCategory = taskCategory;
        this.isInToday = isInToday;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    protected Task(Parcel in) {
        mData = in.readInt();
        taskName = in.readString();
        taskCategory = in.readString();
        taskBeginTime = (ZonedDateTime) in.readSerializable();
        taskEndTime = (ZonedDateTime) in.readSerializable();
    }

    public static final Creator<Task> CREATOR = new Creator<Task>() {
        @RequiresApi(api = Build.VERSION_CODES.O)
        @Override
        public Task createFromParcel(Parcel in) {
            return new Task(in);
        }

        @Override
        public Task[] newArray(int size) {
            return new Task[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void writeToParcel(Parcel parcel, int i) {

        parcel.writeInt(mData);
        parcel.writeString(taskName);
        parcel.writeString(taskCategory);
        parcel.writeSerializable(taskBeginTime);
        parcel.writeSerializable(taskEndTime);
    }

    public int getmData() {
        return mData;
    }

    public ZonedDateTime getTaskBeginTime() {
        return taskBeginTime;
    }

    public ZonedDateTime getTaskEndTime() {
        return taskEndTime;
    }

    public String getTaskName() {
        return taskName;
    }

    public String getTaskCategory() {
        return taskCategory;
    }

    public void setmData(int mData) {
        this.mData = mData;
    }

    public void setTaskBeginTime(ZonedDateTime taskBeginTime) {
        this.taskBeginTime = taskBeginTime;
    }

    public void setTaskEndTime(ZonedDateTime taskEndTime) {
        this.taskEndTime = taskEndTime;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public void setTaskCategory(String taskCategory) {
        this.taskCategory = taskCategory;
    }

    public boolean isInToday() {
        return isInToday;
    }

    public void setInToday(boolean inToday) {
        isInToday = inToday;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }
}
