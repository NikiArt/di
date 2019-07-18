package ru.geekbrains.gb_android_libraries.di;

import android.widget.ImageView;

import dagger.Module;
import dagger.Provides;
import ru.geekbrains.gb_android_libraries.mvp.model.api.INetworkStatus;
import ru.geekbrains.gb_android_libraries.mvp.model.image.IImageLoader;
import ru.geekbrains.gb_android_libraries.ui.image.GlideImageLoader;

@Module
public class ImageLoaderModule {

    @Provides
    public IImageLoader<ImageView> imageLoader(INetworkStatus networkStatus) {
        return new GlideImageLoader(networkStatus);
    }
}
