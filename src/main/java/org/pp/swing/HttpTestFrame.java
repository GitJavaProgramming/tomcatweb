package org.pp.swing;

import org.pp.swing.handler.WindowEventHandler;
import org.pp.swing.panel.HttpRequestPanel;

import javax.swing.*;
import java.awt.*;

/**
 * 该工具需求参考地址：https://www.sojson.com/http/test.html HTTP请求模拟[新版]
 * HTTP协议参考 HTTP权威指南
 * 模拟HTTP请求工具
 */
public class HttpTestFrame extends JFrame {

    public HttpTestFrame() throws HeadlessException {
        this.setTitle("HTTP测试工具");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        initContentPane();

        getContentPane().add(httpRequestPanel());

        this.pack();
        this.setResizable(false);
        this.addWindowListener(new WindowEventHandler());

        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    private HttpRequestPanel httpRequestPanel() {
        HttpRequestPanel panel = new HttpRequestPanel();
        return panel;
    }

    /**
     * contentPane 覆盖JFrame默认的contentPane
     */
    private void initContentPane() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panel.setPreferredSize(new Dimension(900, 600));
        setContentPane(panel);
    }

    public static void main(String[] args) {
        /* 初始化并显示窗体 */
        SwingUtilities.invokeLater(() -> new HttpTestFrame());

        /* 进程钩子，虚拟机终止前，尽可能快速完成，否则将不会执行 */
        Runtime.getRuntime().addShutdownHook(new Thread(() -> System.out.println("执行钩子，虚拟机终止...")));
    }

}
