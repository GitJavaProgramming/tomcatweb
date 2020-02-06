package org.pp.tomcat.classloader;

import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

/**
 * java.lang.ClassNotFoundException: org.pp.tomcat.classloader.TestClassLoader
 */
public class Test {

    public static void main(String[] args) throws MalformedURLException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        URL url = new URL("file:D:\\TestClassLoader.jar");
        URLClassLoader classLoader = new URLClassLoader(new URL[]{url}); // super() ---> getSystemClassLoader() 父加载器
//        ClassLoader systemLoader = ClassLoader.getSystemClassLoader();
//        UserDefinedClassLoader classLoader = new UserDefinedClassLoader(null, "UserDefinedClassLoader");
//        classLoader.setClasspath(url.getPath()); // 需要设置查找路径
        Class<?> clazz = classLoader.loadClass("org.pp.tomcat.classloader.TestClassLoader");
        TestInterface testInterface = (TestInterface) clazz.newInstance();
        System.out.println(testInterface.display());
    }

    public Test() {
        System.out.println(this.getClass().getClassLoader().toString());
    }

    public ClassLoader getClassLoader() {
        ClassLoader classLoader = this.getClass().getClassLoader();
        return classLoader;
    }

    class Inner {
        public Inner() {
        }

        public ClassLoader getClassLoader() {
            ClassLoader classLoader = Test.class.getClassLoader();
            System.out.println(classLoader);
            return classLoader;
        }

    }
}
