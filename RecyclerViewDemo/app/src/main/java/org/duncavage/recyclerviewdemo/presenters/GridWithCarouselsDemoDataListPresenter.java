package org.duncavage.recyclerviewdemo.presenters;

import org.duncavage.recyclerviewdemo.R;
import org.duncavage.recyclerviewdemo.presenters.views.ListView;
import org.duncavage.recyclerviewdemo.viewmodels.ListItemViewModel;

/**
 * Created by brett on 5/27/15.
 */
public class GridWithCarouselsDemoDataListPresenter extends GridWithHeadingsAndSpansDemoDataListPresenter {
    private static final int NO_POSITION = -1;

    private int lastHeaderPosition = NO_POSITION;

    public GridWithCarouselsDemoDataListPresenter(ListView<ListItemViewModel> view,
                                                  int spanCount,
                                                  StringProvider stringProvider) {
        super(view, spanCount, stringProvider);
    }

    @Override
    protected void onHeaderAdded(ListItemViewModel viewModel, int position) {
        super.onHeaderAdded(viewModel, position);
        lastHeaderPosition = position;
    }

    @Override
    protected void onItemAdded(ListItemViewModel viewModel, int position) {
        super.onItemAdded(viewModel, position);
        if (lastHeaderPosition != NO_POSITION && lastHeaderPosition == position -1) {
            viewModel.spanCount = getSpanCount();
            viewModel.layout = R.layout.list_item_recyclerview;
        }
    }
}
