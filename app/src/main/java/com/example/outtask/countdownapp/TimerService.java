package com.example.outtask.countdownapp;

import android.app.IntentService;
import android.content.Intent;
import android.content.Context;
import android.util.Log;

import de.greenrobot.event.EventBus;

public class TimerService extends IntentService {

    private final static String ACTION_COUNTDOWN = "AC";
    private static int DELAY = 128;

    public TimerService() {
        super("TimerService");
        //super(TimerService.class.getName());
        //setIntentRedelivery(true);
    }

    public static void startActionCountdown(Context context, String param1, String param2) {
        Intent intent = new Intent(context, TimerService.class);
        intent.setAction(ACTION_COUNTDOWN);
        intent.putExtra("EXTRA_PARAM1", param1);
        intent.putExtra("EXTRA_PARAM2", param2);
        context.startService(intent);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        // Context: getApplicationContext()
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            if (ACTION_COUNTDOWN.equals(action)) {
                final String param1 = intent.getStringExtra("EXTRA_PARAM1");
                final String param2 = intent.getStringExtra("EXTRA_PARAM2");
                handleActionCountdown(param1, param2);
            }
        }
    }

    private void handleActionCountdown(String param1, String param2) {
        try {
            while (true) {
                Thread.sleep(1000);
                DELAY--;

                EventBus.getDefault().post(new Integer(DELAY));
            }
        } catch (Exception e) {
            // TODO
            Log.wtf("CountDown error", e);
        }

    }
}
