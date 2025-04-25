package cool.lazy.cat.sublimecodex.example.factory;

import cool.lazy.cat.sublimecodex.core.factory.GenericFactory;
import cool.lazy.cat.sublimecodex.example.Main;
import cool.lazy.cat.sublimecodex.example.factory.material.Glass;
import cool.lazy.cat.sublimecodex.example.factory.material.Sand;
import cool.lazy.cat.sublimecodex.example.factory.product.Cup;
import cool.lazy.cat.sublimecodex.example.factory.product.Mirror;
import org.junit.Test;

import javax.annotation.Resource;

/**
 * <p>
 *
 * </p>
 *
 * @author : jason.ma
 * @date : 15/4/2025 15:59
 */
public class GenericFactoryTest extends Main {

    @Resource
    GenericFactory<Sand, Glass> glassFactory;
    @Resource
    GenericFactory<Glass, Cup> cupFactory;
    @Resource
    GenericFactory<Glass, Mirror> mirrorFactory;

    @Test
    public void create() {
        Glass glass = glassFactory.create(new Sand());
        Cup cup = cupFactory.create(glass);
        Mirror mirror = mirrorFactory.create(glass);
    }
}
