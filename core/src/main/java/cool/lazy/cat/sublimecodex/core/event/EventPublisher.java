package cool.lazy.cat.sublimecodex.core.event;

import cool.lazy.cat.sublimecodex.core.util.Caster;
import cool.lazy.cat.sublimecodex.core.util.ReflectUtil;

import java.lang.reflect.Proxy;

/**
 * @author : jason.ma
 * @date : 2023-02-03 21:52
 */
public interface EventPublisher<A extends EventAware> {

    static <A extends EventAware> EventPublisher<A> getPublisher(Class<A> delegateAwarerType) {
        return getPublisher(delegateAwarerType, EventBus.DEFAULT);
    }

    /**
     * <p>获取发布者实例</p>
     * <p>这个实例持有了一个事件感知者代理对象 请不要对这个代理对象使用任何非事件接口方法</p>
     * @param delegateAwarerType 代理感知者接口类型
     * @return 发布者实例
     * @param <A> 感知者泛型
     */
    static <A extends EventAware> EventPublisher<A> getPublisher(Class<A> delegateAwarerType, EventBus eventBus) {
        Object awareDelegate = Proxy.newProxyInstance(delegateAwarerType.getClassLoader(), new Class[]{delegateAwarerType}, (proxy, method, args) -> {
            if ("toString".equals(method.getName())) {
                return "EventPublisherProxy." + delegateAwarerType;
            }
            if ("hashCode".equals(method.getName())) {
                return System.identityHashCode(proxy);
            }
            eventBus.dispatch(delegateAwarerType, aware -> ReflectUtil.invoke(aware, method, args));
            return null;
        });
        return () -> Caster.cast(awareDelegate);
    }

    /**
     * <p>获得感知者代理对象</p>
     * <p>不要对这个代理对象使用任何非事件接口方法</p>
     * @return 感知者代理对象，对这个对象进行的方法调用，即等同于对这个事件感知者的所有实现类进行方法调用
     */
    A getAwareDelegate();
}
