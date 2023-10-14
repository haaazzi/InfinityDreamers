package com.nhnacademy;

import java.util.LinkedList;
import java.util.Queue;

import com.nhnacademy.node.Message;
import com.nhnacademy.node.Request;

import lombok.extern.slf4j.Slf4j;

@Slf4j
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
        Message message = messageQueue.peek();
        Request request = message.getRequest();

        // log.info("ID: {}, Request URL: {}", request.getId(), request.getUrl());
        return messageQueue.poll();
    }

    public WireType getType() {
        return type;
    }

}