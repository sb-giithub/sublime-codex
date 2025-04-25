package cool.lazy.cat.sublimecodex.example.factory;

import cool.lazy.cat.sublimecodex.core.factory.GenericFactory;
import cool.lazy.cat.sublimecodex.example.factory.material.Glass;
import cool.lazy.cat.sublimecodex.example.factory.material.Sand;
import org.springframework.stereotype.Component;

/**
 * <p>
 *
 * </p>
 *
 * @author : jason.ma
 * @date : 15/4/2025 11:10
 */
@Component
public class GlassFactory implements GenericFactory<Sand, Glass> {

    @Override
    public Glass create(Sand material) {
        return null;
    }
}
