package cool.lazy.cat.sublimecodex.example.event.subscribe.service;

import cool.lazy.cat.sublimecodex.example.event.OnDeptChange;
import org.springframework.stereotype.Service;

/**
 * <p>
 *
 * </p>
 *
 * @author : jason.ma
 * @date : 14/4/2025 16:47
 */
@Service
public class WorkflowService implements OnDeptChange {

    @Override
    public void onDeptCreate(String id, String name, String parentId) {
        System.out.println(this.getClass().getName() + " onDeptCreate id=" + id + ", name=" + name + ", parentId=" + parentId);
    }

    @Override
    public void onDeptChange(String id, String name, String parentId) {
        System.out.println(this.getClass().getName() + " onDeptChange id=" + id + ", parentId=" + parentId);
    }

    @Override
    public void onDeptDelete(String id) {
        System.out.println(this.getClass().getName() + " onDeptDelete id=" + id);
    }
}
