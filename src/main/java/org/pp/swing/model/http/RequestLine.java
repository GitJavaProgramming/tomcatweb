package org.pp.swing.model.http;

import lombok.Data;

/**
 * 请求行
 */
@Data
public final class RequestLine {
    private RequestMethod method;
    private String url;
    private String version;

    public RequestLine(RequestMethod method, String url, String version) {
        this.method = method;
        this.url = url;
        this.version = version;
    }
}
