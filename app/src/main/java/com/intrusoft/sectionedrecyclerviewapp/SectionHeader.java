package com.intrusoft.sectionedrecyclerviewapp;

import com.intrusoft.sectionedrecyclerview.Section;

import java.util.List;

/**
 * Created by apple on 11/7/16.
 */

public class SectionHeader implements Section<Child> {

    List<Child> childList;
    String sectionText;

    public SectionHeader(List<Child> childList, String sectionText) {
        this.childList = childList;
        this.sectionText = sectionText;
    }

    @Override
    public List<Child> getChildItems() {
        return childList;
    }

    public String getSectionText() {
        return sectionText;
    }
}