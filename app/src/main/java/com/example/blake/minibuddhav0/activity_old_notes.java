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
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static android.app.NotificationChannel.DEFAULT_CHANNEL_ID;
import static android.app.NotificationManager.IMPORTANCE_NONE;
import static android.icu.lang.UProperty.NAME;

public class activity_old_notes extends AppCompatActivity implements View.OnClickListener {

    private Button sortButton, notificationsButton, toHomeButton;
    private DatabaseReference dbref;
    private String user = User.name;
    private String thingOne, thingTwo, thingThree, date;


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
        /*
        createNotificationChannel();
        Notification notification = new Notification.Builder(activity_old_notes.this)
                .setContentTitle("title")
                .setContentText("text")
                .setSmallIcon(R.mipmap.ic_launcher_round)
                .setChannelId(DEFAULT_CHANNEL_ID)
                .build();

        */
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
                Random rand = new Random();
                int randIndex = rand.nextInt(L.size());
                GoodThings goodThingsChosen = new GoodThings();
                goodThingsChosen = L.get(randIndex).getValue(GoodThings.class);
                contacts.add(new Contact(goodThingsChosen.getThingOne()));
                contacts.add(new Contact(goodThingsChosen.getThingTwo()));
                contacts.add(new Contact(goodThingsChosen.getThingThree()));
                Random rand2 = new Random();
                int randIndex2 = rand2.nextInt(L.size());
                GoodThings goodThingsChosen2 = new GoodThings();
                goodThingsChosen2 = L.get(randIndex2).getValue(GoodThings.class);
                contacts.add(new Contact(goodThingsChosen2.getThingOne()));
                contacts.add(new Contact(goodThingsChosen2.getThingTwo()));
                contacts.add(new Contact(goodThingsChosen2.getThingThree()));
                Random rand3 = new Random();
                int randIndex3 = rand3.nextInt(L.size());
                GoodThings goodThingsChosen3 = new GoodThings();
                goodThingsChosen3 = L.get(randIndex3).getValue(GoodThings.class);
                contacts.add(new Contact(goodThingsChosen3.getThingOne()));
                contacts.add(new Contact(goodThingsChosen3.getThingTwo()));
                contacts.add(new Contact(goodThingsChosen3.getThingThree()));
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
        else if(view == notificationsButton){ /*
            //todo: configure notifications that recycle saved things back to user
            Toast.makeText(this, "Great success",Toast.LENGTH_SHORT).show();
            NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this, DEFAULT_CHANNEL_ID)
                    .setSmallIcon(R.mipmap.ic_launcher_round)
                    .setContentTitle("My notification")
                    .setContentText("Much longer text that cannot fit one line...")
                    .setStyle(new NotificationCompat.BigTextStyle()
                            .bigText("Much longer text that cannot fit one line..."))
                    .setPriority(NotificationCompat.PRIORITY_DEFAULT);

            // Create an explicit intent for an Activity in your app
            Intent intent = new Intent(this, activity_old_notes.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);


            NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
            int notificationId = 8;
            // notificationId is a unique int for each notification that you must define
            notificationManager.notify(notificationId, mBuilder.build());

            */
        }
        else if(view == toHomeButton){
            Intent HomePageIntent = new Intent(activity_old_notes.this, ThreeGoodThings.class);
            startActivity(HomePageIntent);
        }
    }
    /*
    private void createNotificationChannel() {
        CharSequence channelName = "Some Channel";
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = channelName;
            String description = getString(IMPORTANCE_NONE);
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(DEFAULT_CHANNEL_ID, name, importance);
            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }
    */
}
