package top.liubowen.learning08.util.scanner;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.*;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.regex.Pattern;

/**
 * class文件扫描
 * 
 * @author luzj
 */
public class ClassScanner {

    private static final String WIN_FILE_SEPARATOR = "\\";
    // 文件分隔符"\"
    private static final String FILE_SEPARATOR = "/";
    // package扩展名分隔符
    private static final String PACKAGE_SEPARATOR = ".";
    // java类文件的扩展名
    private static final String CLASS_FILE_EXT = ".class";
    // jar类文件的扩展名
    private static final String JAR_FILE_EXT = ".jar";
    // 目录
    private static final String APP_CLASS_END = "classes/";

    /**
     * 获取项目的所有classpath ，包括 APP_CLASS_PATH 和所有的jar文件
     */
    private static Set<String> getAllClassPath() throws Exception {
        Set<String> set = new LinkedHashSet<String>();
        URLClassLoader urlClassLoader = (URLClassLoader) Thread.currentThread().getContextClassLoader();
        URL[] urlAry = urlClassLoader.getURLs();
        for (URL url : urlAry) {
            set.add(url.getPath());
        }
        return set;
    }

    /**
     * 获取文件下的所有文件(递归)
     */
    private static Set<File> getAllFiles(File file) {
        Set<File> files = new LinkedHashSet<File>();
        if (!file.isDirectory()) {
            files.add(file);
        } else {
            File[] subFiles = file.listFiles();
            for (File f : subFiles) {
                files.addAll(getAllFiles(f));
            }
        }
        return files;
    }

    /**
     * 获取文件下的所有.class文件
     */
    private static Set<File> getAllClass(File file) {
        // 获取所有文件
        Set<File> files = getAllFiles(file);
        Set<File> classes = new LinkedHashSet<File>();
        // 只保留.class 文件
        for (File f : files) {
            if (f.getName().endsWith(CLASS_FILE_EXT)) {
                classes.add(f);
            }
        }
        return classes;
    }

    /**
     * 得到文件夹下所有class的全包名
     */
    private static Set<String> getClassNameFromDir(File file) {
        Set<File> files = getAllClass(file);
        Set<String> names = new LinkedHashSet<String>();
        for (File f : files) {
            String fname = f.getAbsolutePath();
            fname = fname.replace(WIN_FILE_SEPARATOR, FILE_SEPARATOR);
            int begin = fname.indexOf(APP_CLASS_END);
            int end = fname.indexOf(CLASS_FILE_EXT);
            String tmpName = fname.substring(begin + APP_CLASS_END.length(), end);
            names.add(tmpName.replace(FILE_SEPARATOR, PACKAGE_SEPARATOR));
        }
        return names;
    }

    /**
     * 获取jar文件里的所有class文件名
     */
    private static Set<String> getClassNameFromJar(JarFile jarFile) throws Exception {
        Enumeration<JarEntry> entries = jarFile.entries();
        Set<String> classes = new LinkedHashSet<String>();
        while (entries.hasMoreElements()) {
            JarEntry entry = (JarEntry) entries.nextElement();
            String name = entry.getName();
            if (name.endsWith(CLASS_FILE_EXT)) {
                int end = name.indexOf(CLASS_FILE_EXT);
                String tmpName = name.substring(0, end);
                classes.add(tmpName.replace(FILE_SEPARATOR, PACKAGE_SEPARATOR));
            }
        }
        return classes;
    }

    public static List<Class<?>> scan(String includes) {
        return scan(includes, null);
    }

    public static List<String> scanClassNames(String includes, String excludes) {
        ScanMatch inmatch = new ScanMatch(includes);
        ScanMatch exmatch = new ScanMatch(excludes);

        List<String> ret = new ArrayList<String>();
        try {
            Set<String> allclassPath = getAllClassPath();
            for (String path : allclassPath) {
                if (path.endsWith(JAR_FILE_EXT)) {
                    if (!inmatch.matcher(path) || exmatch.matcher(path))
                        continue;
                    Set<String> classeNames = getClassNameFromJar(new JarFile(new File(path)));
                    for (String className : classeNames) {
                        if (!inmatch.matcher(className) || exmatch.matcher(className))
                            continue;
                        ret.add(className);
                    }
                } else {
                    Set<String> classNames = getClassNameFromDir(new File(path));
                    for (String className : classNames) {
                        if (!inmatch.matcher(className) || exmatch.matcher(className))
                            continue;
                        ret.add(className);
                    }
                }
            }
        } catch (Exception ex) {
            // ignore
        }
        return ret;
    }

    /**
     * 扫描 工程下的所有类，不包括jar文件中的类
     * 
     * @param "lib/*.jar;com.hoolai.xxx"
     */
    public static List<Class<?>> scan(String includes, String excludes) {
        List<Class<?>> ret = new ArrayList<Class<?>>();
        List<String> classNames = scanClassNames(includes, excludes);
        try {
            for (String className : classNames) {
                ret.add(Class.forName(className));
            }
        } catch (Exception ex) {
            // ignore
        }
        return ret;
    }

    private static class ScanMatch {
        Pattern[] patterns;

        public ScanMatch(String res) {
            if (res == null || "".equals(res)) {
                patterns = new Pattern[0];
                return;
            }

            String[] regs = res.split(";");
            patterns = new Pattern[regs.length];
            for (int i = 0; i < regs.length; i++) {
                String reg = regs[i];
                reg = reg.endsWith("*") ? reg : reg + "$";
                reg = reg.replace(".", "\\.");
                reg = reg.replace("*", ".*");
                patterns[i] = Pattern.compile(reg);
            }
        }

        public boolean matcher(String path) {
            for (Pattern p : patterns) {
                if (p.matcher(path).find()) {
                    return true;
                }
            }
            return false;
        }
    }

}
