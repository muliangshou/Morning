package com.example.frank.morning;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.LinkedHashMap;

public class MainActivity extends AppCompatActivity
        implements BottomNavigationView.OnNavigationItemSelectedListener{

    private TextView mTextMessage;
    static LinkedHashMap<Integer, Task> globalTaskMap = new LinkedHashMap<>();
    // metadata for Tasks. Acts as key in the globalTaskMap. <taskCounter, Task>
//    static ArrayList<Task> achievementList = new ArrayList<>();
    static int taskCounter = 0;

    // Time-Zone
    static final ZoneId ZONEID = ZoneId.of("SystemV/PST8");
    static ZonedDateTime zdt = ZonedDateTime.now(MainActivity.ZONEID);
    ActionBar ab;

    // State variables for creation of Tasks
    static String taskCreationName = "hello";
    static String taskCreationCategory = "world";
    static int year = zdt.getYear();
    static int month = zdt.getMonthValue() - 1;
    static int day = zdt.getDayOfMonth();
    static int hour = zdt.getHour();
    static int minute = zdt.getMinute();
    static int duration = 0; // in minutes

    static TimePickerFragment timePicker = new TimePickerFragment();
    static DatePickerFragment datePicker = new DatePickerFragment();
    static DurationPickerFragment durationPicker = new DurationPickerFragment();
    static TaskCreationOverviewFragment taskCreationOverviewFragment = new TaskCreationOverviewFragment();

    static boolean isConnectedFacebook = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(this);
        Fragment fragment = new ScheduleFragment();
        ab = getSupportActionBar();
        android.support.v7.app.ActionBar ab = getSupportActionBar();
        ab.setTitle("Schedules");
        loadFragment(fragment);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        Fragment fragment = null;

        switch (menuItem.getItemId()) {

            case R.id.navigation_today:
                fragment = new TodayFragment();
                ab.setTitle("Today");
                break;
            case R.id.navigation_schedule:
                fragment = new ScheduleFragment();
                ab.setTitle("Schedules");
                break;
            case R.id.navigation_achievement:
                fragment = new AchievementFragment();
                ab.setTitle("Achievements");
                break;
            case R.id.navigation_social:
                fragment = new SocialFragment();
                ab.setTitle("Social");
                break;
            }
        return loadFragment(fragment);
    }

    private boolean loadFragment(Fragment fragment){
        if (fragment != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragmentContainer, fragment)
                    .commit();
            return true;
        }
        return false;
    }

    static void addTask(ZonedDateTime taskBeginTime, ZonedDateTime taskEndTime, String taskName, String taskCategory, boolean isInToday) {
        Task t = new Task(taskCounter, taskBeginTime, taskEndTime, taskName, taskCategory, isInToday);
        globalTaskMap.put(taskCounter, t);
        incrementTaskCounter();
    }

    static void addTaskFromStaticVar(boolean isInToday) {
        ZonedDateTime beginTime = ZonedDateTime.of(MainActivity.year, MainActivity.month+1, MainActivity.day, MainActivity.hour,
                MainActivity.minute, 0, 0, MainActivity.ZONEID);
        ZonedDateTime endTime = beginTime.plusMinutes(MainActivity.duration);
        addTask(beginTime, endTime, MainActivity.taskCreationName, MainActivity.taskCreationCategory, isInToday);
    }

    static void deleteTask(int metadata) {
        globalTaskMap.remove(metadata);
        decrementTaskCounter();
    }

    static void incrementTaskCounter() {
        taskCounter++;
    }

    static void decrementTaskCounter() {
        taskCounter--;
    }
}
