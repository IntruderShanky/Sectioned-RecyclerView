package com.intrusoft.sectionrecyclerview;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by apple on 10/2/16.
 */

public class SectionViewHolder extends RecyclerView.ViewHolder {

    public View getChilds() {
        return childs;
    }

    public View getSectionView() {
        return sectionView;
    }

    private View childs;
    private View sectionView;

    public SectionViewHolder(View fullSectionView, View sectionView, View child) {
        super(fullSectionView);
        this.childs = child;
        this.sectionView = sectionView;
    }
}
