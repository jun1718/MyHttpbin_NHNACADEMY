package com.nhnacademy.http.exam;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Collections;
import java.util.Map;

public class RequestPostVO implements RequestVO {
    private Map<String, String> args = Collections.emptyMap();
    private String data = "";
    private Map<String, String> files = Collections.emptyMap();
    private Map<String, String> form = Collections.emptyMap();
    private Map<String, String> header = Collections.emptyMap();
    private Map<String, String> json = Collections.emptyMap();
    private String origin;
    private String url;

    public void setFiles(Map<String, String> files) {
        this.files = files;
    }

    public Map<String, String> getFiles() {
        return files;
    }

    public void setData(String data) {
        this.data = data;
    }

    public void setJson(Map<String, String> json) {
        this.json = json;
    }

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

    public void setUrl(String url) {
        this.url = url;
    }

    public void setArgs(Map<String, String> args) {
        this.args = args;
    }

    public void setHeader(Map<String, String> header) {
        this.header = header;
    }

    @Override
    public String toString() {
        return "RequestPostVO{" +
            "args=" + args +
            ", data='" + data + '\'' +
            ", files=" + files +
            ", form=" + form +
            ", header=" + header +
            ", json=" + json +
            ", origin='" + origin + '\'' +
            ", url='" + url + '\'' +
            ", path='" + path + '\'' +
            ", host='" + host + '\'' +
            ", scheme='" + scheme + '\'' +
            '}';
    }
}
