package com.example.blake.minibuddhav0;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ThreeGoodThings extends Activity implements View.OnClickListener {

    private EditText thingOne, thingTwo, thingThree;
    private Button saveButton, toOldThings, toSerenity;
    private String thingOneText, thingTwoText, thingThreeText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_three_good_things);

        thingOne = findViewById(R.id.thingOne);
        thingTwo = findViewById(R.id.thingTwo);
        thingThree = findViewById(R.id.thingThree);
        saveButton = findViewById(R.id.save_Button);
        toOldThings = findViewById(R.id.threeGoodThingsButton);
        toSerenity = findViewById(R.id.guidedMeditationButton);


        thingOne.setOnClickListener(this);
        thingTwo.setOnClickListener(this);
        thingThree.setOnClickListener(this);
        saveButton.setOnClickListener(this);
        toOldThings.setOnClickListener(this);
        toSerenity.setOnClickListener(this);

    }

    public void onClick(View view) {
        if(view == saveButton){
            thingOneText = thingOne.getText().toString();
            thingTwoText = thingTwo.getText().toString();
            thingThreeText = thingThree.getText().toString();
            Toast.makeText(this, "Great success",Toast.LENGTH_SHORT).show();
            final GoodThings newThings = new GoodThings(thingOneText, thingTwoText, thingThreeText);
        }
        else if(view == toOldThings){
            Intent oldNotesIntent = new Intent(ThreeGoodThings.this, activity_old_notes.class);
            startActivity(oldNotesIntent);
        }
        else if(view == toSerenity){
            Intent serenityIntent = new Intent(ThreeGoodThings.this, GuidedMeditation.class);
            startActivity(serenityIntent);
        }

    }
}
