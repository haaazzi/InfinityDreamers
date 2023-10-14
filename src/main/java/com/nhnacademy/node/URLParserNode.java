package com.nhnacademy.node;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.nhnacademy.WireType;

public class URLParserNode extends InputOutputNode {
    Message message;
    String deviceId;
    HashMap<String, String> options;
    WireType type;
    StringBuilder requestBuilder = new StringBuilder();

    public URLParserNode(String name) {
        super(name);
    }

    public long dateConverter(String value) {

        if (value.length() == 16) {
            value += ":00";
        } else if (value.length() == 13) {
            value += ":00:00";
        } else if (value.length() == 9) {
            value += " 00:00:00";
        }

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        Date date;
        long millis = 0;

        try {
            date = sdf.parse(value);
            millis = date.getTime() / 1000;

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return millis;

    }

    public void process() {
        if ((getInputWire(WireType.PARSER) != null) && getInputWire(WireType.PARSER).hasMessage()) {

            message = getInputWire(WireType.PARSER).get();
            Request request = message.getRequest();
            String requestString = "";
            String url = request.getUrl();
            String[] urlOptions = url.split("http://");
            urlOptions = urlOptions[1].split("/");
            urlOptions = urlOptions[1].split("\\?");

            options = new HashMap<String, String>();

            if (urlOptions[1].equals("dev")) {

                if (urlOptions.length > 1) {
                    options.put("deviceId", urlOptions[2]);
                }

                type = WireType.DEVICE;

            } else if (urlOptions[0].equals("temperature") || urlOptions[0].equals("humidity")) {

                requestBuilder.append("/ep/");
                options.put("type", urlOptions[0]);
                requestBuilder.append(urlOptions[0]);
                requestBuilder.append("/24e124126c457594?");

                if (urlOptions[0].equals("temperature")) {
                    type = WireType.TEMPERATURE;
                } else {
                    type = WireType.HUMIDITY;
                }

                if (urlOptions.length > 1) {

                    String urlString = urlOptions[1].replaceAll("\"", "");

                    // GET "http://localhost/humidity?format=json&startDt=2023-10-05
                    // 15:00&endDt=2023-10-05 18:00:00&unit=hour"
                    // http://ems.nhnacademy.com:1880/ep/temperature/24e124126c457594?count=40&st=1696772438&et=1696772438
                    for (String string : urlString.split("&")) {
                        String key = string.split("=")[0];
                        String value = string.split("=")[1];

                        if (key.equals("startDt") || key.equals("endDt")) {
                            if (key.equals("startDt")) {
                                key = "st";
                            } else {
                                key = "et";
                            }
                            value = dateConverter(value) + "";
                        }
                        requestBuilder.append(key + "=" + value + "&");
                        options.put(key, value);
                    }
                    requestString = requestBuilder.toString().substring(0,
                            requestBuilder.toString().length() - 1);
                    // for (Map.Entry<String, String> set : options.entrySet()) {
                    // System.out.println(set.getKey() + " = " + set.getValue());
                    // }

                }
            }

            request.setOptions(options);
            request.setUrl(requestString);
            output(message, type);
        }
    }
}
