package com.intrusoft.sectionedlistview.App;

import com.intrusoft.sectionrecyclerview.SectionItem;

import java.util.List;

public class Sections implements SectionItem {

    List<Childs> childsList;
    String sectionText;

    public Sections(List<Childs> childsList, String sectionText) {
        this.childsList = childsList;
        this.sectionText = sectionText;
    }

    @Override
    public List<?> getChildItems() {
        return childsList;
    }

    public String getSectionText() {
        return sectionText;
    }
}
