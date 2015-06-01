package org.duncavage.recyclerviewdemo.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.toolbox.ImageLoader;

import org.duncavage.recyclerviewdemo.R;
import org.duncavage.recyclerviewdemo.presenters.StringProvider;
import org.duncavage.recyclerviewdemo.viewholders.ListItemRecyclerViewHolder;
import org.duncavage.recyclerviewdemo.viewholders.ListItemViewHolder;
import org.duncavage.recyclerviewdemo.viewmodels.ListItemViewModel;

import java.util.List;

/**
 * Created by brett on 5/27/15.
 */
public class ListItemRecyclerViewHolderAdapter extends ListItemViewHolderAdapter<ListItemViewModel> {
    private final StringProvider stringProvider;

    public ListItemRecyclerViewHolderAdapter(List<ListItemViewModel> viewModels,
                                             ImageLoader imageLoader,
                                             StringProvider stringProvider) {
        super(viewModels, imageLoader);
        this.stringProvider = stringProvider;
    }

    @Override
    public ListItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == R.layout.list_item_recyclerview) {
            View view = LayoutInflater.from(parent.getContext()).inflate(viewType, parent, false);
            ListItemRecyclerViewHolder holder = new ListItemRecyclerViewHolder(view,
                    getImageLoader(), stringProvider);
            holder.load();
            return holder;
        } else {
            return super.onCreateViewHolder(parent, viewType);
        }
    }
}
