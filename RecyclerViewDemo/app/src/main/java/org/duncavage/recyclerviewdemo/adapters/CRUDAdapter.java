package org.duncavage.recyclerviewdemo.adapters;

import org.duncavage.recyclerviewdemo.viewmodels.ListItemViewModel;

/**
 * Created by brett on 5/31/15.
 */
public interface CRUDAdapter {
    void removeItem(int position);
    void addItem(ListItemViewModel item);
    void updateItem(int position, ListItemViewModel item);
}
