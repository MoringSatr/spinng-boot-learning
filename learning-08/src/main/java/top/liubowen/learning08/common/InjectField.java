package top.liubowen.learning08.common;

import org.apache.commons.lang3.StringUtils;
import top.liubowen.learning08.annotation.Inject;

import java.lang.reflect.Field;

/**
 * @author liubowen
 * @date 2018/5/8 20:53
 * @description
 */
public class InjectField {

    private final Field field;
    private final String name;
    private final boolean isLazy;
    private final boolean nullable;
    private Object cache;

    public InjectField(Field field) {
        this.field = field;
        this.field.setAccessible(true);
        this.name = field.isAnnotationPresent(Inject.class) ? field.getAnnotation(Inject.class).value() : "";
        this.isLazy = field.isAnnotationPresent(Inject.class) && field.getAnnotation(Inject.class).lazy();
        this.nullable = field.isAnnotationPresent(Inject.class) && field.getAnnotation(Inject.class).nullable();
    }

    private Class<?> type() {
        return field.getType();
    }

    public final void inject(Object bean) throws IllegalArgumentException, IllegalAccessException {
        cache = fetch();
        if (cache == null) {
            throw new IllegalArgumentException(type().getName() + " doesn`t loaded , beacuse " + name + " name not exist");
        }
        field.set(bean, cache);
    }

    private Object fetch() {
        return StringUtils.isBlank(name) ? BeanContext.fetchBean(type()) : BeanContext.fetchBean(name);
    }

    @Override
    public String toString() {
        return field.getType().getName() + " " + field.getName() + (isLazy ? " lazy" : "");
    }

}
