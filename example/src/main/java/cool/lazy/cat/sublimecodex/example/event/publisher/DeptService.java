package cool.lazy.cat.sublimecodex.example.event.publisher;

import cool.lazy.cat.sublimecodex.core.event.EventPublisher;
import cool.lazy.cat.sublimecodex.example.event.OnDeptChange;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 *
 * </p>
 *
 * @author : jason.ma
 * @date : 14/4/2025 17:01
 */
@Service
public class DeptService {

    @Resource
    EventPublisher<OnDeptChange> deptChangeEventEventPublisher;

    public void onDeptCreate() {
        deptChangeEventEventPublisher.getAwareDelegate().onDeptCreate("A", "A部门", "");
    }

    public void onDeptUpdate() {
        deptChangeEventEventPublisher.getAwareDelegate().onDeptChange("A", "A-部门", "");
    }

    public void onDeptDelete() {
        deptChangeEventEventPublisher.getAwareDelegate().onDeptDelete("A");
    }
}
