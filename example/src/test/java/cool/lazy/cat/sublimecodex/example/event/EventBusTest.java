package cool.lazy.cat.sublimecodex.example.event;

import cool.lazy.cat.sublimecodex.core.event.EventBus;
import cool.lazy.cat.sublimecodex.core.event.EventPublisher;
import cool.lazy.cat.sublimecodex.example.Main;
import org.junit.Test;

import javax.annotation.Resource;

/**
 * <p>
 *
 * </p>
 *
 * @author : jason.ma
 * @date : 14/4/2025 16:37
 */
@SuppressWarnings({"squid:S2699"})
public class EventBusTest extends Main {

    @Resource
    private EventBus eventBus;

    @Test
    public void testEventBus() {
        eventBus.dispatch(OnUserJoin.class, onUserJoin -> onUserJoin.onUserJoin("?", "!"));
    }

    @Resource
    private EventPublisher<OnUserChange> onUserChangeEventPublisher;
    @Resource
    private EventPublisher<OnUserJoin> onUserJoinEventPublisher;
    @Resource
    private EventPublisher<OnUserLeave> onUserLeaveEventPublisher;
    @Resource
    private EventPublisher<OnDeptChange> onDeptChangeEventPublisher;

    @Test
    public void testEventPublisher() {
        onUserChangeEventPublisher.getAwareDelegate().onUserJoinDept(".", "。");
        onUserJoinEventPublisher.getAwareDelegate().onUserJoin("1", "2");
        onUserLeaveEventPublisher.getAwareDelegate().onUserLeave("a", "b");
        onDeptChangeEventPublisher.getAwareDelegate().onDeptCreate("<", ">", "/");
        onDeptChangeEventPublisher.getAwareDelegate().onDeptChange("√", "×", "*");
        onDeptChangeEventPublisher.getAwareDelegate().onDeptDelete("✔️");
    }
}
