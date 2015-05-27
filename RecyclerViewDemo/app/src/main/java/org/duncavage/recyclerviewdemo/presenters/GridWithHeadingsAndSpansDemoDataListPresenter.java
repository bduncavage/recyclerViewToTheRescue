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
        if (position > 0 && position % 5 == 0 || position % 6 == 0) {
            viewModel.spanCount = getSpanCount() - 1;
            viewModel.layout = R.layout.grid_item_large;
        }
        // TODO: Add a full-bleed item that is a horizontal list as the last item of the group
        // Check if next position is a header or not
    }
}
