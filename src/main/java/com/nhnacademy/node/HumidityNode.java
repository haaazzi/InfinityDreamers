package com.nhnacademy.node;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.HashMap;

import com.nhnacademy.WireType;

public class HumidityNode extends InputOutputNode {

    public HumidityNode(String name) {
        super(name);
    }

    String host = "ems.nhnacademy.com";
    HashMap<String, String> options;

    public void process() {
        if ((getInputWire(WireType.HUMIDITY) != null) && getInputWire(WireType.HUMIDITY).hasMessage()) {
            Message message = getInputWire(WireType.HUMIDITY).get();
            String path = message.getRequest().getUrl();
            String requestMessage = "GET " + path + " HTTP/1.1";
            // String requestHeader = "Host: ems.nhnacademy.com:1880";
            // System.out.println(requestHeader);
            boolean isBody = false;
            StringBuilder responseBuilder = new StringBuilder();
            try {
                Socket socket = new Socket(host, 1880);
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
                BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

                writer.write(requestMessage + "\r\n\r\n");
                // writer.write(requestHeader + "\r\n\r\n");
                writer.flush();
                String line;
                int len = 0;
                while ((line = reader.readLine()) != null) {
                    if (line.contains("Content-Length: ")) {
                        len = Integer.parseInt(line.split(": ")[1]);
                    }
                    if (line.isEmpty()) {
                        isBody = true;
                    }
                    if (isBody) {
                        responseBuilder.append(line);
                        if (responseBuilder.length() == len) {
                            break;
                        }
                    }
                }
            } catch (UnknownHostException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            String contents = responseBuilder.toString();
            Response response = message.getResponse();
            response.setContents(contents);
            output(message, WireType.PARSER);
        }
    }
}
