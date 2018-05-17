package top.liubowen.learning08.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author liubowen
 * @date 2018/5/8 17:09
 * @description
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Inject {

    String value() default "";

    boolean lazy() default false;

    boolean nullable() default false;
}
