package com.example.outtask.countdownapp;

import android.content.Intent;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import de.greenrobot.event.EventBus;

public class MainActivity extends AppCompatActivity {

    TimerTask tt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TimerService.startActionCountdown(getApplicationContext(), "foo", "bar");

        //tt = new TimerTask(((TextView)findViewById(R.id.countdownID)));
        //tt.execute();
    }

    @Override
    protected void onResume() {
        super.onResume();

        EventBus.getDefault().register(this);

    }

    @Override
    protected void onPause() {
        super.onPause();

        EventBus.getDefault().unregister(this);
    }

    public void onEventMainThread(Integer event) {
        ((TextView)findViewById(R.id.countdownID)).setText(event.toString());
    }

//    public void onEvent(Integer event) {
//        ((TextView)findViewById(R.id.countdownID)).setText("[" + event.toString() + "]");
//    }
}
