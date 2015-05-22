package org.duncavage.recyclerviewdemo.adapters;

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
public class ListItemViewHolderAdapter<T extends ListItemViewModel> extends RecyclerView.Adapter<ListItemViewHolder> {
    private List<T> viewModels;
    private final ImageLoader imageLoader;

    public ListItemViewHolderAdapter(List<T> viewModels, ImageLoader imageLoader) {
        this.viewModels = viewModels;
        this.imageLoader = imageLoader;
    }

    @Override
    public ListItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
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
    }

    @Override
    public int getItemCount() {
        return viewModels.size();
    }

    @Override
    public int getItemViewType(int position) {
        return viewModels.get(position).layout;
    }
}
