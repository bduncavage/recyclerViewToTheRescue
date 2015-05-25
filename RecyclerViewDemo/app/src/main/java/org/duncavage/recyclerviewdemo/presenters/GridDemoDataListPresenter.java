package org.duncavage.recyclerviewdemo.presenters;

import org.duncavage.recyclerviewdemo.R;
import org.duncavage.recyclerviewdemo.presenters.views.ListView;
import org.duncavage.recyclerviewdemo.viewmodels.ListItemViewModel;

/**
 * Created by brett on 5/25/15.
 */
public class GridDemoDataListPresenter extends DemoDataListPresenter {
    public GridDemoDataListPresenter(ListView<ListItemViewModel> view) {
        super(view, false);
    }

    @Override
    protected void onItemAdded(ListItemViewModel viewModel, int position) {
        viewModel.layout = R.layout.grid_item;
    }
}
