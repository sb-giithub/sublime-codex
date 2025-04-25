package cool.lazy.cat.sublimecodex.starter;

import cool.lazy.cat.sublimecodex.core.event.EventBus;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * <p>
 *
 * </p>
 *
 * @author : jason.ma
 * @date : 14/4/2025 17:24
 */
@Configuration
@Import({EventPublisherAutoRegistry.class, EventAwareAutoRegistry.class})
public class SublimeCodexAutoConfiguration {

    @Configuration
    public static class EventBusAutoConfiguration {
        @Bean
        @ConditionalOnMissingBean(value = {EventBus.class})
        public EventBus getEventBus() {
            return EventBus.DEFAULT;
        }
    }
}
