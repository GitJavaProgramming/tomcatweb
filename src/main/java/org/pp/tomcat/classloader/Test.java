package org.pp.tomcat.classloader;

public class Test {

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
