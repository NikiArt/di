package ru.geekbrains.gb_android_libraries.di;

import dagger.Component;
import ru.geekbrains.gb_android_libraries.mvp.presenter.MainPresenter;

import javax.inject.Singleton;

@Singleton
@Component(modules = {RepoModule.class, UtilsModule.class})
public interface AppComponent {
    void inject(MainPresenter mainPresenter);
}
