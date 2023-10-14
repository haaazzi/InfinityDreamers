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
            JSONObject json = (JSONObject) jsonArray.get(0);
            String method = message.getRequest().getMethod();
            JSONObject newJson = new JSONObject();
            newJson.put("dateTime", json.get("time"));
            newJson.put(method, json.get("value"));

            message.getResponse().setJson(newJson);
            output(message, WireType.SERVER);
        }
        // String contents = "[{\"dateTime\":\"2023-10-14
        // 22:01:04\",\"temperature\":40}]";

    }

}
