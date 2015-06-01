package org.duncavage.recyclerviewdemo.adapters;

import android.os.Handler;
import android.os.Looper;
import android.support.v4.view.animation.LinearOutSlowInInterpolator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.toolbox.ImageLoader;

import org.duncavage.recyclerviewdemo.viewholders.ListItemViewHolder;
import org.duncavage.recyclerviewdemo.viewmodels.ListItemViewModel;

import java.util.List;

/**
 * Created by brett on 5/21/15.
 */
public class ListItemViewHolderAdapter<T extends ListItemViewModel>
        extends RecyclerView.Adapter<ListItemViewHolder>
        implements CRUDAdapter {
    private static final int ANIMATION_DELAY_INTERVAL = 50;

    private List<T> viewModels;
    private int lastAnimatedPosition = -1;
    private long nextAnimationStartTime;
    private boolean animateItemsOnScroll = true;
    private int defaultItemAnimationDuration = 0;

    private final ImageLoader imageLoader;
    private final Handler handler = new Handler(Looper.getMainLooper());
    private final SpanSizeLookup spanSizeLookup = new SpanSizeLookup();

    public enum AnimationDirection {
        UpFromBottom,
        DownFromTop,
        InFromLeft,
        InFromRight,
    }

    public ListItemViewHolderAdapter(List<T> viewModels, ImageLoader imageLoader) {
        this.viewModels = viewModels;
        this.imageLoader = imageLoader;
    }

    public GridLayoutManager.SpanSizeLookup getSpanSizeLookup() {
        return spanSizeLookup;
    }

    protected ImageLoader getImageLoader() {
        return imageLoader;
    }

    public void setAnimateItemsOnScroll(boolean animate) {
        animateItemsOnScroll = animate;
    }

    protected AnimationDirection getAnimationDirection() {
        return AnimationDirection.UpFromBottom;
    }

    @Override
    public ListItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (defaultItemAnimationDuration == 0) {
            defaultItemAnimationDuration = parent.getResources().getInteger(android.R.integer.config_mediumAnimTime);
        }
        View view = LayoutInflater.from(parent.getContext()).inflate(viewType, parent, false);
        return new ListItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ListItemViewHolder holder, int position) {
        ListItemViewModel vm = viewModels.get(position);
        holder.setPrimary(vm.primary);
        holder.setSecondary(vm.secondary);
        holder.setTertiary(vm.tertiary);
        holder.setImageUrl(vm.imageUrl, imageLoader);
        runAnimation(holder, position, defaultItemAnimationDuration, getAnimationDirection());
    }

    @Override
    public int getItemCount() {
        return viewModels.size();
    }

    @Override
    public int getItemViewType(int position) {
        return viewModels.get(position).layout;
    }

    protected void runAnimation(final RecyclerView.ViewHolder targetViewHolder,
                                final int position,
                                final int duration,
                                final AnimationDirection animationDirection) {
        if (animateItemsOnScroll) {
            final float maxAlpha = 1f;
            final View targetView = targetViewHolder.itemView;

            // Don't actually run the animation right a way. This gives a nice effect
            // when adding a large batch of items.
            if (position > lastAnimatedPosition) {
                int delay = 0;
                long currTime = System.currentTimeMillis();
                if (currTime < nextAnimationStartTime + ANIMATION_DELAY_INTERVAL) {
                    delay = (int) ((nextAnimationStartTime + ANIMATION_DELAY_INTERVAL) - currTime);
                }
                nextAnimationStartTime = currTime + delay;

                targetView.setAlpha(0);
                switch (animationDirection) {
                    case UpFromBottom:
                        targetView.setTranslationY(500.0f);
                        break;
                    case DownFromTop:
                        targetView.setTranslationY(-500.0f);
                        break;
                    case InFromLeft:
                        targetView.setTranslationX(500.0f);
                        break;
                    case InFromRight:
                        targetView.setTranslationX(-500.0f);
                        break;
                }
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        switch (animationDirection) {
                            case DownFromTop:
                            case UpFromBottom:
                                targetView.animate().alpha(maxAlpha).translationY(0).setDuration(duration);
                                break;
                            case InFromRight:
                            case InFromLeft:
                                targetView.animate().alpha(maxAlpha).translationX(0).setDuration(duration);
                                break;
                        }
                        targetView.animate().setInterpolator(new LinearOutSlowInInterpolator());
                        targetView.animate().start();
                    }
                }, delay);
                lastAnimatedPosition = position;
            }
        }
    }

    private class SpanSizeLookup extends GridLayoutManager.SpanSizeLookup {
        @Override
        public int getSpanSize(int position) {
            return viewModels.get(position).spanCount;
        }
    }

    // CRUDAdapter

    @Override
    public void removeItem(int position) {
        viewModels.remove(position);
        notifyItemRemoved(position);
    }

    @Override
    public void addItem(ListItemViewModel item) {
        viewModels.add((T)item);
        notifyItemInserted(0);
    }

    @Override
    public void updateItem(int position, ListItemViewModel item) {
        viewModels.set(position, (T)item);
        notifyItemChanged(position);
    }
}
