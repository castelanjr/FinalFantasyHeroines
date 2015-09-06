package com.castelanjr.finalfantasyheroines;

import android.app.Application;
import android.content.Context;

import com.castelanjr.finalfantasyheroines.dagger.FinalFantasyHeroinesComponent;
import com.castelanjr.finalfantasyheroines.dagger.FinalFantasyHeroinesGraph;

/**
 * Created by angelocastelanjr on 5/30/15.
 */
public class FinalFantasyHeroinesApp extends Application {
    private FinalFantasyHeroinesGraph component;

    @Override
    public void onCreate() {
        super.onCreate();

        buildComponentAndInject();
    }

    public void buildComponentAndInject() {
        component = FinalFantasyHeroinesComponent.Initializer.init(this);
        component.inject(this);
    }

    public FinalFantasyHeroinesGraph component() {
        return component;
    }

    public static FinalFantasyHeroinesApp get(Context context) {
        return (FinalFantasyHeroinesApp) context.getApplicationContext();
    }
}
