package com.nhnacademy;

import java.util.LinkedList;
import java.util.Queue;

import com.nhnacademy.node.Message;
//import com.nhnacademy.node.Request;

public class Wire {
    Queue<Message> messageQueue;

    public Wire() {
        messageQueue = new LinkedList<>();
    }

    public void put(Message message) {
        messageQueue.add(message);
    }

    public boolean hasMessage() {
        return !messageQueue.isEmpty();
    }

    public Message get() {
        return messageQueue.poll();
    }
}