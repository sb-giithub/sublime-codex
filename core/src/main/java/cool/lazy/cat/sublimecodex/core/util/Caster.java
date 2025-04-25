package cool.lazy.cat.sublimecodex.core.util;

/**
 * <p>
 *
 * </p>
 *
 * @author : jason.ma
 * @date : 14/4/2025 10:02
 */
public final class Caster {

    private Caster() {}

    @SuppressWarnings({"unchecked"})
    public static  <T> T cast(Object o) {
        return (T) o;
    }
}
