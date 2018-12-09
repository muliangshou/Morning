package com.example.frank.morning;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;

public class IntroActivity extends AppCompatActivity implements View.OnClickListener {
    private ViewPager mPager;
    private int[] layouts = {R.layout.slide1, R.layout.slide2, R.layout.slide3};
    private IntroPagerAdapter pagerAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar ab = getSupportActionBar();
        ab.hide();
        if (Build.VERSION.SDK_INT >= 19) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        } else {
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
        setContentView(R.layout.intro_activity);
        mPager = (ViewPager) findViewById(R.id.viewPager);
        pagerAdapter = new IntroPagerAdapter(layouts, this);
        mPager.setAdapter(this.pagerAdapter);


    }

    @Override
    public void onClick(View v) {
        loadHome();

    }

    private void loadHome() {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }
}
