package com.intrusoft.sectionedrecyclerviewapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    AdapterSectionRecycler adapterRecycler;
    List<SectionHeader> sectionHeaders;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //initialize RecyclerView
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        //setLayout Manager
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);

        //Create a List of Child DataModel
        List<Child> childList = new ArrayList<>();
        childList.add(new Child("April"));
        childList.add(new Child("Austin"));
        childList.add(new Child("Alex"));
        childList.add(new Child("Aakash"));

        //Create a List of SectionHeader DataModel implements SectionHeader
        sectionHeaders = new ArrayList<>();
        sectionHeaders.add(new SectionHeader(childList, "A", 6));

        childList = new ArrayList<>();
        childList.add(new Child("Bill Gates"));
        childList.add(new Child("Bob Proctor"));
        childList.add(new Child("Bryan Tracy"));
        sectionHeaders.add(new SectionHeader(childList, "B", 2));

        childList = new ArrayList<>();
        childList.add(new Child("Intruder Shanky"));
        childList.add(new Child("Invincible Vinod"));
        sectionHeaders.add(new SectionHeader(childList, "I", 1));

        childList = new ArrayList<>();
        childList.add(new Child("Jim Carry"));
        sectionHeaders.add(new SectionHeader(childList, "J", 4));

        childList = new ArrayList<>();
        childList.add(new Child("Neil Patrick Harris"));
        sectionHeaders.add(new SectionHeader(childList, "N", 3));

        childList = new ArrayList<>();
        childList.add(new Child("Orange"));
        childList.add(new Child("Olive"));
        sectionHeaders.add(new SectionHeader(childList, "O", 5));

        adapterRecycler = new AdapterSectionRecycler(this, sectionHeaders);
        recyclerView.setAdapter(adapterRecycler);
    }

}