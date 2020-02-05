package org.pp.tomcat.classloader;

import java.io.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * 参考 org.apache.catalina.startup.ClassLoaderFactory
 * org.apache.catalina.startup.Bootstrap
 * initClassLoaders()
 *     ClassLoader commonLoader = null;
 *     ClassLoader catalinaLoader = null;
 *     ClassLoader sharedLoader = null;
 */
public class UserDefinedClassLoader extends ClassLoader {

    /**
     * ClassLoader name
     */
    private final String name;

    private String classpath;

    public UserDefinedClassLoader(ClassLoader parent, String name) {
        super(parent);
        setClasspath(getSystemResource("").getPath());
        this.name = name;
    }

    public void setClasspath(String classpath) {
        this.classpath = classpath;
    }

//    @Override
//    public Class<?> loadClass(String name) throws ClassNotFoundException {
//        ClassLoader system = getSystemClassLoader();
//        Class<?> clazz = system.loadClass(name);
//        if (clazz != null) {
//            return clazz;
//        }
//        setClasspath(getSystemResource("").getPath());
//        return findClass(name);
//    }

    /**
     * 使用指定的二进制名称查找类。
     * 有效类名称的示例包括：
     * "java.lang.String"
     * "javax.swing.JSpinner$DefaultEditor"
     * "java.security.KeyStore$Builder$FileBuilder$1"
     * "java.net.URLClassLoader$3$1"
     */
    @Override
    protected Class<?> findClass(String name) {
        InputStream is = null;
        byte[] data = null;
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        String path = classpath + name.replace('.', '/').concat(".class");
        System.out.println("findClass: " + path);
        try {
            is = new FileInputStream(new File(path));
            int c = 0;
            while ((c = is.read()) != -1) {
                outputStream.write(c);
            }
            data = outputStream.toByteArray();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return this.defineClass(name, data, 0, data.length); // 将一个 byte 数组转换为 Class 类的实例
    }

    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {

//        UserDefinedClassLoader classLoader = new UserDefinedClassLoader(getSystemClassLoader()/*系统类加载器，在classpath下存在要加载的类时 会加载*/, "UserDefinedUseDefaultModelClassLoader");

        // 自定义加载器 破坏双亲委派模型 parent=null 否则由于双亲委派模型 AppClassLoader永远都会去类路径先去查找，导致自定义失效
        UserDefinedClassLoader classLoader = new UserDefinedClassLoader(null, "UserDefinedUseDefaultModelClassLoader");

        String name = "org.pp.tomcat.classloader.Test";
        Class<?> clazzOuter = classLoader.loadClass(name);
        clazzOuter.newInstance();


        // 内部类 外部类使用不同加载器
//        classLoader = new UserDefinedClassLoader(getSystemClassLoader()/*系统类加载器，在classpath下存在要加载的类时 会加载*/, "UserDefinedUseDefaultModelClassLoader");
        name = "org.pp.tomcat.classloader.Test$Inner";
//        String path = classpath + name.replace('.', '/').concat(".class");
        Class clazzInner = classLoader.loadClass(name);
        /**
         *
         * 参考
         * https://blog.csdn.net/ldstartnow/article/details/52782420
         */
//        Constructor innerCons = clazzInner.getConstructor(clazzOuter); // java.lang.NoSuchMethodException
        /**
         * java.lang.IllegalAccessException
         * 必须显示指定构造方法
         */
        Constructor innerCons = clazzInner.getDeclaredConstructor(clazzOuter);
        /**
         * java.lang.IllegalAccessException: Class org.pp.tomcat.classloader.UserDefinedClassLoader can not
         * access a member of class org.pp.tomcat.classloader.Test$Inner with modifiers "public"
         * 	at sun.reflect.Reflection.ensureMemberAccess(Reflection.java:102)
         */
        innerCons.setAccessible(true);
        Test.Inner innerClass = (Test.Inner) innerCons.newInstance();
        innerClass.getClassLoader();
    }

    @Override
    public String toString() {
        return this.name;
    }
}
