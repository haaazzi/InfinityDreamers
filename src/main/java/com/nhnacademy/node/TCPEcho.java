package com.nhnacademy.node;

public class TCPEcho extends InputOutputNode {

    public TCPEcho(String name) {
        super(name, 1, 1);
    }

    public TCPEcho() {
        super(1, 1);
    }

    @Override
    void process() {
        if ((getInputWire(0) != null) && getInputWire(0).hasMessage()) {
            //Request request = getInputWire(0).get();
            Message message = getInputWire(0).get();
            System.out.println(message.getRequest().getUrl());
            output(message);
        }
    }
}