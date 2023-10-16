package com.nhnacademy.node;

import com.nhnacademy.WireType;

public class TCPEcho extends InputOutputNode {

    public TCPEcho(String name) {
        super(name);
    }

    public TCPEcho() {
        super();
    }

    @Override
    void process() {
        if ((getInputWire(WireType.PARSER) != null) && getInputWire(WireType.PARSER).hasMessage()) {
            // Request request = getInputWire(0).get();
            Message message = getInputWire(WireType.PARSER).get();
            System.out.println(message.getRequest().getUrl());
            output(message, WireType.PARSER);
        }
    }
}