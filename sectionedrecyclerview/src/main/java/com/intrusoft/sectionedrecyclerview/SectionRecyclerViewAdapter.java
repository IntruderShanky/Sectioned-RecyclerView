package com.intrusoft.sectionedrecyclerview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * RecyclerView.Adapter implementation that
 * adds the ability to manage Sections and their child.
 * <p>
 * Changes should be notified through:
 * {@link #insertNewSection(Section)}
 * {@link #insertNewSection(Section, int)}
 * {@link #removeSection(int)}
 * {@link #insertNewChild(Object, int)}
 * {@link #insertNewChild(Object, int, int)}
 * {@link #removeChild(int, int)}
 * methods and not the notify methods of RecyclerView.Adapter.
 */
public abstract class SectionRecyclerViewAdapter<S extends Section<C>, C, SVH extends RecyclerView.ViewHolder, CVH extends RecyclerView.ViewHolder>
        extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<S> sectionItemList;

    /**
     * Default ViewType for section rows
     */
    private int SECTION_VIEW_TYPE = 1;

    /**
     * Default ViewType for child rows
     */
    private int CHILD_VIEW_TYPE = 2;

    /**
     * A {@link List} of all sections and their children, in order.
     * Changes to this list should be made through the add/remove methods
     * available in {@link SectionRecyclerViewAdapter}.
     */
    private List<SectionWrapper<S, C>> flatItemList;

    public SectionRecyclerViewAdapter(Context context, List<S> sectionItemList) {
        this.sectionItemList = sectionItemList;
        this.flatItemList = generateFlatItemList(sectionItemList);
    }

    /**
     * Implementation of Adapter.onCreateViewHolder(ViewGroup, int)
     * that determines if the list item is a section or a child and calls through
     * to the appropriate implementation of either {@link #onCreateSectionViewHolder(ViewGroup, int)}
     * or {@link #onCreateChildViewHolder(ViewGroup, int)}.
     *
     * @param viewGroup The {@link ViewGroup} into which the new {@link android.view.View}
     *                  will be added after it is bound to an adapter position.
     * @param viewType  The view type of the new {@code android.view.View}.
     * @return A new RecyclerView.ViewHolder
     * that holds a {@code android.view.View} of the given view type.
     */
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        if (isSectionViewType(viewType)) {
            SVH sectionViewHolder = onCreateSectionViewHolder(viewGroup, viewType);
            return sectionViewHolder;
        } else {
            CVH childViewHolder = onCreateChildViewHolder(viewGroup, viewType);
            return childViewHolder;
        }
    }


    /**
     * Implementation of Adapter.onBindViewHolder(RecyclerView.ViewHolder, int)
     * that determines if the list item is a section or a child and calls through
     * to the appropriate implementation of either
     * {@link #onBindSectionViewHolder(RecyclerView.ViewHolder, int, Section)} or
     * {@link #onBindChildViewHolder(RecyclerView.ViewHolder, int, int, Object)}.
     *
     * @param holder The RecyclerView.ViewHolder to bind data to
     * @param flatPosition The index in the merged list of children and parents at which to bind
     */
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int flatPosition) {
        if (flatPosition > flatItemList.size()) {
            throw new IllegalStateException("Trying to bind item out of bounds, size " + flatItemList.size()
                    + " flatPosition " + flatPosition + ". Was the data changed without a call to notify...()?");
        }
        SectionWrapper<S, C> sectionWrapper = flatItemList.get(flatPosition);
        if (sectionWrapper.isSection()) {
            SVH sectionViewHolder = (SVH) holder;
            onBindSectionViewHolder(sectionViewHolder, sectionWrapper.getSectionPosition(), sectionWrapper.getSection());
        } else {
            CVH childViewHolder = (CVH) holder;
            onBindChildViewHolder(childViewHolder, sectionWrapper.getSectionPosition(), sectionWrapper.getChildPosition(), sectionWrapper.getChild());
        }
    }

    /**
     * Callback called from {@link #onCreateViewHolder(ViewGroup, int)} when
     * the list item created is a section.
     *
     * @param sectionViewGroup The {@link ViewGroup} in the list for which a {@link SVH} is being
     *                        created
     * @return A {@code SVH} corresponding to the parent with the {@code ViewGroup} parentViewGroup
     */
    public abstract SVH onCreateSectionViewHolder(ViewGroup sectionViewGroup, int viewType);

    /**
     * Callback called from {@link #onCreateViewHolder(ViewGroup, int)} when
     * the list item created is a child.
     *
     * @param childViewGroup The {@link ViewGroup} in the list for which a {@link CVH}
     *                       is being created
     * @return A {@code CVH} corresponding to the child with the {@code ViewGroup} childViewGroup
     */
    public abstract CVH onCreateChildViewHolder(ViewGroup childViewGroup, int viewType);

    /**
     * Callback called from onBindViewHolder(RecyclerView.ViewHolder, int)
     * when the list item bound to is a section.
     * <p>
     * Bind data to the {@link SVH} here.
     *
     * @param sectionViewHolder The {@code SVH} to bind data to
     * @param sectionPosition The position of the parent to bind
     * @param section The parent which holds the data to be bound to the {@code SVH}
     */
    public abstract void onBindSectionViewHolder(SVH sectionViewHolder, int sectionPosition, S section);

    /**
     * Callback called from onBindViewHolder(RecyclerView.ViewHolder, int)
     * when the list item bound to is a child.
     * <p>
     * Bind data to the {@link CVH} here.
     *
     * @param childViewHolder The {@code CVH} to bind data to
     * @param sectionPosition The position of the parent that contains the child to bind
     * @param childPosition The position of the child to bind
     * @param child The child which holds that data to be bound to the {@code CVH}
     */
    public abstract void onBindChildViewHolder(CVH childViewHolder, int sectionPosition, int childPosition, C child);

    private void generateSectionWrapper(List<SectionWrapper<S, C>> flatItemList, S section, int sectionPosition) {
        SectionWrapper<S, C> sectionWrapper = new SectionWrapper<S, C>(section, sectionPosition);
        flatItemList.add(sectionWrapper);
        List<C> childList = section.getChildItems();
        for (int i = 0; i < childList.size(); i++) {
            SectionWrapper<S, C> childWrapper = new SectionWrapper<S, C>(childList.get(i), sectionPosition, i);
            flatItemList.add(childWrapper);
        }

    }

    /**
     * Generates a full list of all sections and their children, in order.
     *
     * @param sectionItemList A list of the sections from
     *                   the {@link SectionRecyclerViewAdapter}
     * @return A list of all sections and their children
     */
    private List<SectionWrapper<S, C>> generateFlatItemList(List<S> sectionItemList) {
        List<SectionWrapper<S, C>> flatItemList = new ArrayList<>();
        for (int i = 0; i < sectionItemList.size(); i++) {
            S section = sectionItemList.get(i);
            generateSectionWrapper(flatItemList, section, i);
        }
        return flatItemList;
    }

    @Override
    public int getItemCount() {
        return flatItemList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return flatItemList.get(position).isSection() ? SECTION_VIEW_TYPE : CHILD_VIEW_TYPE;
    }


    public boolean isSectionViewType(int viewType) {
        return viewType == SECTION_VIEW_TYPE;
    }

    public void insertNewSection(S section) {
        insertNewSection(section, sectionItemList.size());
    }

    public void insertNewSection(S section, int sectionPosition) {
        if (sectionPosition > sectionItemList.size() || sectionPosition < 0)
            throw new IndexOutOfBoundsException("sectionPosition =  " + sectionPosition + " , Size is " + sectionItemList.size());
        flatItemList = new ArrayList<>();
        flatItemList = generateFlatItemList(sectionItemList);
        notifyDataSetChanged();
    }

    public void removeSection(int sectionPosition) {
        if (sectionPosition > sectionItemList.size() - 1 || sectionPosition < 0)
            throw new IndexOutOfBoundsException("sectionPosition =  " + sectionPosition + " , Size is " + sectionItemList.size());
        sectionItemList.remove(sectionPosition);
        flatItemList = new ArrayList<>();
        flatItemList = generateFlatItemList(sectionItemList);
        notifyDataSetChanged();
    }

    public void insertNewChild(C child, int sectionPosition) {
        if (sectionPosition > sectionItemList.size() - 1 || sectionPosition < 0)
            throw new IndexOutOfBoundsException("Invalid sectionPosition =  " + sectionPosition + " , Size is " + sectionItemList.size());
        insertNewChild(child, sectionPosition, sectionItemList.get(sectionPosition).getChildItems().size());
    }

    public void insertNewChild(C child, int sectionPosition, int childPosition) {
        if (sectionPosition > sectionItemList.size() - 1 || sectionPosition < 0)
            throw new IndexOutOfBoundsException("Invalid sectionPosition =  " + sectionPosition + " , Size is " + sectionItemList.size());
        if (childPosition > sectionItemList.get(sectionPosition).getChildItems().size() || childPosition < 0)
            throw new IndexOutOfBoundsException("Invalid childPosition =  " + childPosition + " , Size is " + sectionItemList.get(sectionPosition).getChildItems().size());
        sectionItemList.get(sectionPosition).getChildItems().add(childPosition, child);
        flatItemList = new ArrayList<>();
        flatItemList = generateFlatItemList(sectionItemList);
        notifyDataSetChanged();
    }

    public void removeChild(int sectionPosition, int childPosition) {
        if (sectionPosition > sectionItemList.size() - 1 || sectionPosition < 0)
            throw new IndexOutOfBoundsException("Invalid sectionPosition =  " + sectionPosition + " , Size is " + sectionItemList.size());
        if (childPosition > sectionItemList.get(sectionPosition).getChildItems().size() - 1 || childPosition < 0)
            throw new IndexOutOfBoundsException("Invalid childPosition =  " + childPosition + " , Size is " + sectionItemList.get(sectionPosition).getChildItems().size());
        sectionItemList.get(sectionPosition).getChildItems().remove(childPosition);
        flatItemList = new ArrayList<>();
        flatItemList = generateFlatItemList(sectionItemList);
        notifyDataSetChanged();
    }
}
