package com.nhnacademy.node;

import java.net.Socket;
import java.util.LinkedList;
import java.util.Queue;

public class Request {
    private String url;
    private Socket socket;
    public static Queue<Request> requestList = new LinkedList<>();

    public Request(String url, Socket socket) {
        this.url = url;
        this.socket = socket;
        requestList.add(this);
    }

    public String getUrl() {
        return url;
    }
}
