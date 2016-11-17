package com.intrusoft.sectionedrecyclerviewapp;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

/**
 * Created by shanky on 11/12/2016.
 */

public class SectionViewHolder extends RecyclerView.ViewHolder {

    TextView name;
    public SectionViewHolder(View itemView) {
        super(itemView);
         name = (TextView) itemView.findViewById(R.id.sectionHeader);
    }
}
