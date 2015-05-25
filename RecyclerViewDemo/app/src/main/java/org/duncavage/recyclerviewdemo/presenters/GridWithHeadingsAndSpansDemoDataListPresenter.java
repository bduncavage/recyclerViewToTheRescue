package org.duncavage.recyclerviewdemo.presenters;

import org.duncavage.recyclerviewdemo.R;
import org.duncavage.recyclerviewdemo.presenters.views.ListView;
import org.duncavage.recyclerviewdemo.viewmodels.ListItemViewModel;

/**
 * Created by brett on 5/25/15.
 */
public class GridWithHeadingsAndSpansDemoDataListPresenter extends GridWithHeadingsDemoDataListPresenter {
    public GridWithHeadingsAndSpansDemoDataListPresenter(ListView<ListItemViewModel> view, int spanCount) {
        super(view, spanCount);
    }

    @Override
    protected void onItemAdded(ListItemViewModel viewModel, int position) {
        super.onItemAdded(viewModel, position);
        if (position > 0 && position % 5 == 0) {
            viewModel.spanCount = getSpanCount() - 1;
            viewModel.layout = R.layout.grid_item_large;
        }
    }
}
