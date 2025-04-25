package cool.lazy.cat.sublimecodex.example.event.subscribe.service;

import cool.lazy.cat.sublimecodex.example.event.OnDeptChange;
import cool.lazy.cat.sublimecodex.example.event.OnUserChange;
import org.springframework.stereotype.Service;

/**
 * <p>
 *
 * </p>
 *
 * @author : jason.ma
 * @date : 14/4/2025 16:40
 */
@Service
public class UserDeptService implements OnUserChange, OnDeptChange {

    @Override
    public void onUserJoin(String id, String name) {
        System.out.println(this.getClass().getName() + " onUserJoin id=" + id + ", name=" + name);
    }

    @Override
    public void onUserLeave(String id, String name) {
        System.out.println(this.getClass().getName() + " onUserLeave id=" + id + ", name=" + name);
    }

    @Override
    public void onUserJoinDept(String id, String deptId) {
        System.out.println(this.getClass().getName() + " onUserJoinDept id=" + id + ", deptId=" + deptId);
    }

    @Override
    public void onUserLeaveDept(String id, String deptId) {
        System.out.println(this.getClass().getName() + " onUserLeaveDept id=" + id + ", deptId=" + deptId);
    }

    @Override
    public void onDeptCreate(String id, String name, String parentId) {
        System.out.println(this.getClass().getName() + " onDeptCreate id=" + id + ", name=" + name + ", parentId=" + parentId);
    }

    @Override
    public void onDeptChange(String id, String name, String parentId) {
        System.out.println(this.getClass().getName() + " onDeptChange id=" + id + ", name=" + name + ", parentId=" + parentId);
    }

    @Override
    public void onDeptDelete(String id) {
        System.out.println(this.getClass().getName() + " onDeptDelete id=" + id);
    }
}
