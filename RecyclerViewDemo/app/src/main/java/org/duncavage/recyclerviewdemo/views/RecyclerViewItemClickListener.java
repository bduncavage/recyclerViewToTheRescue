package org.duncavage.recyclerviewdemo.views;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import org.duncavage.recyclerviewdemo.util.ViewUtil;

/**
 * Created by brett on 5/31/15.
 */
public class RecyclerViewItemClickListener implements RecyclerView.OnItemTouchListener{
    private OnItemGestureListener mListener;
    private View viewUnderTouch;
    private RecyclerView recyclerView;

    public interface OnItemGestureListener {
        public void onItemClick(View view, int position);
        public void onItemLongClick(View view, int position);
    }

    GestureDetector mGestureDetector;

    public RecyclerViewItemClickListener(Context context, OnItemGestureListener listener) {
        mListener = listener;
        mGestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
            @Override public boolean onSingleTapUp(MotionEvent e) {
                return true;
            }
            @Override public void onLongPress(MotionEvent e) {
                int position = recyclerView.getChildAdapterPosition(viewUnderTouch);
                if (position != RecyclerView.NO_POSITION) {
                    mListener.onItemLongClick(viewUnderTouch, position);
                }
            }
        });
        mGestureDetector.setIsLongpressEnabled(true);
    }

    @Override
    public boolean onInterceptTouchEvent(RecyclerView view, MotionEvent e) {
        View childView = view.findChildViewUnder(e.getX(), e.getY());

        /* Return false early if we know that the click has been made on a subview
         * that has an onClick listener set on it. This will allow propagating the
         * click event to child views.
         */
        if(ViewUtil.isTouchInsideViewWithClickListener(childView, e)) {
            return false;
        }

        /* Else we consider the click event for the entire recycler view item */
        viewUnderTouch = childView;
        recyclerView = view;
        if (childView != null && mListener != null && mGestureDetector.onTouchEvent(e)) {
            mListener.onItemClick(childView, view.getChildAdapterPosition(childView));
        }
        return false;
    }

    @Override
    public void onTouchEvent(RecyclerView view, MotionEvent motionEvent) { }
}
