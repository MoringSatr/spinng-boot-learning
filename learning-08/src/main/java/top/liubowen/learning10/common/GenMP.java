package top.liubowen.learning10.common;

import com.google.common.collect.Sets;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.Modifier;
import javassist.NotFoundException;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * 
 * Generate module proxy add inject code
 * 
 * @author luzj
 *
 */
@Slf4j
public class GenMP {

    static final List<Patcher> patchers = new ArrayList<Patcher>();
    static {
        patchers.add(new PrototypePatcher());
    }

    public static void makePatch(List<String> classNames) {
        ClassPool pool = ClassPool.getDefault();
        Set<CtClass> patchedClasses = Sets.newHashSet();
        for (String className : classNames) {
            try {
                makePatch(pool, patchedClasses, pool.get(className));
            } catch (Throwable e) {
                log.error("Patch code  error", e);
            }
        }
    }

    private static void makePatch(ClassPool pool, Set<CtClass> patchedClasses, CtClass clazz) throws Throwable {
        if (patchedClasses.contains(clazz) || dispensablePatch(clazz))
            return;

        makePatch(pool, patchedClasses, clazz.getSuperclass());// 先处理父类

        boolean patched = false;

        for (Patcher patcher : patchers) {
            if (patcher.required(clazz)) {
                patcher.patch(clazz);
                patched = true;
            }
        }

        if (patched)
            clazz.toClass();

        patchedClasses.add(clazz);
    }

    private static boolean dispensablePatch(CtClass clazz) throws NotFoundException {
        try {
            return Object.class.getName().equals(clazz.getName()) || Modifier.isInterface(clazz.getModifiers());
        } catch (Exception e) {
            log.error("clazz name : {}", clazz.getName(), e);
        }
        return false;
    }

    public static Class<?> generate(Class<?> clazz) {
        try {
            ClassPool pool = ClassPool.getDefault();
            CtClass ctParent = pool.getCtClass(clazz.getName());
            // CtClass ct = pool.makeClass(clazz.getName() + "$$Proxy");
            // ct.setSuperclass(ctParent);
            //
            // ct.addConstructor(CtNewConstructor.defaultConstructor(ct));
            //
            // ClassFile ccfile = ct.getClassFile();
            // ConstPool ccpool = ccfile.getConstPool();
            //
            // AnnotationsAttribute attr = new AnnotationsAttribute(ccpool, AnnotationsAttribute.visibleTag);
            // attr.addAnnotation(new Annotation(ccpool, pool.get(Prototype.class.getName())));
            // ccfile.addAttribute(attr);

            new PrototypePatcher().patch(ctParent);
            // Class toClass = ctParent.toClass();
            // Loader cl = new Loader(pool);
            // return cl.loadClass(clazz.getName());
            return ctParent.toClass();
        } catch (Exception e) {
            throw new IllegalArgumentException(e);
        }
    }

}
