package top.liubowen.learning10.test;

/**
 * @author liubowen
 * @date 2018/5/11 10:49
 * @description
 */
public class TestBean {

    private String name;

    public TestBean(String name) {
        this.name = name;
    }

    public void print() {
        System.err.println("TestBean name : " + name);
    }
}
