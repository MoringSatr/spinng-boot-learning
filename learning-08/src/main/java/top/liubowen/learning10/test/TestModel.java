package top.liubowen.learning10.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import top.liubowen.learning10.annotation.Prototype;

/**
 * @author liubowen
 * @date 2018/5/11 15:06
 * @description
 */
@Prototype
public class TestModel {

    @Autowired
    private TestService testService;

    @Autowired
    @Qualifier("testBean1")
    private TestBean testBean1;

    @Autowired
    @Qualifier("testBean2")
    private TestBean testBean2;

    public void print() {
        System.err.println("this is the module ...");
        testService.println();
        testBean1.print();
        testBean2.print();
    }
}
