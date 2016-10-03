package com.intrusoft.sectionedlistview.App;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.intrusoft.sectionedlistview.R;
import com.intrusoft.sectionrecyclerview.SectionRecyclerViewAdapter;
import com.intrusoft.sectionrecyclerview.SectionItem;

import java.util.List;

/**
 * Created by apple on 10/2/16.
 */

public class AdapterSectionRecycler extends SectionRecyclerViewAdapter<HolderView> {

    List<Sections> sections;
    Context context;


    public AdapterSectionRecycler(Context context, List<Sections> sections, RecyclerView recyclerView, int sectionResId, int childResId) {
        super(context, sections, recyclerView, sectionResId, childResId);
        this.sections = sections;
        this.context =context;
    }

    @Override
    public HolderView onCreateSectionViewHolder(View fullSectionView, View sectionView, View child) {
        if(child == null){
            Toast.makeText(context, "NULL CHILD", Toast.LENGTH_SHORT).show();
        }
        return new HolderView(fullSectionView, sectionView, child);
    }

    @Override
    public void onBindSectionView(HolderView sectionHolder, int position, SectionItem sectionItem) {
        sectionHolder.sectionText.setText(sections.get(position).sectionText);
    }

    @Override
    public View onBindChildView(int position, View view, Object childItem) {
        Childs child = (Childs) childItem;
        TextView childText = (TextView) view.findViewById(R.id.child);
        childText.setText(child.getName());
        return view;
    }
}
