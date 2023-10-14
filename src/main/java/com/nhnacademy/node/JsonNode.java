package com.nhnacademy.node;

import org.json.JSONArray;
import org.json.JSONObject;

import com.nhnacademy.WireType;

public class JsonNode extends InputOutputNode {

    @Override
    void process() {
        if ((getInputWire(WireType.JSON) != null) && getInputWire(WireType.JSON).hasMessage()) {
            Message message = getInputWire(WireType.JSON).get();
            String contents = message.getResponse().getContents();
            JSONArray jsonArray = new JSONArray(contents);
            message.getResponse().setJson(jsonArray);
            output(message, WireType.PARSER);
        }
    }

}
