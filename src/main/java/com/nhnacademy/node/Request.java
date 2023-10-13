package com.nhnacademy.node;

import java.util.HashMap;
import java.util.UUID;

public class Request {
    private String url;
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
}
