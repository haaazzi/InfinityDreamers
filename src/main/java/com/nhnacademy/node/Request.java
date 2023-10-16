package com.nhnacademy.node;

import java.util.HashMap;
import java.util.UUID;

public class Request {
    private String url;
    private String method;
    private UUID id;
    HashMap<String, String> options;

    public Request(UUID id, String url) {
        this.id = id;
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public UUID getId() {
        return id;
    }

    public HashMap<String, String> getOptions() {
        return options;
    }

    public void setOptions(HashMap<String, String> options) {
        this.options = options;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }
}
