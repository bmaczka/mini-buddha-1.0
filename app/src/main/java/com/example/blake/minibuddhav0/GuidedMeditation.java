package com.example.blake.minibuddhav0;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class GuidedMeditation extends AppCompatActivity implements View.OnClickListener {

    private Button fiveMinuteButton, tenMinuteButton, fifteenMinuteButton, startButton;
    private TextView timerTextView;
    private CountDownTimer countDownTimer;
    private long startTimeInMillis = 600000;
    private long timeLeftInMilliseconds = startTimeInMillis; //10 minutes
    private boolean timerRunning;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guided_meditation);

        fiveMinuteButton = findViewById(R.id.fiveMinuteButton);
        tenMinuteButton = findViewById(R.id.tenMinuteButton);
        fifteenMinuteButton = findViewById(R.id.fifteenMinuteButton);
        timerTextView = findViewById(R.id.timerTextView);
        startButton = findViewById(R.id.startButton);
        fiveMinuteButton.setOnClickListener(this);
        tenMinuteButton.setOnClickListener(this);
        fifteenMinuteButton.setOnClickListener(this);
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
        if(view == fiveMinuteButton){
            //set timer to 05:00
        }
        else if(view == tenMinuteButton){
            //set timer to 10:00
        }
        else if(view == fifteenMinuteButton){
            //set timer to 15:00
        }
        else if(view == startButton){
            //start the timer and update the text view to reflect the countdown
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

    String timeLeftText;
    timeLeftText = "" + minutes;
    timeLeftText += ":";
    if(seconds < 10) timeLeftText += "0";
    timeLeftText += seconds;

    timerTextView.setText(timeLeftText);
    }
}
