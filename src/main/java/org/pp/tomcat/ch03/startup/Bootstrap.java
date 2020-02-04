package org.pp.tomcat.ch03.startup;

import org.pp.tomcat.ch03.connector.http.HttpConnector;

/**
 * 从流中请求 构造Request对象
 * HTTP/1.1标准报文：请求行 请求首部 请求实体主体
 * 不同请求方法传参数方式不一样GET、POST
 * 请求首部信息包含cookie信息
 *
 * 测试：http://localhost:8080/servlet/PrimitiveServlet
 */
public final class Bootstrap {
  public static void main(String[] args) {
    HttpConnector connector = new HttpConnector();
    connector.start();
  }
}