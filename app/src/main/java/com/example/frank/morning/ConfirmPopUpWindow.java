package com.example.frank.morning;
import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class ConfirmPopUpWindow extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.confirmpop);


        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;
        int height = dm.heightPixels;
        ImageView imgView = findViewById(R.id.check_img);
        imgView.setImageDrawable(getResources().getDrawable(R.drawable.check));

        TextView infoText = findViewById(R.id.confirm_info);


        infoText.setTextSize(20);
        getWindow().setLayout((int) (width * 0.8), (int) (height * 0.7));
    }

    public void onClickDone(View view) {
        finish();
    }
}
