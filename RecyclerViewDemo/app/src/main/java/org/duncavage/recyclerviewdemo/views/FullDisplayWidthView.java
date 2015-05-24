package org.duncavage.recyclerviewdemo.views;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;

/**
 * Created by brett on 4/22/15.
 *
 * This is a special FrameLayout subclass that forces its width to be as large as the display's
 * width. Essentially it ignores width constraints desired by the parent it is inside.
 *
 * The main use-case for this class is for views inside a parent that wish to have full-bleed, but
 * the parent is imposing side padding. For example, items in a grid that span all columns
 * generally want full-bleed (i.e. a carousel in a grid), but our grids enforce a standard side
 * padding.
 */
public class FullDisplayWidthView extends FrameLayout {
    public FullDisplayWidthView(Context context) {
        super(context);
    }

    public FullDisplayWidthView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public FullDisplayWidthView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int displayWidth = getResources().getDisplayMetrics().widthPixels;
        super.onMeasure(MeasureSpec.makeMeasureSpec(displayWidth, MeasureSpec.EXACTLY), heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        int displayWidth = getResources().getDisplayMetrics().widthPixels;
        setLeft(0);
        setRight(displayWidth);
        super.onLayout(changed, 0, top, displayWidth, bottom);
    }
}
