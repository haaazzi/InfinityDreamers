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
        super(name, 1, 1);
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
                                System.out.println(message.getRequest().getUrl());
                                Handler handler = getHandler(message.getRequest().getId());
                                handler.write(message.getRequest().getUrl());
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
        TCPEcho echo = new TCPEcho();
        URLParserNode parser = new URLParserNode("PARSER");
        Wire wire = new Wire(WireType.PARSER);
        Wire wire2 = new Wire(WireType.PARSER);
        Wire wire3 = new Wire(WireType.PARSER);

        server.connectOutputWire(wire);
        parser.connectInputWire(wire);
        parser.connectOutputWire(wire2);
        echo.connectInputWire(wire2);
        echo.connectOutputWire(wire3);
        server.connectInputWire(wire3);
        echo.start();
        parser.start();
        server.start();

    }
}
