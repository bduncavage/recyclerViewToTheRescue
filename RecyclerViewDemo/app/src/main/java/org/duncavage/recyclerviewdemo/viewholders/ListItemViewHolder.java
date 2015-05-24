package org.duncavage.recyclerviewdemo.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

import org.duncavage.recyclerviewdemo.R;

/**
 * Created by brett on 5/21/15.
 */
public class ListItemViewHolder extends RecyclerView.ViewHolder {
    private final TextView primary;
    private final TextView secondary;
    private final TextView tertiary;
    private final NetworkImageView imageView;

    public ListItemViewHolder(View itemView) {
        super(itemView);
        primary = (TextView)itemView.findViewById(R.id.primary);
        secondary = (TextView)itemView.findViewById(R.id.secondary);
        tertiary = (TextView)itemView.findViewById(R.id.tertiary);
        imageView = (NetworkImageView)itemView.findViewById(R.id.image);
    }

    public void setPrimary(String text) {
        if (primary != null) {
            primary.setText(text);
        }
    }

    public void setSecondary(String text) {
        if (secondary != null) {
            secondary.setText(text);
        }
    }

    public void setTertiary(String text) {
        if (tertiary != null) {
            tertiary.setText(text);
        }
    }

    public void setImageUrl(String url, ImageLoader imageLoader) {
        if (imageView != null) {
            // Remove the old image
            imageView.setImageBitmap(null);
            imageView.setImageUrl(url, imageLoader);
        }
    }
}
