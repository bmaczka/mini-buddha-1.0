package com.example.blake.minibuddhav0;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class activity_old_notes extends AppCompatActivity implements View.OnClickListener {

    private Button sortButton, notificationsButton, toHomeButton;


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
        contacts.add(new Contact("Pedro", "pedro_pic"));
        contacts.add(new Contact("Andrew", "pedro_pic_two"));
        contacts.add(new Contact("Nevin", "nevin_pic"));
        contacts.add(new Contact("Sanjeev", "sanjeev_pic"));
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

        }
        else if(view == notificationsButton){

        }
        else if(view == toHomeButton){
            Intent HomePageIntent = new Intent(activity_old_notes.this, ThreeGoodThings.class);
            startActivity(HomePageIntent);
        }
    }
}
