package com.nhnacademy.http.exam;

import java.util.Map;

public interface RequestVO {

    void setOrigin(String hostAddress);

    String getScheme();

    String getHost();

    String getPath();

    void setUrl(String s);

    String getOrigin();

    void setPath(String s);

    void setScheme(String s);

    void setHeader(Map<String, String> header);

    void setHost(String s);

    void setArgs(Map<String, String> map);

    void setData(String metaDatum);

    void setJson(Map<String, String> map);

    void setDispositionName(String dispositionName);
    String getDispositionName();
}
