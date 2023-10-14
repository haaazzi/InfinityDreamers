package com.nhnacademy.node;

import com.nhnacademy.WireType;

public class ContentsParserNode extends InputOutputNode {

    @Override
    void process() {
        if ((getInputWire(WireType.PARSER) != null) && getInputWire(WireType.PARSER).hasMessage()) {
            Message message = getInputWire(WireType.PARSER).get();
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
