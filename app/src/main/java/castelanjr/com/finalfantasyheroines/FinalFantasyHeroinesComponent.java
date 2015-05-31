package castelanjr.com.finalfantasyheroines;

import javax.inject.Singleton;

import castelanjr.com.finalfantasyheroines.data.DataModule;
import dagger.Component;

/**
 * Created by angelocastelanjr on 5/30/15.
 */
@Singleton
@Component(modules = {FinalFantasyHeroinesAppModule.class, DataModule.class})
public interface FinalFantasyHeroinesComponent extends FinalFantasyHeroinesGraph {

    final class Initializer {
        static FinalFantasyHeroinesGraph init(FinalFantasyHeroinesApp app) {
            return DaggerFinalFantasyHeroinesComponent
                    .builder()
                    .finalFantasyHeroinesAppModule(new FinalFantasyHeroinesAppModule(app))
                    .build();
        }
        private Initializer() {}
    }

}
