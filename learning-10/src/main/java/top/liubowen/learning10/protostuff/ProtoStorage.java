package top.liubowen.learning10.protostuff;

import java.lang.annotation.*;

/**
 * @author: liubowen
 * @date: 2018-12-25 11:25
 * @description:
 */
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface ProtoStorage {

	String value();

	int model() default 0;
}
