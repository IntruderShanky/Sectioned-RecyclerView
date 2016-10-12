package com.intrusoft.sectionrecyclerview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

/**
 * Created by apple on 10/2/16.
 */
abstract class ChildListHelper extends BaseAdapter {

    private int numOfItems;
    private Context context;
    private int resId;

    public ChildListHelper(int numOfItems, Context context, int resId) {
        this.numOfItems = numOfItems;
        this.context = context;
        this.resId = resId;
    }

    @Override
    public int getCount() {
        return numOfItems;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(resId, null);
        }
        convertView = onChildCreateView(position, convertView);
        return convertView;
    }

    public abstract View onChildCreateView(int position, View childView);
}
