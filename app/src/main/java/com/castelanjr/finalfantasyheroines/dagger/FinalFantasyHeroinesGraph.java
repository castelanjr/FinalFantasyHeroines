package com.castelanjr.finalfantasyheroines.dagger;

import com.castelanjr.finalfantasyheroines.FinalFantasyHeroinesApp;
import com.castelanjr.finalfantasyheroines.activity.DetailsActivity;
import com.castelanjr.finalfantasyheroines.activity.MainActivity;

/**
 * Created by angelocastelanjr on 5/30/15.
 */
public interface FinalFantasyHeroinesGraph {
    void inject(FinalFantasyHeroinesApp app);
    void inject(MainActivity activity);
    void inject(DetailsActivity activity);
}
