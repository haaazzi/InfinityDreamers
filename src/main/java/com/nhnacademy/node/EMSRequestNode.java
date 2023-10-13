package com.nhnacademy.node;

import java.net.Socket;

public class EMSRequestNode extends InputOutputNode {
    Socket socket;
    Message message;

    public EMSRequestNode(String name) {
        super(name, 1, 1);
        message = getInputWire(0).get();
    }

    public void process() {
        Request request = message.getRequest();
    }
}
