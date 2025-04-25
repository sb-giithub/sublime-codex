package cool.lazy.cat.sublimecodex.example.event.publisher;

import cool.lazy.cat.sublimecodex.core.event.EventPublisher;
import cool.lazy.cat.sublimecodex.example.event.OnDeptChange;
import cool.lazy.cat.sublimecodex.example.event.OnUserChange;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 *
 * </p>
 *
 * @author : jason.ma
 * @date : 14/4/2025 16:55
 */
@Service
public class UserService {

    @Resource
    EventPublisher<OnUserChange> userChangeEventEventPublisher;
    @Resource
    EventPublisher<OnDeptChange> deptChangeEventEventPublisher;

    public void createUser() {
        userChangeEventEventPublisher.getAwareDelegate().onUserJoin("1", "小明");
    }

    public void deleteUser() {
        userChangeEventEventPublisher.getAwareDelegate().onUserLeave("2", "小张");
    }

    public void moveUser() {
        // 离开部门
        userChangeEventEventPublisher.getAwareDelegate().onUserLeaveDept("1", "A");
        deptChangeEventEventPublisher.getAwareDelegate().onDeptChange("A", "A部门", "");
        // 加入部门
        userChangeEventEventPublisher.getAwareDelegate().onUserJoinDept("1", "B");
        deptChangeEventEventPublisher.getAwareDelegate().onDeptChange("B", "B部门", "");
    }
}
