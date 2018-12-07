package com.example.blake.minibuddhav0;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Date;

public class ThreeGoodThings extends Activity implements View.OnClickListener {

    private EditText thingOne, thingTwo, thingThree;
    private Button saveButton, toOldThings, toSerenity;
    private String thingOneText, thingTwoText, thingThreeText, user, dateText;
    private TextView messageTextView;
    private DatabaseReference key;
    private Date date;


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
        messageTextView = findViewById(R.id.messageTextView);


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
            date = new Date();
            final DatabaseReference dbref;
            dbref = FirebaseDatabase.getInstance().getReference(user);
            key = dbref.push();
            final GoodThings goodthings = new GoodThings(thingOneText, thingTwoText, thingThreeText, date);
            dbref.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if(dataSnapshot.exists()){
                        key.setValue(goodthings);
                        messageTextView.setText("memories saved successfully");
                    } else { //if there's no data snapshot at all
                        key.setValue(goodthings);
                        messageTextView.setText("memories saved successfully");
                    }
                }
                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                }
            });


            Toast.makeText(this, "Great success",Toast.LENGTH_SHORT).show();
            //final GoodThings newThings = new GoodThings(thingOneText, thingTwoText, thingThreeText, key);

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
