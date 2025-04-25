package cool.lazy.cat.sublimecodex.example.event.subscribe.service;

import cool.lazy.cat.sublimecodex.example.event.OnUserChange;
import org.springframework.stereotype.Service;

/**
 * <p>
 *
 * </p>
 *
 * @author : jason.ma
 * @date : 14/4/2025 16:45
 */
@Service
public class UserThirdPartService implements OnUserChange {

    @Override
    public void onUserJoin(String id, String name) {
        System.out.println(this.getClass().getName() + " onUserJoin " + id + " " + name);
    }

    @Override
    public void onUserLeave(String id, String name) {
        System.out.println(this.getClass().getName() + " onUserLeave " + id + " " + name);
    }

    @Override
    public void onUserJoinDept(String id, String deptId) {
        System.out.println(this.getClass().getName() + " onUserJoinDept " + id + " " + deptId);
    }

    @Override
    public void onUserLeaveDept(String id, String deptId) {
        System.out.println(this.getClass().getName() + " onUserLeaveDept " + id + " " + deptId);
    }
}
