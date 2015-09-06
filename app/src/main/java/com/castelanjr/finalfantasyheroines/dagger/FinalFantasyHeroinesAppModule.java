package com.castelanjr.finalfantasyheroines.dagger;

import android.app.Application;

import com.castelanjr.finalfantasyheroines.FinalFantasyHeroinesApp;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by angelocastelanjr on 5/30/15.
 */
@Module
public class FinalFantasyHeroinesAppModule {
    private final FinalFantasyHeroinesApp app;

    public FinalFantasyHeroinesAppModule(FinalFantasyHeroinesApp app) {
        this.app = app;
    }

    @Provides @Singleton
    Application provideApplication() {
        return app;
    }
}
