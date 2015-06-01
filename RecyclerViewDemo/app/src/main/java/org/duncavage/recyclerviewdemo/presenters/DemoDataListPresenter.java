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
public class DemoDataListPresenter extends ListPresenter<ListItemViewModel>
    implements ListView.Events {
    private static final int DUMMY_MODEL_COUNT = 100;

    private final boolean isAddingHeaders;

    public DemoDataListPresenter(ListView<ListItemViewModel> view,
                                 boolean addHeaders) {
        super(view);
        isAddingHeaders = addHeaders;
        view.setEventsListener(this);
    }

    /**
     * Override this to perform extra processing of a header view model
     * @param viewModel
     */
    protected void onHeaderAdded(ListItemViewModel viewModel, int position) {}

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
            } else {
                vm.primary = "Primary " + i;
                vm.secondary = "Secondary " + i;
                vm.tertiary = "Tertiary " + i;
                vm.imageUrl = "http://rdiodynimages0-a.akamaihd.net/?l=a" + (i + 100000) + "-0";
            }
            viewModels.add(vm);
            if (vm.layout == R.layout.list_group_heading) {
                onHeaderAdded(vm, i);
            } else {
                onItemAdded(vm, i);
            }
        }
        setViewModels(viewModels);
    }

    protected ListItemViewModel createBlankItem() {
        ListItemViewModel vm = new ListItemViewModel();
        int randAlbum = Math.abs(new Random().nextInt() % 2000000);
        vm.imageUrl = "http://rdiodynimages0-a.akamaihd.net/?l=a" + randAlbum + "-0";

        return vm;
    }

    @Override
    public void onItemClicked(int position) {

    }

    @Override
    public void onAddNewItem() {
        ListItemViewModel vm = createBlankItem();
        vm.layout = R.layout.list_item;
        getViewModels().add(0, vm);
    }
}
