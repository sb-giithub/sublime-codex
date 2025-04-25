package cool.lazy.cat.sublimecodex.example.event.subscribe.service;

import cool.lazy.cat.sublimecodex.example.event.OnUserChange;
import org.springframework.stereotype.Service;

/**
 * <p>
 *
 * </p>
 *
 * @author : jason.ma
 * @date : 14/4/2025 16:50
 */
@Service
public class EmployeeCareService implements OnUserChange {

    @Override
    public void onUserJoinDept(String id, String deptId) {
        System.out.println(this.getClass() + " onUserJoinDept(" + id + ", " + deptId + ")");
    }

    @Override
    public void onUserLeaveDept(String id, String deptId) {
        System.out.println(this.getClass() + " onUserLeaveDept(" + id + ", " + deptId + ")");
    }

    @Override
    public void onUserJoin(String id, String name) {
        System.out.println(this.getClass() + " onUserJoin(" + id + ", " + name + ")");
    }

    @Override
    public void onUserLeave(String id, String name) {
        System.out.println(this.getClass() + " onUserLeave(" + id + ", " + name + ")");
    }
}
