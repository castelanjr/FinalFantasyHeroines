package com.castelanjr.finalfantasyheroines.dagger;

import com.castelanjr.finalfantasyheroines.FinalFantasyHeroinesApp;
import com.castelanjr.finalfantasyheroines.data.DataModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by angelocastelanjr on 5/30/15.
 */
@Singleton
@Component(modules = {FinalFantasyHeroinesAppModule.class, DataModule.class})
public interface FinalFantasyHeroinesComponent extends FinalFantasyHeroinesGraph {

    final class Initializer {
        public static FinalFantasyHeroinesGraph init(FinalFantasyHeroinesApp app) {
            return DaggerFinalFantasyHeroinesComponent
                    .builder()
                    .finalFantasyHeroinesAppModule(new FinalFantasyHeroinesAppModule(app))
                    .build();
        }
        private Initializer() {}
    }

}
