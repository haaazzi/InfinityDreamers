package com.nhnacademy;

import java.util.LinkedList;
import java.util.Queue;

import com.nhnacademy.node.Message;
//import com.nhnacademy.node.Request;

public class Wire {
    Queue<Message> messageQueue;
    WireType type;

    public Wire(WireType type) {
        messageQueue = new LinkedList<>();
        this.type = type;
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

    public WireType getType() {
        return type;
    }

}