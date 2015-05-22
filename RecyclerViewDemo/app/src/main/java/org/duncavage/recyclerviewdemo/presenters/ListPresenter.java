package org.duncavage.recyclerviewdemo.presenters;

import org.duncavage.recyclerviewdemo.presenters.views.ListView;

import java.util.List;

/**
 * Created by brett on 5/21/15.
 *
 * The template type indicates the view model type that will be produced by this presenter.
 */
public abstract class ListPresenter<T> {
    private final ListView<T> view;
    private List<T> viewModels;

    public ListPresenter(ListView<T> view) {
        this.view = view;
    }

    protected ListView<T> getView() {
        return view;
    }

    protected List<T> getViewModels() {
        return viewModels;
    }

    public abstract void load();

    protected void setViewModels(List<T> viewModels) {
        this.viewModels = viewModels;
        view.setList(viewModels);
    }
}
