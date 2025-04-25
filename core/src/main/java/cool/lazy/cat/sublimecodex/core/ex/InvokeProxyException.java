package cool.lazy.cat.sublimecodex.core.ex;

/**
 * <p>
 *
 * </p>
 *
 * @author : jason.ma
 * @date : 14/4/2025 10:23
 */
public class InvokeProxyException extends SublimeCodexException {
    private static final long serialVersionUID = -5010883360704382110L;

    public InvokeProxyException(String message) {
        super(message);
    }

    public InvokeProxyException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvokeProxyException(Throwable cause) {
        super(cause);
    }

    public InvokeProxyException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
