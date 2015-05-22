package org.duncavage.recyclerviewdemo;

import android.app.Application;
import android.graphics.Bitmap;
import android.support.v4.util.LruCache;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;

/**
 * Created by brett on 5/22/15.
 */
public class DemoApplication extends Application {
    private final static int DEFAULT_L1_IMAGE_CACHE_SIZE_IN_BYTES = 15 * 1024;
    private static DemoApplication instance;

    private ImageLoader imageLoader;
    private RequestQueue requestQueue;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }

    public static DemoApplication getInstance() {
        return instance;
    }

    public RequestQueue getRequestQueue() {
        if (requestQueue == null) {
            requestQueue = Volley.newRequestQueue(this);
        }
        return requestQueue;
    }

    public ImageLoader getImageLoader() {
        if (imageLoader == null) {
            imageLoader = new ImageLoader(getRequestQueue(), imageCache);
        }
        return imageLoader;
    }

    private final ImageLoader.ImageCache imageCache = new ImageLoader.ImageCache() {
        private final LruCache<String, Bitmap> cache = new LruCache<String, Bitmap>(DEFAULT_L1_IMAGE_CACHE_SIZE_IN_BYTES) {
            @Override
            protected int sizeOf(String key, Bitmap bitmap) {
                return bitmap.getByteCount();
            }
        };
        @Override
        public void putBitmap(String url, Bitmap bitmap) {
            cache.put(url, bitmap);
        }
        @Override
        public Bitmap getBitmap(String url) {
            return cache.get(url);
        }
    };
}
