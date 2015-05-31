package castelanjr.com.finalfantasyheroines.data.api.model;

import android.graphics.Color;
import android.os.Parcelable;
import android.support.annotation.ColorInt;

import auto.parcel.AutoParcel;
import castelanjr.com.finalfantasyheroines.data.api.util.AutoGson;

/**
 * Created by angelocastelanjr on 5/30/15.
 */
@AutoParcel @AutoGson
public abstract class Heroine implements Parcelable {
    public abstract long id();
    public abstract String name();
    public abstract String fullname();
    public abstract String ability();
    public abstract String avatar();
    public abstract String image();
    public abstract String description();
    public abstract String color();
    public abstract String game();

    @ColorInt public int getColor() {
        return color() == null ? Color.WHITE : Color.parseColor(color());
    }

    public Heroine create(long id, String name, String fullname, String ability, String avatar,
                          String image, String description, String color, String game) {
        return new AutoParcel_Heroine(id, name, fullname, ability, avatar, image, description,
                color, game);
    }
}
