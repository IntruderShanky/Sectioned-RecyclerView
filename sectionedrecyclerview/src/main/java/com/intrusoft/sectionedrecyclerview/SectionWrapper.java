package com.intrusoft.sectionedrecyclerview;

/**
 * Wrapper used to link metadata with a list item.
 *
 * @param <S> Section list item
 * @param <C> Child list item
 */
class SectionWrapper<S extends Section<C>, C> {

    private boolean sectionItem;
    private S section;
    private C child;
    private int sectionPosition;
    private int childPosition;

    /**
     * Constructor to wrap a section object of type {@link S}.
     *
     * @param section The parent object to wrap
     */
    public SectionWrapper(S section, int sectionPosition) {
        this.sectionItem = true;
        this.section = section;
        this.sectionPosition = sectionPosition;
        this.childPosition = -1;
    }

    /**
     * Constructor to wrap a child object of type {@link C}.
     *
     * @param child The child object to wrap
     */
    public SectionWrapper(C child, int sectionPosition, int childPosition) {
        this.child = child;
        this.sectionPosition = sectionPosition;
        this.sectionItem = false;
        this.childPosition = childPosition;
    }

    public boolean isSection() {
        return sectionItem;
    }

    public S getSection() {
        return section;
    }

    public C getChild() {
        return child;
    }

    public int getSectionPosition() {
        return sectionPosition;
    }

    public int getChildPosition() {
        if (childPosition == -1)
            throw new IllegalAccessError("This is not child");
        return childPosition;
    }
}
