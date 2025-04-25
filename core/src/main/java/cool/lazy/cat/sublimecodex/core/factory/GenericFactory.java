package cool.lazy.cat.sublimecodex.core.factory;

import cool.lazy.cat.sublimecodex.core.util.ReflectUtil;

/**
 * <p>
 *
 * </p>
 *
 * @author : jason.ma
 * @date : 14/4/2025 15:12
 */
public interface GenericFactory<M extends Material, P extends Product> {

    default Class<M> getMeterialClass() {
        return ReflectUtil.findGenericType(this.getClass(), GenericFactory.class, 0);
    }

    default Class<P> getProductClass() {
        return ReflectUtil.findGenericType(this.getClass(), GenericFactory.class, 1);
    }

    P create(M material);
}
