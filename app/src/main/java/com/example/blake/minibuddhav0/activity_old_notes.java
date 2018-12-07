package com.example.blake.minibuddhav0;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class activity_old_notes extends AppCompatActivity {

    private ArrayList<Contact> contacts;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_old_notes);
        initContacts();
    }

    private void initContacts(){
        contacts = new ArrayList<>();
        contacts.add(new Contact("pedro", "pedro_pic"));
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
}
