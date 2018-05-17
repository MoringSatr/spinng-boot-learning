package top.liubowen.learning08.test;

import top.liubowen.learning08.annotation.Inject;

/**
 * @author liubowen
 * @date 2018/5/11 15:06
 * @description
 */
public class TestModel {

    @Inject
    private TestService testService;

    @Inject("testBean1")
    private TestBean testBean1;

    @Inject("testBean2")
    private TestBean testBean2;

    public void print() {
        System.err.println("this is the module ...");
        testService.println();
        testBean1.print();
        testBean2.print();
    }
}
