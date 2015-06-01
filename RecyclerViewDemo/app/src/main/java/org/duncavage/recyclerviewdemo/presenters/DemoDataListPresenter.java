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
    private final StringProvider stringProvider;

    public DemoDataListPresenter(ListView<ListItemViewModel> view,
                                 boolean addHeaders,
                                 StringProvider stringProvider) {
        super(view);
        isAddingHeaders = addHeaders;
        view.setEventsListener(this);
        this.stringProvider = stringProvider;
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
                vm.primary = stringProvider.getStringForResource(R.string.item_primary_prefix) + " " + i;
                vm.layout = R.layout.list_group_heading;
            } else {
                vm.primary = stringProvider.getStringForResource(R.string.item_primary_prefix) + " " + i;
                vm.secondary = stringProvider.getStringForResource(R.string.item_secondary_prefix) + " " + i;
                vm.tertiary = i + " " + stringProvider.getStringForResource(R.string.item_tertiary_prefix);
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
        vm.primary = stringProvider.getStringForResource(R.string.new_item_primary);
        vm.secondary = stringProvider.getStringForResource(R.string.new_item_secondary);
        int randAlbum = Math.abs(new Random().nextInt() % 20000000) + 10000000;
        vm.imageUrl = "http://rdiodynimages0-a.akamaihd.net/?l=a" + randAlbum + "-1";

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

    @Override
    public void onRemoveItem(int position) {
        getViewModels().remove(position);
    }
}
