package org.duncavage.recyclerviewdemo.presenters;

import org.duncavage.recyclerviewdemo.R;
import org.duncavage.recyclerviewdemo.presenters.views.ListView;
import org.duncavage.recyclerviewdemo.viewmodels.ListItemViewModel;

/**
 * Created by brett on 5/25/15.
 */
public class GridWithHeadingsDemoDataListPresenter extends DemoDataListPresenter {
    private final int spanCount;
    public GridWithHeadingsDemoDataListPresenter(ListView<ListItemViewModel> view, int spanCount) {
        super(view, true);
        this.spanCount = spanCount;
    }

    protected int getSpanCount() {
        return spanCount;
    }

    @Override
    protected void onHeaderAdded(ListItemViewModel viewModel) {
        viewModel.spanCount = spanCount;
    }

    @Override
    protected void onItemAdded(ListItemViewModel viewModel, int position) {
        viewModel.layout = R.layout.grid_item;
    }
}
