package cool.lazy.cat.sublimecodex.core.ex;

/**
 * <p>
 *
 * </p>
 *
 * @author : jason.ma
 * @date : 14/4/2025 10:23
 */
public class ClassLoadException extends SublimeCodexException {
    private static final long serialVersionUID = -3789345835922024297L;

    public ClassLoadException(String message) {
        super(message);
    }

    public ClassLoadException(String message, Throwable cause) {
        super(message, cause);
    }

    public ClassLoadException(Throwable cause) {
        super(cause);
    }

    public ClassLoadException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
