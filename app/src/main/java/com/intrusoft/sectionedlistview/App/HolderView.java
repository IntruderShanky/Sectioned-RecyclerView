package com.intrusoft.sectionedlistview.App;

import android.view.View;
import android.widget.TextView;

import com.intrusoft.sectionedlistview.R;
import com.intrusoft.sectionrecyclerview.SectionViewHolder;

/**
 * Created by apple on 10/2/16.
 */

public class HolderView extends SectionViewHolder {

    TextView sectionText;

    public HolderView(View fullSectionView, View sectionView, View childs) {
        super(fullSectionView, sectionView, childs);
        sectionText = (TextView) sectionView.findViewById(R.id.section);
    }
}
