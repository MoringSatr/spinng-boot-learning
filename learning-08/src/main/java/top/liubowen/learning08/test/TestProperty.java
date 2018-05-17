package top.liubowen.learning08.test;

import top.liubowen.learning08.annotation.Inject;

/**
 * @author liubowen
 * @date 2018/5/8 19:40
 * @description
 */
public class TestProperty extends TestAbs {

    @Inject
    private TestService testService;

    @Inject("testBean1")
    private TestBean testBean1;

    @Inject("testBean2")
    private TestBean testBean2;

    public TestProperty() {
    }

    public void println() {
        testService.println();
        testBean1.print();
        testBean2.print();
    }
}
