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

    @JsonIgnore
    private String dispositionName;


    public String getDispositionName() {
        return dispositionName;
    }

    public void setDispositionName(String dispositionName) {
        this.dispositionName = dispositionName;
    }

    public void setFiles(Map<String, String> files) {
        this.files = files;
    }

    public Map<String, String> getFiles() {
        return files;
    }

    public Map<String, String> getForm() {
        return form;
    }

    public Map<String, String> getJson() {
        return json;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getData() {
        return data;
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

    public Map<String, String> getHeader() {
        return header;
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
