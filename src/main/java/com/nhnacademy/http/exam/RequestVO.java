package com.nhnacademy.http.exam;

import java.util.HashMap;
import java.util.Map;

public class RequestVO {
    private String method;
    private String path;
    private String scheme;

    private Map<String, String> header = new HashMap<>();

    private String domain;
    private String ip;
    private String agent;
    private String accept;
    private String requestHeaderOfFirst;

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getScheme() {
        return scheme;
    }

    public void setScheme(String scheme) {
        this.scheme = scheme;
    }

    public Map<String, String> getHeader() {
        return header;
    }

    public void setHeader(Map<String, String> header) {
        this.header = header;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getAgent() {
        return agent;
    }

    public void setAgent(String agent) {
        this.agent = agent;
    }

    public String getAccept() {
        return accept;
    }

    public void setAccept(String accept) {
        this.accept = accept;
    }

    public String getRequestHeaderOfFirst() {
        return requestHeaderOfFirst;
    }

    public void setRequestHeaderOfFirst(String requestHeaderOfFirst) {
        this.requestHeaderOfFirst = requestHeaderOfFirst;
    }

    @Override
    public String toString() {
        return "RequestVO{" +
            "method='" + method + '\'' +
            ", path='" + path + '\'' +
            ", scheme='" + scheme + '\'' +
            ", header=" + header +
            ", domain='" + domain + '\'' +
            ", ip='" + ip + '\'' +
            ", agent='" + agent + '\'' +
            ", accept='" + accept + '\'' +
            ", requestHeaderOfFirst='" + requestHeaderOfFirst + '\'' +
            '}';
    }
}
