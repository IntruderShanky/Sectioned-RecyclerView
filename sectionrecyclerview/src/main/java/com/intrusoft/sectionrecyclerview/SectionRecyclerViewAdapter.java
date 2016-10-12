package com.intrusoft.sectionrecyclerview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;

import java.util.List;

/**
 * Created by apple on 10/2/16.
 */

public abstract class SectionRecyclerViewAdapter<SVH extends SectionViewHolder> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private List<? extends SectionItem> sections;
    private int sectionResId;
    private int childResId;
    private RecyclerView recyclerView;
    private OnItemClickListener onItemClickListener;
    private OnSectionClickListener onSectionClickListener;

    public SectionRecyclerViewAdapter(Context context, List<? extends SectionItem> sections,
                                      RecyclerView recyclerView, int sectionResId, int childResId) {
        this.context = context;
        this.sections = sections;
        this.sectionResId = sectionResId;
        this.childResId = childResId;
        this.recyclerView = recyclerView;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LinearLayout fullSectionView = new LinearLayout(context);
        fullSectionView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));
        fullSectionView.setOrientation(LinearLayout.VERTICAL);

        View sectionView = LayoutInflater.from(context).inflate(sectionResId, null);
        fullSectionView.addView(sectionView);

        ChildListView childs = new ChildListView(context);
        childs.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));
        fullSectionView.addView(childs);

        return onCreateSectionViewHolder(fullSectionView, sectionView, childs);
    }

    @SuppressWarnings("unchecked")
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {

        //Better not show to user toasts like this
//        if (holder == null) {
//            Toast.makeText(context, "NULL HOLDER", Toast.LENGTH_SHORT).show();
//        }

        final SVH sectionViewHolder = (SVH) holder;

        if (sectionViewHolder.getChilds() != null) {
            ChildListView childListView = (ChildListView) sectionViewHolder.getChilds();
            childListView.setAdapter(
                    new ChildListHelper(sections.get(position).getChildItems().size(), context, childResId) {
                        @Override
                        public View onChildCreateView(int p, View childView) {
                            Object childItem = sections.get(position).getChildItems().get(p);
                            return onBindChildView(p, childView, childItem);
                        }
                    });

            childListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int p, long id) {
                    if (onItemClickListener != null)
                        onItemClickListener.onItemClick(position, p);
                }
            });
        }

        onBindSectionView((SVH) holder, position, sections.get(position));

        sectionViewHolder.getSectionView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recyclerView.scrollToPosition(position);
                if (onSectionClickListener != null)
                    onSectionClickListener.onSectionClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return sections.size();
    }

    public abstract SVH onCreateSectionViewHolder(View fullSectionView, View sectionView, View child);

    public abstract void onBindSectionView(SVH sectionHolder, int position, SectionItem sectionItem);

    public abstract View onBindChildView(int position, View view, Object childItem);

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public void setOnSectionClickListener(OnSectionClickListener onSectionClickListener) {
        this.onSectionClickListener = onSectionClickListener;
    }

    public interface OnItemClickListener {
        void onItemClick(int sectionPosition, int itemPosition);
    }

    public interface OnSectionClickListener {
        void onSectionClick(int position);
    }
}
