package top.liubowen.learning10.common;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Lazy;

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
        this.name = field.isAnnotationPresent(Qualifier.class) ? field.getAnnotation(Qualifier.class).value() : "";
        this.isLazy = field.isAnnotationPresent(Lazy.class) && field.getAnnotation(Lazy.class).value();
        this.nullable = field.isAnnotationPresent(Autowired.class) && field.getAnnotation(Autowired.class).required();
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
        return "InjectField{" +
                "field=" + field +
                ", name='" + name + '\'' +
                ", isLazy=" + isLazy +
                ", nullable=" + nullable +
                ", cache=" + cache +
                '}';
    }
}
