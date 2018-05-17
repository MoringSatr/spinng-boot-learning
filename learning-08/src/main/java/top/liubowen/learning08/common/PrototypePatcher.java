package top.liubowen.learning08.common;

import javassist.*;
import javassist.expr.ConstructorCall;
import javassist.expr.ExprEditor;
import top.liubowen.learning08.annotation.Inject;
import top.liubowen.learning08.annotation.Prototype;

public class PrototypePatcher implements Patcher {

    public static String prototypeInjectorName(Class<?> clazz) {
        // fetch name like CtClass.getSimpleName
        String qname = clazz.getName();
        int index = qname.lastIndexOf('.');
        return String.format(INJECTOR_NAME, index < 0 ? qname : qname.substring(index + 1));
    }

    private static final String INJECTOR_NAME = "%s$Injector";
    private static final String INJECTOR_FIELD = "private static final top.liubowen.learning08.common.Injector %s = top.liubowen.learning08.common.Injector.build(%s.class, %s.class);";
    private static final String INJECTOR_CALL = "%s.inject(this);";

    /**
     * 当前类是Prototype 1: 有需要Inject的字段 2: 父类不是Prototype并且有需要Inject的字段
     */
    public boolean required(CtClass clazz) throws NotFoundException {
        if (isPrototype(clazz)) {
            CtClass t = clazz;
            do {
                CtField[] fields = t.getDeclaredFields();
                for (CtField ctField : fields) {
                    if (ctField.hasAnnotation(Inject.class))
                        return true;
                }
                t = t.getSuperclass();
            } while (!isPrototype(t) && !Object.class.getName().equals(t.getName()));
        }
        return false;
    }

    private boolean isPrototype(CtClass clazz) throws NotFoundException {
        CtClass temp = clazz;
        while (!Object.class.getName().equals(temp.getName())) {
            if (temp.hasAnnotation(Prototype.class))
                return true;
            temp = temp.getSuperclass();
        }
        return false;
    }

    @Override
    public void patch(CtClass clazz) throws CannotCompileException {
        String injectorName = String.format(INJECTOR_NAME, clazz.getSimpleName());
        clazz.addField(CtField.make(String.format(INJECTOR_FIELD, injectorName, clazz.getName(), Prototype.class.getName()), clazz));
        CtConstructor[] constructors = clazz.getConstructors();

        for (CtConstructor ctConstructor : constructors) {
            final SuperCall superCall = new SuperCall();
            ctConstructor.instrument(new ExprEditor() {
                @Override
                public void edit(ConstructorCall c) throws CannotCompileException {
                    if (c.isSuper())
                        superCall.is = true;
                }
            });
            if (superCall.is) {
                ctConstructor.insertBeforeBody(String.format(INJECTOR_CALL, injectorName));
            }
        }
    }

    static class SuperCall {
        boolean is = false;
    }

}
