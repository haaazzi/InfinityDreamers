package com.nhnacademy.node;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import com.github.f4b6a3.uuid.UuidCreator;
import com.nhnacademy.Wire;
import com.nhnacademy.WireType;

public class TCPServer extends InputOutputNode {
    class Handler implements Runnable {
        UUID id;
        Socket socket;
        BufferedReader reader;
        BufferedWriter writer;
        TCPServer server;
        Thread thread;

        public Handler(Socket socket, TCPServer server) throws IOException {
            id = UuidCreator.getTimeBased();
            this.socket = socket;
            this.server = server;
            thread = new Thread(this);
        }

        public UUID getId() {
            return id;
        }

        public void start() {
            thread.start();
        }

        public void write(String message) throws IOException {
            writer.write(message + "\n");
            writer.flush();
        }

        @Override
        public void run() {
            try {
                writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
                reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                StringBuilder builder = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    builder.append(line);
                    if (line.isEmpty()) {
                        break;
                    }
                }
                Message message = new Message(new Request(id, builder.toString()));
                server.output(message, WireType.PARSER);

            } catch (Exception e) {
                // TODO: handle exception
            }
        }

    }

    int port = 8080;
    ServerSocket serverSocket;
    String id;
    Thread receiverThread;
    Map<UUID, Handler> handlerMap;

    public TCPServer(String name) {
        super(name);
        handlerMap = new HashMap<>();
    }

    public Handler getHandler(UUID id) {
        return handlerMap.get(id);
    }

    @Override
    void preprocess() {
        try {
            serverSocket = new ServerSocket(port);
            receiverThread = new Thread(() -> {
                while (!Thread.currentThread().isInterrupted()) {
                    try {
                        if ((getInputWire(WireType.PARSER) != null) && getInputWire(WireType.PARSER).hasMessage()) {
                            try {
                                Message message = getInputWire(WireType.PARSER).get();
                                Handler handler = getHandler(message.getRequest().getId());
                                handler.write(message.getResponse().getJson().toString());
                            } catch (IOException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }
                        }
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            });
            receiverThread.start();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    @Override
    void process() {
        try {
            Socket socket = serverSocket.accept();
            System.out.println(socket.getInetAddress());
            Handler handler = new Handler(socket, this);
            // handler
            handler.start();
            handlerMap.put(handler.getId(), handler);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        TCPServer server = new TCPServer("TCPServer");
        URLParserNode urlParser = new URLParserNode("PARSER");
        HumidityNode humidity = new HumidityNode("humidity");
        ContentsParserNode contentsParser = new ContentsParserNode();
        JsonNode json = new JsonNode();

        Wire wire = new Wire(WireType.PARSER);
        Wire wire2 = new Wire(WireType.HUMIDITY);
        Wire wire3 = new Wire(WireType.PARSER);
        Wire wire4 = new Wire(WireType.JSON);
        Wire wire5 = new Wire(WireType.PARSER);

        server.connectOutputWire(wire);
        urlParser.connectInputWire(wire);
        urlParser.connectOutputWire(wire2);
        humidity.connectInputWire(wire2);
        humidity.connectOutputWire(wire3);
        contentsParser.connectInputWire(wire3);
        contentsParser.connectOutputWire(wire4);
        json.connectInputWire(wire4);
        json.connectOutputWire(wire5);
        server.connectInputWire(wire5);

        contentsParser.start();
        json.start();
        humidity.start();
        urlParser.start();
        server.start();

    }
}
