package org.pp.swing.model.http;

/**
 * HTTP请求方法
 */
public enum RequestMethod {
    /**
     * 从服务器获取一个文档
     */
    GET,
    /**
     * 只从服务器获取文档首部
     */
    HEAD,
    /**
     * 向服务器发送需要处理的数据
     */
    POST, // 包含实体主体
    /**
     * 将请求的主体部分存储在服务器上
     */
    PUT, // 包含实体主体
    /**
     * 对可能经过代理服务器传送到服务器上的报文进行跟踪
     */
    TRACE,
    /**
     * 决定可以在服务器上执行哪些方法
     */
    OPTIONS,
    /**
     * 从服务器上删除一个文档
     */
    DELETE;
}
