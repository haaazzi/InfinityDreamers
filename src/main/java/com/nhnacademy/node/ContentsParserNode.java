package com.nhnacademy.node;

import java.util.Map;

import com.nhnacademy.Wire;
import com.nhnacademy.WireType;

public class ContentsParserNode extends InputOutputNode {
    public ContentsParserNode(String name) {
        super(name);
    }

    @Override
    void process() {
        Message message = null;
        if ((getInputWire(WireType.HUMTOPARSER) != null) && getInputWire(WireType.HUMTOPARSER).hasMessage()) {
            message = getInputWire(WireType.HUMTOPARSER).get();
            String format = "";
            if (message.getRequest().getOptions().containsKey("format")) {
                format = message.getRequest().getOptions().get("format");
            } else {
                format = "json";
            }

            if (format.equalsIgnoreCase("json")) {
                output(message, WireType.JSON);
            } else if (format.equalsIgnoreCase("xml")) {
                output(message, WireType.XML);
            } else if (format.equalsIgnoreCase("html")) {
                output(message, WireType.HTML);
            }
        } else if ((getInputWire(WireType.TEMTOPARSER) != null) && getInputWire(WireType.TEMTOPARSER).hasMessage()) {
            message = getInputWire(WireType.TEMTOPARSER).get();
            String format = "";
            if (message.getRequest().getOptions().containsKey("format")) {
                format = message.getRequest().getOptions().get("format");
            } else {
                format = "json";
            }

            if (format.equalsIgnoreCase("json")) {
                output(message, WireType.JSON);
            } else if (format.equalsIgnoreCase("xml")) {
                output(message, WireType.XML);
            } else if (format.equalsIgnoreCase("html")) {
                output(message, WireType.HTML);
            }
        }

    }

}
