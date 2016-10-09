package com.intrusoft.sectionedlistview.App;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.intrusoft.sectionedlistview.R;
import com.intrusoft.sectionrecyclerview.SectionRecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //initialize RecyclerView
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        //setLayout Manager
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //Create a List of Child DataModel
        List<Childs> childsList = new ArrayList<>();
        childsList.add(new Childs("April"));
        childsList.add(new Childs("Austin"));
        childsList.add(new Childs("Alex"));
        childsList.add(new Childs("Aakash"));

        //Create a List of Section DataModel implements SectionItem
        List<Sections> sections = new ArrayList<>();
        sections.add(new Sections(childsList, "A"));

        childsList = new ArrayList<>();
        childsList.add(new Childs("Bill Gates"));
        childsList.add(new Childs("Bob Proctor"));
        childsList.add(new Childs("Bryan Tracy"));
        sections.add(new Sections(childsList, "B"));

        childsList = new ArrayList<>();
        childsList.add(new Childs("Intruder Shanky"));
        childsList.add(new Childs("Invincible Vinod"));
        sections.add(new Sections(childsList, "I"));

        childsList = new ArrayList<>();
        childsList.add(new Childs("Jim Carry"));
        sections.add(new Sections(childsList, "J"));

        childsList = new ArrayList<>();
        childsList.add(new Childs("Neil Patrick Harris"));
        sections.add(new Sections(childsList, "N"));

        childsList = new ArrayList<>();
        childsList.add(new Childs("Orange"));
        childsList.add(new Childs("Olive"));
        sections.add(new Sections(childsList, "O"));


        AdapterSectionRecycler adapterRecycler = new AdapterSectionRecycler(this, sections, recyclerView, R.layout.section_layout, R.layout.item_layout);
        recyclerView.setAdapter(adapterRecycler);
        adapterRecycler.setOnItemClickListener(new SectionRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int sectionPosition, int itemPosition) {
                Log.d(sectionPosition + "", itemPosition + "");
            }
        });

        adapterRecycler.setOnSectionClickListener(new SectionRecyclerViewAdapter.OnSectionClickListener() {
            @Override
            public void onSectionClick(int position) {
                Log.d("sectionPosition", position + "");
            }
        });

    }
}
