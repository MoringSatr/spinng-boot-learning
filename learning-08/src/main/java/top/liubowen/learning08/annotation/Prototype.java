package top.liubowen.learning08.annotation;

import java.lang.annotation.*;

/**
 * 
 * 对通过new关键字生成的对象进行注入
 * 框架会优先给Prototype增加补丁来实现注入
 * 要求多个prototype之间没有通过静态(static)方式的依赖关系
 * @author luzj
 *
 */
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Prototype {

}
