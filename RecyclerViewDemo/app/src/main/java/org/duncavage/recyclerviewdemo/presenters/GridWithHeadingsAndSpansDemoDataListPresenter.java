package org.duncavage.recyclerviewdemo.presenters;

import org.duncavage.recyclerviewdemo.R;
import org.duncavage.recyclerviewdemo.presenters.views.ListView;
import org.duncavage.recyclerviewdemo.viewmodels.ListItemViewModel;

import java.util.Random;

/**
 * Created by brett on 5/25/15.
 */
public class GridWithHeadingsAndSpansDemoDataListPresenter extends GridWithHeadingsDemoDataListPresenter {
    private static final int NO_POSITION = -1;
    private int lastSpannableItemAddedPosition = NO_POSITION;

    public GridWithHeadingsAndSpansDemoDataListPresenter(ListView<ListItemViewModel> view,
                                                         int spanCount,
                                                         StringProvider stringProvider) {
        super(view, spanCount, stringProvider);
    }

    @Override
    protected void onItemAdded(ListItemViewModel viewModel, int position) {
        super.onItemAdded(viewModel, position);
        if (position > 0) {
            int rand = new Random().nextInt() % 3;
            // 33% chance of adding a span.
            boolean wonRandom = rand == 2;
            // Don't put spannable items next to each other, it doesn't look good.
            boolean notAdjacent = lastSpannableItemAddedPosition == NO_POSITION ||
                    lastSpannableItemAddedPosition != position - 1;
            // Don't put in last column
            boolean notInLastColumn = (position + 1) % getSpanCount() != 0;
            if (wonRandom && notAdjacent && notInLastColumn) {
                viewModel.spanCount = getSpanCount() - 1;
                viewModel.layout = R.layout.grid_item_large;
                lastSpannableItemAddedPosition = position;
            }
        }
        // TODO: Add a full-bleed item that is a horizontal list as the last item of the group
        // Check if next position is a header or not
    }
}
