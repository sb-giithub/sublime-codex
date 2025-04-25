package cool.lazy.cat.sublimecodex.core.util;

import cool.lazy.cat.sublimecodex.core.ex.ClassLoadException;
import cool.lazy.cat.sublimecodex.core.ex.InvokeProxyException;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * <p>
 *
 * </p>
 *
 * @author : jason.ma
 * @date : 14/4/2025 10:12
 */
@SuppressWarnings({"squid:S3011"})
public final class ReflectUtil {

    private ReflectUtil() {}

    public static Object invoke(Object instance, Method method, Object[] args) {
        method.setAccessible(true);
        try {
            return method.invoke(instance, args);
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new InvokeProxyException(e);
        }
    }

    @SuppressWarnings({"unchecked"})
    public static <T> Class<T> findGenericType(Class<?> clazz, Class<?> target, int index) {
        Type[] types = findGenericTypes(clazz, target);
        if (types.length == 0) {
            return null;
        }
        return (Class<T>) types[index];
    }

    public static Type[] findGenericTypes(Class<?> clazz, Class<?> target) {
        while (clazz != Object.class) {
            for (Type type : clazz.getGenericInterfaces()) {
                if (type instanceof ParameterizedType && target.isAssignableFrom((Class<?>) ((ParameterizedType) type).getRawType())) {
                    return ((ParameterizedType) type).getActualTypeArguments();
                }
            }
            Type type = clazz.getGenericSuperclass();
            if (type instanceof ParameterizedType && target.isAssignableFrom((Class<?>) ((ParameterizedType) type).getRawType())) {
                return ((ParameterizedType) type).getActualTypeArguments();
            }
            clazz = clazz.getSuperclass();
        }
        return new Class[0];
    }

    public static Class<?> loadClass(String className) {
        try {
            return Class.forName(className);
        } catch (ClassNotFoundException e) {
            throw new ClassLoadException(e);
        }
    }
}
