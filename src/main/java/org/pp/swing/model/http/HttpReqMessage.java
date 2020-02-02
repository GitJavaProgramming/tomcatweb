package org.pp.swing.model.http;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.pp.swing.model.Message;

/**
 * HTTP请求报文
 */
@NoArgsConstructor
@Data
public class HttpReqMessage implements Message {

    private RequestLine requestLine;
    private RequestHeader requestHeader;
    private RequestEntityBody requestEntityBody;

    /**
     * 有些请求方式没有请求实体主体
     */
    public HttpReqMessage(RequestLine requestLine, RequestHeader requestHeader) {
        this(requestLine, requestHeader, null);
    }

    public HttpReqMessage(RequestLine requestLine, RequestHeader requestHeader, RequestEntityBody requestEntityBody) {
        this.requestLine = requestLine;
        this.requestHeader = requestHeader;
        this.requestEntityBody = requestEntityBody;
    }

}
