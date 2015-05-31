package castelanjr.com.finalfantasyheroines.data.api.util;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Marks an {@link AutoValue @AutoValue}-annotated type for proper Gson serialization.
 * <p>
 * This annotation is needed because the {@linkplain Retention retention} of {@code @AutoValue}
 * does not allow reflection at runtime.
 *
 * Source: https://gist.github.com/JakeWharton/0d67d01badcee0ae7bc9
 */
@Target(TYPE)
@Retention(RUNTIME)
public @interface AutoGson {
}
