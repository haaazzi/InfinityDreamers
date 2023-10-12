package com.nhnacademy.node;

import java.net.Socket;
import java.util.LinkedList;
import java.util.Queue;
import java.util.UUID;

public class Request {
    private String url;
    private UUID id;

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
}
