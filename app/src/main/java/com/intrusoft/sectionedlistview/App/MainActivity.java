package com.intrusoft.sectionedlistview.App;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.intrusoft.sectionedlistview.R;
import com.intrusoft.sectionrecyclerview.SectionRecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        view = (RecyclerView) findViewById(R.id.recycler_view);
        view.setLayoutManager(new LinearLayoutManager(this));
        List<Childs> childsList = new ArrayList<>();
        childsList.add(new Childs("Apple"));
        childsList.add(new Childs("Microsoft"));
        childsList.add(new Childs("Google"));
        List<Sections> sections = new ArrayList<>();
        sections.add(new Sections(childsList, "FIRST"));
        childsList = new ArrayList<>();
        childsList.add(new Childs("Akash 1"));
        sections.add(new Sections(childsList, "Second"));
        childsList = new ArrayList<>();
        childsList.add(new Childs("Akash 2"));
        childsList.add(new Childs("Akash 3"));
        childsList.add(new Childs("Akash 4"));
        sections.add(new Sections(childsList, "Third"));
        childsList.add(new Childs("Apple"));
        childsList.add(new Childs("Microsoft"));
        childsList.add(new Childs("Google"));
        sections.add(new Sections(childsList, "FIRST"));
        childsList = new ArrayList<>();
        childsList.add(new Childs("Akash 1"));
        sections.add(new Sections(childsList, "Second"));
        childsList = new ArrayList<>();
        childsList.add(new Childs("Akash 2"));
        childsList.add(new Childs("Akash 3"));
        childsList.add(new Childs("Akash 4"));
        sections.add(new Sections(childsList, "Third"));


        AdapterSectionRecycler adapterRecycler = new AdapterSectionRecycler(this, sections, view, R.layout.section_layout, R.layout.item_layout);
        view.setAdapter(adapterRecycler);
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
