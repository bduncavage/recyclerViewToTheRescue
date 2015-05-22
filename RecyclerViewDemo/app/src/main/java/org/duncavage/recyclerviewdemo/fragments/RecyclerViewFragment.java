package org.duncavage.recyclerviewdemo.fragments;

import android.app.Activity;
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
import org.duncavage.recyclerviewdemo.R;
import org.duncavage.recyclerviewdemo.adapters.ListItemViewHolderAdapter;
import org.duncavage.recyclerviewdemo.presenters.DemoDataListPresenter;
import org.duncavage.recyclerviewdemo.presenters.views.ListView;
import org.duncavage.recyclerviewdemo.viewmodels.ListItemViewModel;

import java.util.List;

/**
 * Created by brett on 5/21/15.
 */
public class RecyclerViewFragment extends Fragment implements ListView<ListItemViewModel> {
    public enum LayoutType {
        Linear,
        Grid,
        GridWithSpans,
    }

    private RecyclerView recyclerView;
    private ImageLoader imageLoader;
    private DemoDataListPresenter listPresenter;
    private LayoutType layoutType = LayoutType.Linear;
    private int gridSpanCount;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        imageLoader = DemoApplication.getInstance().getImageLoader();
        gridSpanCount = getResources().getInteger(R.integer.grid_span_count);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_recyclerview, container);

        recyclerView = (RecyclerView)rootView.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(getLayoutManager(layoutType));

        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // Now that the view has been created, create any presenters needed to get data.
        listPresenter = new DemoDataListPresenter(this);
        listPresenter.load();
    }

    // ListView methods

    @Override
    public void setList(List<ListItemViewModel> list) {
        recyclerView.swapAdapter(new ListItemViewHolderAdapter<>(list, imageLoader), false);
    }

    private RecyclerView.LayoutManager getLayoutManager(LayoutType type) {
        if (type == null) {
            return new LinearLayoutManager(getActivity());
        }
        switch (type) {
            case Linear:
                return new LinearLayoutManager(getActivity());
            case GridWithSpans:
            case Grid:
                return new GridLayoutManager(getActivity(), gridSpanCount);
            default:
                return new LinearLayoutManager(getActivity());
        }
    }
}
