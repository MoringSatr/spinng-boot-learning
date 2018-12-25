package top.liubowen.learning08.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

/**
 * @author liubowen
 * @date 2018/5/8 19:40
 * @description
 */
public class TestProperty extends TestAbs {

    @Autowired
    private TestService testService;

    @Autowired
    @Qualifier("testBean1")
    private TestBean testBean1;

    @Autowired
    @Qualifier("testBean2")
    private TestBean testBean2;

    public TestProperty() {
    }

    public void println() {
        testService.println();
        testBean1.print();
        testBean2.print();
    }
}
