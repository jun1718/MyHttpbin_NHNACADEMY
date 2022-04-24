package com.nhnacademy.http.exam;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class Parser {
    private RequestVO requestVO;
    private String[] metaData;
    private String[] metaDataHeader;
    private String metaDataBody;
    private String contentType;
    private String boundary = "";
    private String method = "";

    public Parser(RequestVO requestVO, String requestData) {
        this.requestVO = requestVO;

        metaData = requestData.split("\r\n\r\n");
        metaDataHeader = metaData[0].split("\r\n");

        setContentType();
        if (!boundary.isEmpty() && metaData.length >= 2) {
            metaData = requestData.split(boundary + "\r\n\r\n");
        }

        this.method = metaDataHeader[0].split(" ")[0];


        if (this.method.equals("POST")) {
            metaDataBody = metaData[1];
        }
    }

    public void parse() {
        headerParse();

        if (this.method.equals("POST")) {
            bodyParse();
        }
    }

    private void headerParse() {
        parseStartLine();
        Map<String, String> header = new HashMap<>();

        for (int i = 1; i < metaDataHeader.length; i++) {
            String[] dividedHeader = metaDataHeader[i].split(": ");
            header.put(dividedHeader[0], dividedHeader[1]);
            if (dividedHeader[0].equals("Host")) {
                requestVO.setHost(dividedHeader[1]);
            }
        }

        requestVO.setHeader(header);
        requestVO.setUrl(requestVO.getScheme().toLowerCase() + "://" + requestVO.getHost() + requestVO.getPath());
    }

    private void parseStartLine() {
        String[] startLine = metaDataHeader[0].split(" ");
        requestVO.setPath(startLine[1]);
        requestVO.setScheme(startLine[2].split("/")[0]);
        Map<String, String> map = new LinkedHashMap<>();

        if (requestVO.getPath().contains("?")) {
            String[] parameters = startLine[1].split("\\?");
            String[] nParamter = parameters[1].split("&");

            for (String param : nParamter) {
                String[] mapParameters = param.split("=");
                map.put(mapParameters[0], mapParameters[1]);
            }

            requestVO.setArgs(map);
        }
    }

    private void bodyParse() {
        RequestPostVO post = (RequestPostVO) requestVO;
        ObjectMapper mapper = new ObjectMapper();
        TypeReference<LinkedHashMap<String, String>> typeRef
            = new TypeReference<LinkedHashMap<String, String>>() {};

        if (contentType.equals("application/json")) {
            post.setData(metaDataBody);

            try {
                Map<String, String> map = mapper.readValue(metaDataBody, typeRef);
                post.setJson(map);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        } else if (contentType.equals("multipart/form-data")) {
            Map<String, String> filesMap = new HashMap<String, String>();
            String[] files = metaDataBody.split( "--" + boundary + "\r\n");

            for (String file : files) {
                String[] total = file.split("\r\n\r\n");
                String header = total[0];
                String[] headers = header.split("\r\n");

                if (total.length <= 1) {
                    continue;
                }

                String body = total[1];

                for (String head : headers) {
                    if (head.contains("Content-Disposition")) {
                        String[] dispositionValues = head.split(" ");

                        String name = Arrays.stream(dispositionValues)
                            .filter(a -> a.contains("name"))
                            .collect(Collectors.toList()).get(0).split("=\"")[1].replace("\";", "");

                        String lastBoundary = "--" + boundary + "--" + "\r\n";
                        if (body.contains(lastBoundary)) {
                            body = body.replace(lastBoundary, "");
                        }
                        FileMake fileMake = new FileMake(name, body);
                        filesMap.put(name, body);
                        break;
                    }
                }
                post.setFiles(filesMap);
            }
        }
    }

    private void setContentType() {
        for (int i = 0; i < metaDataHeader.length; i++) {
            if (metaDataHeader[i].contains("Content-Type")) {
                contentType = metaDataHeader[i].split("; ")[0];
                contentType = contentType.split(": ")[1];
                if (contentType.equals("multipart/form-data")) {
                    boundary = metaDataHeader[i].split("; ")[1].split("=")[1];
                }
                break;
            }
        }
    }
}
