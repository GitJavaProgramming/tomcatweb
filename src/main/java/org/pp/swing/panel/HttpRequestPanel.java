package org.pp.swing.panel;

import org.pp.swing.model.http.Header;
import org.pp.swing.model.http.HttpReqMessage;
import org.pp.swing.model.http.ProtocolType;
import org.pp.swing.model.http.RequestMethod;
import org.pp.swing.component.CCombo;
import org.pp.swing.component.list.CList;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.util.ArrayList;

public class HttpRequestPanel extends JPanel {

    public HttpRequestPanel() {
        this.setLayout(new BorderLayout());

        addRequestLinePanel();
        addRequestHeaderPanel();
        addRequestEntityBodyPanel();
    }

    private void addRequestEntityBodyPanel() {
        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createTitledBorder("请求行"));

        // 请求方法
        final CCombo<RequestMethod> methodCombo = new CCombo<>(
                RequestMethod.GET,
                RequestMethod.HEAD,
                RequestMethod.POST,
                RequestMethod.PUT,
                RequestMethod.TRACE,
                RequestMethod.OPTIONS,
                RequestMethod.DELETE);
        // 请求的url
        final JTextField urlTextField = new JTextField(25);
        // 应用协议
        final CCombo<ProtocolType> protocolTypeCombo = new CCombo<>(ProtocolType.HTTP, ProtocolType.HTTPS);

        panel.add(methodCombo);
        panel.add(urlTextField);
        panel.add(protocolTypeCombo);

        add(panel, BorderLayout.NORTH);
    }

    private void addRequestHeaderPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBorder(BorderFactory.createTitledBorder("请求首部"));

//        final JTextArea headPropertiesTextArea = new JTextArea(20, 1);
        final CList<Header> headerCList = new CList<>(defaultHeaderList());
        headerCList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollPane = new JScrollPane(headerCList);
        scrollPane.setFocusCycleRoot(false);
        scrollPane.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEmptyBorder(), "", TitledBorder.CENTER, TitledBorder.TOP));

        panel.add(scrollPane);

        add(panel, BorderLayout.CENTER);
    }


    private void addRequestLinePanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBorder(BorderFactory.createTitledBorder("请求体"));

        final JTextArea entityBodyTextArea = new JTextArea(5, 1);
        JScrollPane scrollPane = new JScrollPane(entityBodyTextArea);
        scrollPane.setFocusCycleRoot(false);
        scrollPane.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEmptyBorder(), "", TitledBorder.CENTER, TitledBorder.TOP));
        panel.add(scrollPane);

        add(panel, BorderLayout.SOUTH);
    }

    /**
     * 默认请求头
     */
    public java.util.List<Header> defaultHeaderList() {
        java.util.List<Header> headers = new ArrayList<>();
        headers.add(new Header("cookie", "cookie"));
        headers.add(new Header("cookie", "cookie"));
        headers.add(new Header("cookie", "cookie"));
        headers.add(new Header("cookie", "cookie"));
        headers.add(new Header("cookie", "cookie"));
        headers.add(new Header("cookie", "cookie"));
        return headers;
    }

    /**
     * 构造HTTP请求报文
     */
    public HttpReqMessage buildHttpRequestMessage() {
        return new HttpReqMessage();
    }
}
