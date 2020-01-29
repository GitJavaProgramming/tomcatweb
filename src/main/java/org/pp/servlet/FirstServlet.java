package org.pp.servlet;

import org.apache.catalina.startup.CatalinaProperties;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Properties;

/**
 * 获取系统属性、环境变量、tomcat属性
 * 各属性意义参考相关文档
 */
public class FirstServlet extends javax.servlet.http.HttpServlet {

    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        Properties properties = System.getProperties(); // 获取当前系统属性
        properties.list(System.out);
        System.out.println("******************************************************************************");
        System.out.println(System.getenv()); // 获取系统环境变量快照
        System.out.println("******************************************************************************");
        Field[] fields = CatalinaProperties.class.getDeclaredFields();
        Arrays.stream(fields).parallel().forEach(System.out::println);
        System.out.println("******************************************************************************");
        try {
            fields[1].setAccessible(true); // private static Properties properties 通过CatalinaProperties.loadProperties初始化
            /* 获取tomcat设置的属性 静态属性被所有对象共享 */
            Properties properties1 = (Properties) fields[1].get(CatalinaProperties.class.newInstance());
            properties1.list(System.out);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        doPost(request, response);
    }
}
