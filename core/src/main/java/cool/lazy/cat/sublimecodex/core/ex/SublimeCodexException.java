package cool.lazy.cat.sublimecodex.core.ex;

/**
 * <p>
 *
 * </p>
 *
 * @author : jason.ma
 * @date : 14/4/2025 10:22
 */
public abstract class SublimeCodexException extends RuntimeException {
    private static final long serialVersionUID = -9069468325054313443L;

    protected SublimeCodexException(String message) {
        super(message);
    }

    protected SublimeCodexException(String message, Throwable cause) {
        super(message, cause);
    }

    protected SublimeCodexException(Throwable cause) {
        super(cause);
    }

    protected SublimeCodexException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
