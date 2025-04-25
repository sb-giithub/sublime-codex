package cool.lazy.cat.sublimecodex.example.event;

import cool.lazy.cat.sublimecodex.core.event.EventAware;

/**
 * <p>
 *
 * </p>
 *
 * @author : jason.ma
 * @date : 14/4/2025 16:39
 */
public interface OnUserChange extends EventAware, OnUserJoin, OnUserLeave {

    void onUserJoinDept(String id, String deptId);

    void onUserLeaveDept(String id, String deptId);
}
