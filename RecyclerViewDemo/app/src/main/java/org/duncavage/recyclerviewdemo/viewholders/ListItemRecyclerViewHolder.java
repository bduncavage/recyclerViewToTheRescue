package org.duncavage.recyclerviewdemo.viewholders;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.android.volley.toolbox.ImageLoader;

import org.duncavage.recyclerviewdemo.R;
import org.duncavage.recyclerviewdemo.adapters.ListItemViewHolderAdapter;
import org.duncavage.recyclerviewdemo.presenters.DemoDataListPresenter;
import org.duncavage.recyclerviewdemo.presenters.GridDemoDataListPresenter;
import org.duncavage.recyclerviewdemo.presenters.views.ListView;
import org.duncavage.recyclerviewdemo.viewmodels.ListItemViewModel;

import java.util.List;

/**
 * Created by brett on 5/27/15.
 */
public class ListItemRecyclerViewHolder extends ListItemViewHolder
        implements ListView<ListItemViewModel> {
    private final RecyclerView recyclerView;
    private final ImageLoader imageLoader;
    private DemoDataListPresenter presenter;
    private ListView.Events eventsListener;

    public ListItemRecyclerViewHolder(View itemView, ImageLoader imageLoader) {
        super(itemView);
        recyclerView = (RecyclerView)itemView.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(itemView.getContext(), LinearLayoutManager.HORIZONTAL, false));
        this.imageLoader = imageLoader;
    }

    public void load() {
        presenter = new Presenter(this);
        presenter.load();
    }

    @Override
    public void setList(List<ListItemViewModel> list) {
        ListItemViewHolderAdapter adapter = new Adapter(list, imageLoader);
        recyclerView.swapAdapter(adapter, false);
    }

    @Override
    public void setEventsListener(Events listener) {
        eventsListener = listener;
    }

    private static class Adapter extends ListItemViewHolderAdapter {
        public Adapter(List viewModels, ImageLoader imageLoader) {
            super(viewModels, imageLoader);
        }
        @Override
        protected AnimationDirection getAnimationDirection() {
            return AnimationDirection.InFromLeft;
        }
    }

    private static class Presenter extends GridDemoDataListPresenter {
        public Presenter(ListView<ListItemViewModel> view) {
            super(view);
        }

        @Override
        protected void onItemAdded(ListItemViewModel viewModel, int position) {
            super.onItemAdded(viewModel, position);
            viewModel.layout = R.layout.carousel_item;
        }
    }
}
