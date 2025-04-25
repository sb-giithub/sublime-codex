package cool.lazy.cat.sublimecodex.starter;

import cool.lazy.cat.sublimecodex.core.event.EventAware;
import cool.lazy.cat.sublimecodex.core.event.EventBus;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.BeansException;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * <p>
 *
 * </p>
 *
 * @author : jason.ma
 * @date : 15/4/2025 10:31
 */
@ConditionalOnBean({EventBus.class})
@ConditionalOnProperty(value = "sublime-codex.enable-event-aware-auto-registry", havingValue = "true", matchIfMissing = true)
public class EventAwareAutoRegistry implements ApplicationContextAware {

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        // 自动将事件感知者注册到事件总线中去
        EventBus eventBus = applicationContext.getBean(EventBus.class);
        for (EventAware awarer : applicationContext.getBeansOfType(EventAware.class).values()) {
            Class<?> originalType = AopUtils.getTargetClass(awarer);
            eventBus.registerByAwareType(originalType, awarer);
        }
    }
}
