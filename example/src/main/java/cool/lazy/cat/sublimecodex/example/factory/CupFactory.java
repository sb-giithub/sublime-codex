package cool.lazy.cat.sublimecodex.example.factory;

import cool.lazy.cat.sublimecodex.core.factory.GenericFactory;
import cool.lazy.cat.sublimecodex.example.factory.material.Glass;
import cool.lazy.cat.sublimecodex.example.factory.product.Cup;
import org.springframework.stereotype.Component;

/**
 * <p>
 *
 * </p>
 *
 * @author : jason.ma
 * @date : 15/4/2025 11:12
 */
@Component
public class CupFactory implements GenericFactory<Glass, Cup> {

    @Override
    public Cup create(Glass material) {
        return null;
    }
}
