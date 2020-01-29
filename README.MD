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



## 参考资料
* tomcat架构解析 刘光瑞 著 2017/05
* tomcat内核设计剖析 汪建 著 2017/06


