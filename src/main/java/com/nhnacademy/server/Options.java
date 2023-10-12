package com.nhnacademy.server;

import java.util.HashMap;

public class Options {
    String deviceId;
    HashMap<String, String> options;

    public void run() {
        Request request = Request.requestList.poll();
        String url = request.getUrl();

        String[] urlOptions = url.split("/");

        options = new HashMap<String, String>();

        if (urlOptions[1].equals("dev")) {

            options.put("path", "dev");

            if (urlOptions.length > 1) {
                options.put("deviceId", urlOptions[2]);
            }

        } else if (urlOptions[1].equals("ep")) {

            options.put("path", "ep");

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
    }
}
