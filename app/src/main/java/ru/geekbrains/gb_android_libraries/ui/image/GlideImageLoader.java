package ru.geekbrains.gb_android_libraries.ui.image;

import android.graphics.Bitmap;
import android.support.annotation.Nullable;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import ru.geekbrains.gb_android_libraries.mvp.model.api.INetworkStatus;
import ru.geekbrains.gb_android_libraries.mvp.model.cache.ImageCache;
import ru.geekbrains.gb_android_libraries.mvp.model.image.IImageLoader;
import ru.geekbrains.gb_android_libraries.ui.NetworkStatus;

public class GlideImageLoader implements IImageLoader<ImageView> {

    INetworkStatus networkStatus = new NetworkStatus();

    @Override
    public void loadInto(String url, ImageView container) {
        if(networkStatus.isOnline()){
            Glide.with(container.getContext())
                    .asBitmap()
                    .load(url)
                    .listener(new RequestListener<Bitmap>() {
                        @Override
                        public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Bitmap> target, boolean isFirstResource) {
                            return false;
                        }

                        @Override
                        public boolean onResourceReady(Bitmap resource, Object model, Target<Bitmap> target, DataSource dataSource, boolean isFirstResource) {
                            ImageCache.saveImage(url, resource);
                            return false;
                        }
                    })
                    .into(container);
        } else {
            if (ImageCache.contains(url)) {
                GlideApp.with(container.getContext())
                        .load(ImageCache.getFile(url))
                        .into(container);
            }
        }


    }
}
