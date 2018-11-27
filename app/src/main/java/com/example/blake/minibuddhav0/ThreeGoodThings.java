package com.example.blake.minibuddhav0;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ThreeGoodThings extends Activity implements View.OnClickListener {

    private EditText thingOne, thingTwo, thingThree;
    private Button saveButton;
    private String thingOneText, thingTwoText, thingThreeText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_three_good_things);

        thingOne = findViewById(R.id.thingOne);
        thingTwo = findViewById(R.id.thingTwo);
        thingThree = findViewById(R.id.thingThree);
        saveButton = findViewById(R.id.save_Button);

        thingOne.setOnClickListener(this);
        thingTwo.setOnClickListener(this);
        thingThree.setOnClickListener(this);
        saveButton.setOnClickListener(this);

    }

    public void onClick(View view) {
        if(view == saveButton){
            thingOneText = thingOne.getText().toString();
            thingTwoText = thingTwo.getText().toString();
            thingThreeText = thingThree.getText().toString();
            Toast.makeText(this, "Great success",Toast.LENGTH_SHORT).show();
        }

    }
}
