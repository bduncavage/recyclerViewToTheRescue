package org.duncavage.recyclerviewdemo.presenters;

import org.duncavage.recyclerviewdemo.R;
import org.duncavage.recyclerviewdemo.presenters.views.ListView;
import org.duncavage.recyclerviewdemo.viewmodels.ListItemViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by brett on 5/22/15.
 */
public class DemoDataListPresenter extends ListPresenter<ListItemViewModel> {
    private static final int DUMMY_MODEL_COUNT = 100;

    private final boolean isAddingHeaders;

    public DemoDataListPresenter(ListView<ListItemViewModel> view,
                                 boolean addHeaders) {
        super(view);
        isAddingHeaders = addHeaders;
    }

    /**
     * Override this to perform extra processing of a header view model
     * @param viewModel
     */
    protected void onHeaderAdded(ListItemViewModel viewModel) {}

    /**
     * Override this to perform extra processing of a content item
     * @param viewModel
     */
    protected void onItemAdded(ListItemViewModel viewModel, int position) {}

    @Override
    public void load() {
        List<ListItemViewModel> viewModels = new ArrayList<>(DUMMY_MODEL_COUNT);
        // Create some dummy data
        for (int i = 0; i < DUMMY_MODEL_COUNT; i++) {
            ListItemViewModel vm = new ListItemViewModel();
            if (i % 10 == 0 && isAddingHeaders) {
                vm.primary = "Heading " + i;
                vm.layout = R.layout.list_group_heading;
                onHeaderAdded(vm);
            } else {
                vm.primary = "Primary " + i;
                vm.secondary = "Secondary " + i;
                vm.tertiary = "Tertiary " + i;
                vm.imageUrl = "http://rdiodynimages0-a.akamaihd.net/?l=a" + (i + 1) + "-0";
                onItemAdded(vm, i);
            }
            viewModels.add(vm);
        }
        setViewModels(viewModels);
    }
}
