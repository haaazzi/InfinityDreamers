package com.nhnacademy.node;

import java.util.HashMap;

import com.nhnacademy.WireType;

public class URLParserNode extends InputOutputNode {
    Message message;
    String deviceId;
    HashMap<String, String> options;

    public URLParserNode(String name) {
        super(name, 1, 2);
    }

    public void process() {
        if ((getInputWire(WireType.PARSER) != null) && getInputWire(WireType.PARSER).hasMessage()) {

            message = getInputWire(WireType.PARSER).get();
            Request request = message.getRequest();
            String url = request.getUrl();
            System.out.println(url);
            String[] urlOptions = url.split("/");

            options = new HashMap<String, String>();

            if (urlOptions[1].equals("dev")) {

                if (urlOptions.length > 1) {
                    options.put("deviceId", urlOptions[2]);
                }

            } else if (urlOptions[1].equals("ep")) {

                if (urlOptions.length > 1) {

                    if (urlOptions[2].contains("?")) {
                        options.put("type", urlOptions[2].split("\\?")[0]);

                        String urlString = urlOptions[2].split("\\?")[1];

                        for (String string : urlString.split("&")) {
                            options.put(string.split("=")[0], string.split("=")[1]);
                        }

                        // for (Map.Entry<String, String> set : options.entrySet()) {
                        // System.out.println(set.getKey() + " = " + set.getValue());
                        // }
                    } else {
                        options.put("type", urlOptions[2]);
                    }
                }
            }
            request.setOptions(options);
            output(message, WireType.PARSER);
        }
    }
}
