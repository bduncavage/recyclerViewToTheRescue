package org.duncavage.recyclerviewdemo.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.toolbox.ImageLoader;

import org.duncavage.recyclerviewdemo.DemoApplication;
import org.duncavage.recyclerviewdemo.MainActivity;
import org.duncavage.recyclerviewdemo.R;
import org.duncavage.recyclerviewdemo.adapters.CRUDAdapter;
import org.duncavage.recyclerviewdemo.adapters.ListItemRecyclerViewHolderAdapter;
import org.duncavage.recyclerviewdemo.adapters.ListItemViewHolderAdapter;
import org.duncavage.recyclerviewdemo.presenters.DemoDataListPresenter;
import org.duncavage.recyclerviewdemo.presenters.GridDemoDataListPresenter;
import org.duncavage.recyclerviewdemo.presenters.GridWithCarouselsDemoDataListPresenter;
import org.duncavage.recyclerviewdemo.presenters.GridWithHeadingsAndSpansDemoDataListPresenter;
import org.duncavage.recyclerviewdemo.presenters.GridWithHeadingsDemoDataListPresenter;
import org.duncavage.recyclerviewdemo.presenters.views.ListView;
import org.duncavage.recyclerviewdemo.viewmodels.ListItemViewModel;
import org.duncavage.recyclerviewdemo.views.RecyclerViewItemClickListener;

import java.util.List;
import java.util.Random;

/**
 * Created by brett on 5/21/15.
 */
public class RecyclerViewFragment extends Fragment implements ListView<ListItemViewModel> {
    private static final String LAYOUT_TYPE_ARG_KEY = "layoutType";
    private ListView.Events eventsListener;

    public enum LayoutType {
        Linear,
        Grid,
        GridWithGroupHeadings,
        GridWithHeadingsAndSpans,
        GridWithCarousel,
    }

    public static RecyclerViewFragment newInstance(LayoutType layoutType) {
        Bundle args = new Bundle();
        args.putString(LAYOUT_TYPE_ARG_KEY, layoutType.name());
        RecyclerViewFragment f = new RecyclerViewFragment();
        f.setArguments(args);
        return f;
    }

    private RecyclerView recyclerView;
    private ImageLoader imageLoader;
    private DemoDataListPresenter listPresenter;
    private LayoutType layoutType = LayoutType.Linear;
    private int gridSpanCount;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null && getArguments().containsKey(LAYOUT_TYPE_ARG_KEY)) {
            String type = getArguments().getString(LAYOUT_TYPE_ARG_KEY);
            layoutType = LayoutType.valueOf(type);
        }
        imageLoader = DemoApplication.getInstance().getImageLoader();
        gridSpanCount = getResources().getInteger(R.integer.grid_span_count);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_recyclerview, container, false);

        recyclerView = (RecyclerView)rootView.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(createLayoutManager(layoutType));

        recyclerView.addOnItemTouchListener(new RecyclerViewItemClickListener(getActivity(),
                new RecyclerViewItemClickListener.OnItemGestureListener() {
            @Override
            public void onItemClick(View view, int position) {
                view.performClick();
                if (recyclerView.getAdapter() instanceof CRUDAdapter) {
                    ((CRUDAdapter)recyclerView.getAdapter()).removeItem(position);
                }
            }

            @Override
            public void onItemLongClick(View view, int position) {

            }
        }));

        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // Now that the view has been created, create any presenters needed to get data.
        listPresenter = createListPresenter();
        listPresenter.load();
    }

    public void addNewItem() {
        eventsListener.onAddNewItem();
        recyclerView.getAdapter().notifyItemInserted(0);
        recyclerView.scrollToPosition(0);
    }

    private DemoDataListPresenter createListPresenter() {
        switch (layoutType) {
            case Linear:
                return new DemoDataListPresenter(this, true);
            case Grid:
                return new GridDemoDataListPresenter(this);
            case GridWithGroupHeadings:
                return new GridWithHeadingsDemoDataListPresenter(this, gridSpanCount);
            case GridWithHeadingsAndSpans:
                return new GridWithHeadingsAndSpansDemoDataListPresenter(this, gridSpanCount);
            case GridWithCarousel:
                return new GridWithCarouselsDemoDataListPresenter(this, gridSpanCount);
        }
        return null;
    }

    private RecyclerView.LayoutManager createLayoutManager(LayoutType type) {
        if (type == null) {
            return new LinearLayoutManager(getActivity());
        }
        switch (type) {
            case Linear:
                return new LinearLayoutManager(getActivity());
            case Grid:
            case GridWithGroupHeadings:
            case GridWithHeadingsAndSpans:
            case GridWithCarousel:
                return new GridLayoutManager(getActivity(), gridSpanCount);
            default:
                return new LinearLayoutManager(getActivity());
        }
    }

    // ListView methods

    @Override
    public void setList(List<ListItemViewModel> list) {
        ListItemViewHolderAdapter adapter;
        if (layoutType == LayoutType.GridWithCarousel) {
            adapter = new ListItemRecyclerViewHolderAdapter(list, imageLoader);
        } else {
            adapter = new ListItemViewHolderAdapter<>(list, imageLoader);
        }
        recyclerView.swapAdapter(adapter, false);
        if (recyclerView.getLayoutManager() instanceof GridLayoutManager) {
            GridLayoutManager glm = (GridLayoutManager) recyclerView.getLayoutManager();
            glm.setSpanSizeLookup(adapter.getSpanSizeLookup());
        }
    }

    @Override
    public void setEventsListener(Events listener) {
        eventsListener = listener;
    }
}
