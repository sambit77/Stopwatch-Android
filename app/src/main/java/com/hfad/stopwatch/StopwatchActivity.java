package com.hfad.stopwatch;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.util.Locale;
import java.util.logging.Handler;
import java.util.logging.LogRecord;


public class StopwatchActivity extends Activity {
    private  int seconds = 0 ;
    private  boolean running;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stopwatch);

        if( savedInstanceState != null)
        {
            seconds = savedInstanceState.getInt("seconds");
            running = savedInstanceState.getBoolean("running");
        }
       runTimer();
    }
    public  void onSaveInstanceState(Bundle savedInstanceState)
    {
        savedInstanceState.putInt("seconds",seconds);
        savedInstanceState.putBoolean("running",running);
    }


    public void onClickStart(View v)
    {
        running = true;

    }
    public void onClickStop(View v)
    {
        running = false;
    }
    public void onClickReset(View v)
    {
        running = false;
        seconds = 0 ;
    }


    private void runTimer()

    {
        final TextView timeView = (TextView) findViewById(R.id.time_View);
        final android.os.Handler handler = new android.os.Handler();
        handler.post(new Runnable() {
            @Override
            public void run()
            {
                int hours = (seconds/3600) ;
                int minutes = (seconds%3600)/60 ;
                int secs = seconds%60;
                String time = String.format(Locale.getDefault(),"%d:%02d:%02d",hours,minutes,secs);
                timeView.setText(time);
                if(running)
                {
                    seconds++;
                }
                handler.postDelayed(this,1000);
            }
        });

    }

}
