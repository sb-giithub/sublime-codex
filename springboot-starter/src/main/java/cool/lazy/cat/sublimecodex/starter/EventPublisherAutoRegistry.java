package cool.lazy.cat.sublimecodex.starter;

import cool.lazy.cat.sublimecodex.core.event.EventAware;
import cool.lazy.cat.sublimecodex.core.event.EventBus;
import cool.lazy.cat.sublimecodex.core.event.EventPublisher;
import cool.lazy.cat.sublimecodex.core.util.Caster;
import cool.lazy.cat.sublimecodex.core.util.ReflectUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.ResolvableType;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Consumer;

/**
 * <p>
 *
 * </p>
 *
 * @author : jason.ma
 * @date : 15/4/2025 10:28
 */
@ConditionalOnProperty(value = "sublime-codex.enable-event-publisher-auto-registry", havingValue = "true", matchIfMissing = true)
public class EventPublisherAutoRegistry implements BeanDefinitionRegistryPostProcessor, ApplicationContextAware {

    protected ApplicationContext applicationContext;
    private static final Logger LOGGER = LoggerFactory.getLogger(EventPublisherAutoRegistry.class);

    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
        // 收集所有待生成发布器的aware 和已经注册过的事件发布器
        Set<Class<?>> awareTypes = new HashSet<>();
        Set<Class<?>> existPublisherAwareDef = new HashSet<>();
        for (String name : registry.getBeanDefinitionNames()) {
            BeanDefinition def = registry.getBeanDefinition(name);
            if (null == def.getBeanClassName()) {
                continue;
            }
            Class<?> beanClass = ReflectUtil.loadClass(def.getBeanClassName());
            ResolvableType type = def.getResolvableType();
            if (beanClass == EventPublisher.class) {
                existPublisherAwareDef.add(type.as(EventPublisher.class).getGeneric(0).getRawClass());
            } else {
                for (Class<?> anInterface : beanClass.getInterfaces()) {
                    // 登记需要生成发布器的事件感知者类型
                    lookup(anInterface, awareTypes::add);
                }
            }
        }
        Set<Class<?>> dynamicRegistryClasses = new HashSet<>();
        for (Class<?> type : awareTypes) {
            // 用户手动注册过发布器了 则忽略 || 同一个发布器已经动态注册过了 则忽略
            if (existPublisherAwareDef.contains(type) || !dynamicRegistryClasses.add(type)) {
                continue;
            }
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("动态注册事件发布器EventPublisher<{}>...", type.getName());
            }
            // 创建BeanDefinition
            RootBeanDefinition beanDefinition = new RootBeanDefinition();
            beanDefinition.setBeanClass(EventPublisher.class);
            // 手动创建发布器代理
            beanDefinition.setInstanceSupplier(() -> EventPublisher.getPublisher(Caster.cast(type), applicationContext.getBean(EventBus.class)));
            // 设置泛型类型信息
            ResolvableType resolvableType = ResolvableType.forClassWithGenerics(EventPublisher.class, type);
            beanDefinition.setTargetType(resolvableType);
            // 注册发布器
            registry.registerBeanDefinition(this.getClass().getSimpleName() + "." + type.getSimpleName(), beanDefinition);
        }
    }

    protected void lookup(Class<?> source, Consumer<Class<?>> consumer) {
        for (Class<?> anInterface : source.getInterfaces()) {
            if (EventAware.class != source) {
                lookup(anInterface, consumer);
            }
        }
        while (null != source && source != Object.class) {
            if (EventAware.class.isAssignableFrom(source) && EventAware.class != source) {
                consumer.accept(source);
            }
            source = source.getSuperclass();
        }
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {}

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
