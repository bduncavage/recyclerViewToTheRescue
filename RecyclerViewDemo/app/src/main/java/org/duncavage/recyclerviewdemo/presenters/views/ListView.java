package org.duncavage.recyclerviewdemo.presenters.views;

import java.util.List;

/**
 * Created by brett on 5/21/15.
 */
public interface ListView<T> {
    interface Events {
        void onItemClicked(int position);
        void onAddNewItem();
    }

    void setEventsListener(Events listener);
    void setList(List<T> list);
}
