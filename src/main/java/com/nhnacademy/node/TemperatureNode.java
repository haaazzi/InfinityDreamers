package com.nhnacademy.node;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.HashMap;

import com.nhnacademy.WireType;

public class TemperatureNode extends InputOutputNode {
    public TemperatureNode(String name, int inputCount, int outputCount) {
        super(name, inputCount, outputCount);
        // TODO Auto-generated constructor stub
    }

    String host = "ems.nhnacademy.com";
    HashMap<String, String> options;

    public void process() {
        if ((getInputWire(WireType.PARSER) != null) && getInputWire(WireType.PARSER).hasMessage()) {
            Message message = getInputWire(WireType.PARSER).get();
            System.out.println(message.getRequest().getUrl());
            output(message, WireType.PARSER);

            options = message.request.getOptions();

            try {
                Socket socket = new Socket(host, 1880);

            } catch (UnknownHostException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
