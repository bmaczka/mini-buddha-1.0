package com.example.blake.minibuddhav0;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import static android.app.NotificationChannel.DEFAULT_CHANNEL_ID;
import static android.app.NotificationManager.IMPORTANCE_NONE;
import static android.icu.lang.UProperty.NAME;
import static com.example.blake.minibuddhav0.NotificationUtils.ANDROID_CHANNEL_ID;
import static com.example.blake.minibuddhav0.NotificationUtils.IOS_CHANNEL_ID;

public class activity_old_notes extends AppCompatActivity implements View.OnClickListener {

    private Button sortButton, notificationsButton, toHomeButton;
    private DatabaseReference dbref;
    private String user = User.name;
    private String thingOne, thingTwo, thingThree, date, key;
    private NotificationUtils mNotificationUtils;


    private ArrayList<Contact> contacts;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_old_notes);
        initContacts();
        sortButton = findViewById(R.id.sortButton);
        notificationsButton = findViewById(R.id.notificationsButton);
        toHomeButton = findViewById(R.id.toHomeButton);
        sortButton.setOnClickListener(this);
        notificationsButton.setOnClickListener(this);
        toHomeButton.setOnClickListener(this);
        mNotificationUtils = new NotificationUtils(this);


        notificationsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbref = FirebaseDatabase.getInstance().getReference(user);
                dbref.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        List<DataSnapshot> L = new ArrayList<>();
                        for(DataSnapshot d: dataSnapshot.getChildren()){
                            L.add(d);
                        }
                        Random rand = new Random();
                        int randIndex = rand.nextInt(L.size());
                        GoodThings goodThingNotification = new GoodThings();
                        goodThingNotification = L.get(randIndex).getValue(GoodThings.class);
                        String thing = goodThingNotification.getThingOne();
                        String dateDay = Integer.toString(goodThingNotification.getDate().getDay());
                        String dateMonth = Integer.toString(goodThingNotification.getDate().getMonth());
                        //String dateYear = Integer.toString(goodThingNotification.getDate().getYear());
                        String date = dateMonth + "/" + dateDay;

                        if(!TextUtils.isEmpty(thing) && !TextUtils.isEmpty(date)) {
                            Notification.Builder nb = mNotificationUtils.getAndroidChannelNotification(thing, "recorded on:  " + date);
                            mNotificationUtils.getManager().notify(101, nb.build());
                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                    }
                });
            }
        });
    }

    private void initContacts(){
        contacts = new ArrayList<>();
        dbref = FirebaseDatabase.getInstance().getReference(user);
        dbref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                List<DataSnapshot> L = new ArrayList<>();
                for(DataSnapshot d: dataSnapshot.getChildren()){
                    L.add(d);
                }
                /*
                Random rand = new Random();
                int randIndex = rand.nextInt(L.size());
                GoodThings goodThingsChosen = new GoodThings();
                goodThingsChosen = L.get(randIndex).getValue(GoodThings.class);
                contacts.add(new Contact(goodThingsChosen.getThingOne()));
                contacts.add(new Contact(goodThingsChosen.getThingTwo()));
                contacts.add(new Contact(goodThingsChosen.getThingThree()));
                thingOne = goodThingsChosen.getThingOne();
                Random rand2 = new Random();
                int randIndex2 = rand2.nextInt(L.size());
                GoodThings goodThingsChosen2 = new GoodThings();
                goodThingsChosen2 = L.get(randIndex2).getValue(GoodThings.class);
                //if below ensures that the same set of things isn't selected twice
                if(goodThingsChosen2.getThingOne() == thingOne){
                    rand2 = new Random();
                }
                randIndex2 = rand2.nextInt(L.size());
                goodThingsChosen2 = L.get(randIndex2).getValue(GoodThings.class);
                contacts.add(new Contact(goodThingsChosen2.getThingOne()));
                contacts.add(new Contact(goodThingsChosen2.getThingTwo()));
                contacts.add(new Contact(goodThingsChosen2.getThingThree()));
                thingTwo = goodThingsChosen2.getThingOne();
                Random rand3 = new Random();
                int randIndex3 = rand3.nextInt(L.size());
                GoodThings goodThingsChosen3 = new GoodThings();
                goodThingsChosen3 = L.get(randIndex3).getValue(GoodThings.class);
                //if below ensures that the same set of things isn't selected twice
                if(goodThingsChosen3.getThingOne() == thingOne || goodThingsChosen3.getThingOne() == thingTwo){
                    rand3 = new Random();
                }
                randIndex3 = rand3.nextInt(L.size());
                goodThingsChosen3 = L.get(randIndex3).getValue(GoodThings.class);
                contacts.add(new Contact(goodThingsChosen3.getThingOne()));
                contacts.add(new Contact(goodThingsChosen3.getThingTwo()));
                contacts.add(new Contact(goodThingsChosen3.getThingThree()));
                */
                // Implementation of sortList()
                List<GoodThings> publishList = new ArrayList<>();
                publishList = sortList(L);
                for (int i = 0; i < publishList.size(); i++) {
                    contacts.add(new Contact (publishList.get(i).getThingOne()));
                    contacts.add(new Contact (publishList.get(i).getThingTwo()));
                    contacts.add(new Contact (publishList.get(i).getThingThree()));
                }

                initRecyclerView();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
        initRecyclerView();
    }

    private void initRecyclerView(){
        RecyclerView recyclerView = findViewById(R.id.oldThingsRecyclerView);
        RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter(contacts, this);
        recyclerView.setAdapter(recyclerViewAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void onClick(View view) {
        if(view == sortButton){
            //todo: sort things by either newest or oldest
        }
        else if(view == notificationsButton){
            //todo: configure notifications that recycle saved things back to user

        }
        else if(view == toHomeButton){
            Intent HomePageIntent = new Intent(activity_old_notes.this, ThreeGoodThings.class);
            startActivity(HomePageIntent);
        }
    }


    public Notification.Builder getAndroidChannelNotification(String title, String body) {
        return new Notification.Builder(getApplicationContext(), ANDROID_CHANNEL_ID)
                .setContentTitle("title")
                .setContentText("body")
                .setSmallIcon(android.R.drawable.stat_notify_more)
                .setAutoCancel(true);
    }

    // IDEA: Sort list of Good Things by data, newest to oldest, to be published to screen in
    // initContacts() function. Uses for loops to check if Good Things in initContacts() are ordered
    // by date and then reorders if needed
    public ArrayList<GoodThings> sortList(List<DataSnapshot> dbList) {

        // Do this for 9 GoodThings objects

        List<GoodThings> gtList = new ArrayList<>();

        // Checks dates against date of first ArrayList item
        // ArrayLists are 0 indexed
        for (int c = 1; c < dbList.size(); c++) {

            GoodThings firstGT = dbList.get(0).getValue(GoodThings.class);
            GoodThings cGT = dbList.get(c).getValue(GoodThings.class);
            Date firstDate = firstGT.getDate();
            Date cDate = cGT.getDate();
            // Compares cDate to firstDate
            if(c ==1){
                gtList.add(0, cGT);
                gtList.add(c, firstGT);
            }
            // If cDate is newer it places it at index 0 and moves previous 1st index to c index
            else if (firstDate.compareTo(cDate) < 0) {
                gtList.set(0, cGT);
                gtList.add(c, cGT); //this line used to pass firstGT as its 2nd arg but this was giving duplication errors
            }
            /*
            if (firstDate.compareTo(cDate) < 0) {
                gtList.add(0, cGT);
                gtList.add(c, firstGT);
            }
            */
        }

        // Check dates against date of second ArrayList item
        for (int count = 2; count < dbList.size(); count++) {

            GoodThings secondGT = dbList.get(1).getValue(GoodThings.class);
            GoodThings countGT = dbList.get(count).getValue(GoodThings.class);
            Date secondDate = secondGT.getDate();
            Date countDate = countGT.getDate();

            // Compares countDate to secondDate
            // if countDate is newer it places it at index 1 and
            // moves previous second index to count index
            if (secondDate.compareTo(countDate) < 0) {
                gtList.set(1, countGT);
                gtList.set(count, secondGT);
            }
        }

        // Check dates against date of third ArrayList item
        for (int counter = 3; counter < dbList.size(); counter++) {

            GoodThings thirdGT = dbList.get(2).getValue(GoodThings.class);
            GoodThings counterGT = dbList.get(counter).getValue(GoodThings.class);
            Date thirdDate = thirdGT.getDate();
            Date counterDate = counterGT.getDate();

            if (thirdDate.compareTo(counterDate) < 0) {
                gtList.set(2, counterGT);
                gtList.set(counter, counterGT);
            }
        }

        // Check dates against date of fourth ArrayList item
        for (int c4 = 4; c4 < dbList.size(); c4++) {

            GoodThings fourthGT = dbList.get(3).getValue(GoodThings.class);
            GoodThings c4GT = dbList.get(c4).getValue(GoodThings.class);
            Date fourthDate = fourthGT.getDate();
            Date c4Date = c4GT.getDate();

            if (fourthDate.compareTo(c4Date) < 0) {
                gtList.set(3, c4GT);
                gtList.set(c4, fourthGT);
            }
        }

        // Checks dates against date of fifth ArrayList item
        for (int c5 = 5; c5 < dbList.size(); c5++) {

            GoodThings fifthGT = dbList.get(4).getValue(GoodThings.class);
            GoodThings c5GT = dbList.get(c5).getValue(GoodThings.class);
            Date fifthDate = fifthGT.getDate();
            Date c5Date = c5GT.getDate();

            if (fifthDate.compareTo(c5Date) < 0) {
                gtList.set(4, c5GT);
                gtList.set(c5, fifthGT);
            }
        }

        // Checks dates against date of sixth ArrayList item
        for (int c6 = 6; c6 < dbList.size(); c6++) {

            GoodThings sixthGT = dbList.get(5).getValue(GoodThings.class);
            GoodThings c6GT = dbList.get(c6).getValue(GoodThings.class);
            Date sixthDate = sixthGT.getDate();
            Date c6Date = c6GT.getDate();

            if (sixthDate.compareTo(c6Date) < 0) {
                gtList.set(5, c6GT);
                gtList.set(c6, sixthGT);
            }
        }

        // Checks dates against date of seventh ArrayList item
        for (int c7 = 7; c7 < dbList.size(); c7++) {

            GoodThings seventhGT = dbList.get(6).getValue(GoodThings.class);
            GoodThings c7GT = dbList.get(c7).getValue(GoodThings.class);
            Date seventhDate = seventhGT.getDate();
            Date c7Date = c7GT.getDate();

            if (seventhDate.compareTo(c7Date) < 0) {
                gtList.set(6, c7GT);
                gtList.set(c7, seventhGT);
            }
        }

        // Checks dates against eight ArrayList item
        for (int c8 = 8; c8 < dbList.size(); c8++) {

            GoodThings eigthGT = dbList.get(7).getValue(GoodThings.class);
            GoodThings c8GT = dbList.get(c8).getValue(GoodThings.class);
            Date eigthDate = eigthGT.getDate();
            Date c8Date = c8GT.getDate();

            if (eigthDate.compareTo(c8Date) < 0) {
                gtList.set(7, c8GT);
                gtList.set(c8, eigthGT);
            }
        }

        return (ArrayList<GoodThings>) gtList;
    }

}
