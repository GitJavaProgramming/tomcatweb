# 本项目做tomcat源码研究
    主页 http://tomcat.apache.org/index.html
## 研究环境：  

    Server.服务器版本:     Apache Tomcat/9.0.30
    OS Name:               Windows 10
    OS.版本:               10.0
    架构:                  amd64
    JVM 版本:              1.8.0_221-b11
    JVM.供应商:            Oracle Corporation
    
    IDE   idea2019
## 第一个问题--idea控制台乱码
    ### 乱码解决
    tomcat conf下logging.properties设置文件编码 其中由如下一行，默认utf-8
    java.util.logging.ConsoleHandler.encoding = GBK
    
    ### 猜想---如果idea 开源就可以证实我的猜想了！
    这行配置代码应该是向标准IO输出（等研究tomcat源码到这里在说明），被idea集成后，猜想idea读取ConsoleHandler的输出或者
    读取ConsoleHandler写入本地的文件，然后使用机器本地编码写入console
    本机环境编码默认gbk，所以就出现乱码啦。
    再比如更改上面那行设置后，虽然控制台编码正确了，但是Tomcat Catalina Log中还是乱码，根据（没有源码证明的）现象得出猜想！  

## tomcat版本与支持的规范
    http://tomcat.apache.org/whichversion.html
    这些规范同样可以在tomcat_home/lib下找到，例如servlet-api.jar/META-INF/MANIFEST.MF中可以看到描述：
        Name: javax/servlet/
        Implementation-Title: javax.servlet
        Implementation-Vendor: Apache Software Foundation
        Implementation-Version: 4.0.FR
        Specification-Title: Java API for Servlets
        Specification-Vendor: Sun Microsystems, Inc.
        Specification-Version: 4.0
        
    关于jar包中manifest的更多信息可以参考oracle文档
    https://docs.oracle.com/javase/8/docs/technotes/tools/windows/jar.html#BGBFFFGG
    
    关于JavaEE 8规范可以参考github
    https://javaee.github.io/javaee-spec/javadocs/
## tomcat目录结构

    PowerShell下命令
    PS D:\soft\apache-tomcat-9.0.30> Get-ChildItem -Recurse -Directory -Depth 0
    
        目录: D:\soft\apache-tomcat-9.0.30
    
    
    Mode                LastWriteTime         Length Name
    ----                -------------         ------ ----
    d-----        2020/1/29     15:47                bin         停止、启动脚本
    d-----        2020/1/29     15:47                conf        相关配置
    d-----        2020/1/29     15:47                lib         tomcat运行环境依赖jar包
    d-----        2019/12/7     16:42                logs        默认日志存放目录
    d-----        2020/1/29     15:47                temp        临时文件目录
    d-----        2020/1/29     15:47                webapps     tomcat应用部署目录
    d-----        2019/12/7     16:42                work        web应用JSP代码编译成的servlet临时目录

## 启动脚本 批处理命令与shell
    参考资料中讲的很详细，不用造轮子了
    脚本主要作用就是找到tomcat的可执行文件，设置运行环境(环境变量、JVM参数等)并启动
    
### 生命周期
    Server启动时序
    --Bootstrap.main
    --init
    --Class<?> startupClass = catalinaLoader.loadClass("org.apache.catalina.startup.Catalina");
    -- public Catalina() {
           setSecurityProtection(); // 双重检查单例设计
           ExceptionUtils.preload(); // NO-OP
       }
    -- catalinaDaemon根据command(start,started等)执行。org.apache.catalina.startup.Catalina调用load配置信息并使用
       createStartDigester()创建Digester解析conf/server.xml得到Server，Service，Connnector，Engine，Host，Context
       conf/server.xml中配置的几个监听器也会实例化
       然后就是server start
    -- 默认的Server是StandardServer往上追溯它的顶层接口为Lifecycle,同时继承了LifecycleBase。LifecycleBase为StandardServer
      提供addLifecycleListener(LifecycleListener listener)方法的默认实现，调用addLifecycleListener可以注册监听器（从功能
      上讲，只要是Lifecycle的实现类，就是一个事件源--可以注册，查找，删除监听器以及触发事件），监听器监听（定义好的）事件，
      当事件源触发事件时，监听器需要做相应事件的处理（参考Lifecycle LifecycleEvent源码 Lifecycle源码中的圈圈图很好玩~~）
      LifecycleState事件状态，圈圈图就是这个类的状态转换图  
      
      这里有个疑问fireLifecycleEvent方法并没有在Lifecycle中定义而是在实现的抽象类LifecycleBase中定义，其他继承了Lifecycle
      的子接口都是通过继承LifecycleMBeanBase来增加fireLifecycleEvent功能，为什么要分成Lifecycle和LifecycleBase？？将添加、
      查找、删除监听器与触发事件分离？？JMX？？后续补充
     
    例如 启动时Tomcat Catalina log打印的日志VersionLoggerListener.log调用时序
    -- LifecycleBase.init--setStateInternal--fireLifecycleEvent(Lifecycle.BEFORE_INIT_EVENT)--VersionLoggerListener.log 

## tomcat整体架构
    参考资料写这么详细，感觉没我啥事了，待总结时模拟一个简单的tomcat吧
    参考：
    tomcat架构解析 ch2.1 总体设计
    资源文件夹下的UML类图

## WWW与HTTP协议
    WWW[万维网]使用HTTP[超文本传输协议]作为传输协议
    HTTP:
        请求响应（报文格式--请求行【请求方法、URI、协议版本】 请求首部 实体主体）
        无状态（web服务器依靠session、客户端cookie机制维护连接状态）
        应用层（传输层建立TCP连接）
    JavaWeb与Servlet规范
    servlet服务器端脚本，用于处理请求与响应；Javaweb容器都应该实现servlet规范，对http请求做处理并响应
    Servlet目前规范4.0
## session与cookie



## 参考资料
* 深入剖析tomcat 中文版
* tomcat架构解析 刘光瑞 著 2017/05 
* tomcat内核设计剖析 汪建 著 2017/06
* Servlet规范
* JMX规范

