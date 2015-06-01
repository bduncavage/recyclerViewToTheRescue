package org.duncavage.recyclerviewdemo.presenters;

import android.content.Context;

/**
 * Created by brett on 6/1/15.
 */
public class ContextStringProvider implements StringProvider {
    private final Context context;

    public ContextStringProvider(Context context) {
        this.context = context;
    }

    @Override
    public String getStringForResource(int id) {
        return context.getString(id);
    }
}
