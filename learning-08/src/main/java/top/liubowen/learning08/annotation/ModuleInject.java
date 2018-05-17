package top.liubowen.learning08.annotation;

import java.lang.annotation.*;

/**
 * @author liubowen
 * @date 2018/5/11 16:25
 * @description 注入模块
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface ModuleInject {

    String value() default "";

    boolean lazy() default false;

}
