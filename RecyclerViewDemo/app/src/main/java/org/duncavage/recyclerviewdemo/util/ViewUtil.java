package org.duncavage.recyclerviewdemo.util;

import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by brett on 5/31/15.
 */
public class ViewUtil {
    /**
     * Returns true if the provided view or any of it's children (or children's children, etc)
     * contain the touch point and have a click listener.
     * @param view
     * @param event
     * @return
     */
    public static boolean isTouchInsideViewWithClickListener(View view, MotionEvent event) {
        if(view == null) {
            return false;
        }

        if (!(view instanceof ViewGroup)) {
            return view.hasOnClickListeners();
        } else if (((ViewGroup)view).getChildCount() == 0) {
            return view.hasOnClickListeners();
        }

        ViewGroup viewGroup = (ViewGroup)view;
        for (int i = 0; i < viewGroup.getChildCount(); i++) {
            View child = viewGroup.getChildAt(i);
            if (ViewUtil.isPointInsideView(event.getRawX(), event.getRawY(), child)){
                if (isTouchInsideViewWithClickListener(child, event)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Returns true if the point is within the view's bounds.
     * @param x
     * @param y
     * @param view
     * @return
     */
    public static boolean isPointInsideView(float x, float y, View view){
        int location[] = new int[2];
        view.getLocationOnScreen(location);
        int viewX = location[0];
        int viewY = location[1];

        //point is inside view bounds
        if(( x > viewX && x < (viewX + view.getWidth())) &&
                ( y > viewY && y < (viewY + view.getHeight()))){
            return true;
        } else {
            return false;
        }
    }
}
