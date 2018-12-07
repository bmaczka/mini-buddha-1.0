package com.example.blake.minibuddhav0;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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
        //contacts.add(new Contact(goodThingsChosen.getThingOne(), "date"));
        //contacts.add(new Contact("Andrew", "pedro_pic_two"));
        //contacts.add(new Contact("Nevin", "nevin_pic"));
        //contacts.add(new Contact("Sanjeev", "sanjeev_pic"));
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
}
