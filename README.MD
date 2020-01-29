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






