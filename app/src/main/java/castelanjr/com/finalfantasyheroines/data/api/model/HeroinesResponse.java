package castelanjr.com.finalfantasyheroines.data.api.model;

import java.util.List;

/**
 * Created by angelocastelanjr on 5/30/15.
 */
public class HeroinesResponse {
    public final List<Heroine> heroines;

    public HeroinesResponse(List<Heroine> heroines) {
        this.heroines = heroines;
    }
}
