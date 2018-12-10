package com.example.blake.minibuddhav0;

import android.app.Activity;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class CustomTimer extends Activity implements View.OnClickListener {

    private Button startButton;
    private EditText customTimeText;
    private TextView customTimeTextView;
    private CountDownTimer countDownTimer;
    private int customTime;
    private long startTimeInMillis;
    private long timeLeftInMilliseconds = startTimeInMillis;
    private boolean timerRunning;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_timer);

        startButton = findViewById(R.id.customStartButton);
        customTimeText = findViewById(R.id.customTimeText);
        customTimeTextView = findViewById(R.id.customTimerTextView);

        startTimeInMillis = customTime * 6000;

        startButton.setOnClickListener(this);

        updateTimer();
    }

    @Override
    public void onClick(View v) {
        customTime = Integer.parseInt(customTimeText.getText().toString());
        if(timerRunning){
            stopTimer();
        }
        else{
            startTimer();
        }
    }

    public void startTimer(){
        countDownTimer = new CountDownTimer(timeLeftInMilliseconds, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeLeftInMilliseconds = millisUntilFinished;
                updateTimer();
            }

            @Override
            public void onFinish() {
                timerRunning = false;
                startButton.setText("START");

            }
        }.start();
        startButton.setText("PAUSE");
        timerRunning = true;
    }

    public void stopTimer(){
        countDownTimer.cancel();
        startButton.setText("START");
        timerRunning = false;
    }

    public void updateTimer(){
        int minutes = (int) timeLeftInMilliseconds / 60000;
        int seconds = (int) timeLeftInMilliseconds % 60000 / 1000;

        String timeLeftText = timeParser(minutes, seconds);
        customTimeTextView.setText(timeLeftText);
    }

    public String timeParser(int minutes, int seconds){
        String timeLeftText;
        timeLeftText = "" + minutes;
        timeLeftText += ":";
        if(seconds < 10) timeLeftText += "0";
        timeLeftText += seconds;
        return timeLeftText;
    }
}
