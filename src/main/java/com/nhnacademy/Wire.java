package com.nhnacademy;

import java.util.LinkedList;
import java.util.Queue;

import com.nhnacademy.node.Request;

public class Wire {
    Queue<Request> requestQueue;

    public Wire() {
        requestQueue = new LinkedList<>();
    }

    public void put(Request requset) {
        requestQueue.add(requset);
    }

    public boolean hasMessage() {
        return !requestQueue.isEmpty();
    }

    public Request get() {
        return requestQueue.poll();
    }
}