package com.example.frank.morning;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class InvitePopUpWindow extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.invitepop);

        String time = "";
        String friendName = "";
        String taskName = "";

        Bundle bundle = getIntent().getExtras();
        if (bundle != null && bundle.containsKey("time")) {
            time = (String) bundle.get("time");
        }

        if (bundle != null && bundle.containsKey("taskName")) {
            taskName = (String) bundle.get("taskName");
        }

        if (bundle != null && bundle.containsKey("friend")) {
            friendName = (String) bundle.get("friend");
        }

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;
        int height = dm.heightPixels;
        ImageView imgView = findViewById(R.id.icon_img);
        imgView.setImageDrawable(getResources().getDrawable(R.drawable.man));
        TextView timeText = findViewById(R.id.timetext);
        timeText.setText(time);

        TextView infoText = findViewById(R.id.task_info);
        infoText.setText("works for both you and " + friendName + " to " + taskName);


        timeText.setTextSize(20);
        getWindow().setLayout((int) (width * 0.8), (int) (height * 0.7));

        Button inviteButton = findViewById(R.id.invite_button);
        inviteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent confirmPop = new Intent(getApplicationContext(), ConfirmPopUpWindow.class);
                startActivity(confirmPop);
                finish();

            }
        });
    }

    public void onClickDone(View view) {
        finish();
    }
}
