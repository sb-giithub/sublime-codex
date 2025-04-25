package cool.lazy.cat.sublimecodex.example.event;

import cool.lazy.cat.sublimecodex.core.event.EventAware;

/**
 * <p>
 *
 * </p>
 *
 * @author : jason.ma
 * @date : 14/4/2025 16:48
 */
public interface OnUserLeave extends EventAware {

    void onUserLeave(String id, String name);
}
