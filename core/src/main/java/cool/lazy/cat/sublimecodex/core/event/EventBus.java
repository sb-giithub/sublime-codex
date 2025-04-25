package cool.lazy.cat.sublimecodex.core.event;


import cool.lazy.cat.sublimecodex.core.util.Caster;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

/**
 * <p>使用事件总线的方式松散耦合</p>
 * <p>当你在维护一段代码时 发现作者的代码逻辑散落在各个角落 --尤其是嵌套了太多层lambda表达式的情况</p>
 * <p>这无疑是一种灾难现象 lambda的存在极大的降低了代码的可读性</p>
 * <p>因此 宁愿牺牲掉部分性能也要彻底的避免这种事情的发生</p>
 * @author jason.ma
 * @date 2023-02-03 22:12
 */
public interface EventBus {

    Logger LOGGER = LoggerFactory.getLogger(EventBus.class);

    default <A extends EventAware> A registerAware(A aware) {
        return this.registerByAwareType(aware.getClass(), aware);
    }

    /**
     * <p>注册事件感知者</p>
     * <p>从实例实现的接口中过滤EventAware接口的子接口 并按接口类型进行注册</p>
     * @param awareRealType 对象的真实类型 开放此参数的目的是如果aware是一个动态的代理后的对象 则aware.getClass()不能拿到真实的对象类型 因此允许外部传入类型
     * @param aware 关注此事件的对象
     */
    default <A extends EventAware> A registerByAwareType(Class<?> awareRealType, A aware) {
        while (awareRealType != null && awareRealType != Object.class) {
            // 检查实现的接口是不是一个事件感知者
            for (Class<?> anInterface : awareRealType.getInterfaces()) {
                if (anInterface == EventAware.class) {
                    continue;
                }
                if (EventAware.class.isAssignableFrom(anInterface)) {
                    if (LOGGER.isDebugEnabled()) {
                        LOGGER.debug("注册事件感知 {} -> {}", aware, anInterface.getName());
                    }
                    this.register(Caster.cast(anInterface), aware);
                }
                registerByAwareType(anInterface, aware);
            }
            awareRealType = awareRealType.getSuperclass();
        }
        return aware;
    }

    /**
     * 发布事件
     * @param awareClass 感知者的接口类型
     * @param action 调用事件函数
     */
    <E extends EventAware> void dispatch(Class<E> awareClass, Consumer<E> action);

    /**
     * 注册事件感知者
     * @param awareInterfaceClass 感知者的接口类型
     * @param aware 关注此事件的对象
     */
    <A extends EventAware> A register(Class<A> awareInterfaceClass, A aware);

    EventBus DEFAULT = new Default();

    class Default implements EventBus {

        protected Default() {}

        protected final Map<Class<? extends EventAware>, List<EventAware>> eventSubscriberMap = new HashMap<>();
        protected static final Object NODE = new Object();
        protected final Map<Class<? extends EventAware>, Map<EventAware, Object>> existEventAware = new HashMap<>();

        @Override
        public synchronized <A extends EventAware> A register(Class<A> awareInterfaceClass, A aware) {
            // 同一个事件类型下同一个感知者只能注册一次 否则会重复通知
            Map<EventAware, Object> eventAwarerMap = existEventAware.computeIfAbsent(awareInterfaceClass, k -> new IdentityHashMap<>());
            if (eventAwarerMap.containsKey(aware)) {
                LOGGER.warn("事件感知者{} -> {}已注册过，忽略", aware, awareInterfaceClass);
                return aware;
            }
            eventAwarerMap.put(aware, NODE);
            eventSubscriberMap.computeIfAbsent(awareInterfaceClass, et -> new ArrayList<>()).add(aware);
            return aware;
        }

        @Override
        public <E extends EventAware> void dispatch(Class<E> awareClass, Consumer<E> action) {
            List<E> eventAwareList = Caster.cast(eventSubscriberMap.getOrDefault(awareClass, Collections.emptyList()));
            if (eventAwareList.isEmpty()) {
                LOGGER.warn("该事件无感知者！{}", awareClass);
                return;
            }
            eventAwareList.forEach(action);
        }
    }
}
