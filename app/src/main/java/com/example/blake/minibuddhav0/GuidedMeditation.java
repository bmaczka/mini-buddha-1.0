package com.example.blake.minibuddhav0;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class GuidedMeditation extends AppCompatActivity implements View.OnClickListener {

    private Button fiveMinuteButton, tenMinuteButton, fifteenMinuteButton, customButton, startButton;
    private TextView timerTextView;
    private CountDownTimer countDownTimer;
    private long startTimeInMillis = 600000; //10 minutes
    private long timeLeftInMilliseconds = startTimeInMillis;
    private boolean timerRunning;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guided_meditation);

        fiveMinuteButton = findViewById(R.id.fiveMinuteButton);
        tenMinuteButton = findViewById(R.id.tenMinuteButton);
        fifteenMinuteButton = findViewById(R.id.fifteenMinuteButton);
        //customButton = findViewById(R.id.customButton);
        timerTextView = findViewById(R.id.timerTextView);
        startButton = findViewById(R.id.startButton);
        fiveMinuteButton.setOnClickListener(this);
        tenMinuteButton.setOnClickListener(this);
        fifteenMinuteButton.setOnClickListener(this);
        //customButton.setOnClickListener(this);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(timerRunning){
                    stopTimer();
                }
                else{
                    startTimer();
                }
            }
        });

        updateTimer();
    }

    @Override
    public void onClick(View view) {
        int minutes = (int) timeLeftInMilliseconds / 60000;
        int seconds = (int) timeLeftInMilliseconds % 60000 / 1000;
        switch(view.getId()) {
            case R.id.fiveMinuteButton:
                //set timer to 05:00
                timeLeftInMilliseconds = 300000;
                String currentTime = timeParser(minutes, seconds);
                timerTextView.setText(currentTime);
                break;

            case R.id.tenMinuteButton:
                //set timer to 10:00
                timeLeftInMilliseconds = 600000;
                currentTime = timeParser(minutes, seconds);
                timerTextView.setText(currentTime);
                break;

            case R.id.fifteenMinuteButton:
                //set timer to 15:00
                timeLeftInMilliseconds = 900000;
                currentTime = timeParser(minutes, seconds);
                timerTextView.setText(currentTime);
                break;

           /*case R.id.customButton:
                Intent customIntent = new Intent(GuidedMeditation.this, CustomTimer.class);
                startActivity(customIntent);
                break;
           */

            case R.id.startButton:
                //start the timer and update the text view to reflect the countdown
                startTimer();
                break;
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
    timerTextView.setText(timeLeftText);
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
