package cool.lazy.cat.sublimecodex.example.event;

import cool.lazy.cat.sublimecodex.core.event.EventAware;

/**
 * <p>
 *
 * </p>
 *
 * @author : jason.ma
 * @date : 14/4/2025 16:41
 */
public interface OnDeptChange extends EventAware {

    void onDeptCreate(String id, String name, String parentId);

    void onDeptChange(String id, String name, String parentId);

    void onDeptDelete(String id);
}
