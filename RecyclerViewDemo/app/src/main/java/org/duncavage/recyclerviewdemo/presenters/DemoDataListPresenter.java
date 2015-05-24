package org.duncavage.recyclerviewdemo.presenters;

import org.duncavage.recyclerviewdemo.R;
import org.duncavage.recyclerviewdemo.presenters.views.ListView;
import org.duncavage.recyclerviewdemo.viewmodels.ListItemViewModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by brett on 5/22/15.
 */
public class DemoDataListPresenter extends ListPresenter<ListItemViewModel> {
    private static final int DUMMY_MODEL_COUNT = 100;

    public DemoDataListPresenter(ListView<ListItemViewModel> view) {
        super(view);
    }

    @Override
    public void load() {
        List<ListItemViewModel> viewModels = new ArrayList<>(DUMMY_MODEL_COUNT);
        // Create some dummy data
        for (int i = 0; i < DUMMY_MODEL_COUNT; i++) {
            ListItemViewModel vm = new ListItemViewModel();
            if (i % 10 == 0) {
                vm.primary = "Heading " + i;
                vm.layout = R.layout.list_group_heading;
            } else {
                vm.primary = "Primary " + i;
                vm.secondary = "Secondary " + i;
                vm.tertiary = "Tertiary " + i;
                vm.imageUrl = "http://rdiodynimages0-a.akamaihd.net/?l=a" + (i + 1) + "-0";
            }
            viewModels.add(vm);
        }
        setViewModels(viewModels);
    }
}
