package com.intrusoft.sectionrecyclerview;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by apple on 10/2/16.
 */

public class SectionViewHolder extends RecyclerView.ViewHolder {

    View childs;
    View sectionView;

    public SectionViewHolder(View fullSectionView, View sectionView, View child) {
        super(fullSectionView);
        this.childs = child;
        this.sectionView = sectionView;
    }
}
