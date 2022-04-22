package com.nhnacademy.http.exam;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Collections;
import java.util.Map;

public class RequestGetVO implements RequestVO{
    private Map<String, String> args = Collections.emptyMap();
    private Map<String, String> header = Collections.emptyMap();
    private String origin;
    private String url;

    @JsonIgnore
    private String path;

    @JsonIgnore
    private String host;

    @JsonIgnore
    private String scheme;

    public void setScheme(String scheme) {
        this.scheme = scheme;
    }

    public String getScheme() {
        return scheme;
    }

    public String getPath() {
        return path;
    }

    public String getHost() {
        return host;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Map<String, String> getArgs() {
        return args;
    }

    public void setArgs(Map<String, String> args) {
        this.args = args;
    }

    @Override
    public void setData(String metaDatum) {

    }

    @Override
    public void setJson(Map<String, String> map) {

    }

    public Map<String, String> getHeader() {
        return header;
    }

    public void setHeader(Map<String, String> header) {
        this.header = header;
    }


    @Override
    public String toString() {
        return "RequestGetVO{" +
            "args=" + args +
            ", header=" + header +
            ", origin='" + origin + '\'' +
            ", url='" + url + '\'' +
            ", path='" + path + '\'' +
            ", host='" + host + '\'' +
            ", scheme='" + scheme + '\'' +
            '}';
    }
}
