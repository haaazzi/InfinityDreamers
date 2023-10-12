package com.nhnacademy.node;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class SocketInNode extends InputNode {
    Socket socket;

    public SocketInNode(Socket socket) throws IOException {
        this.socket = socket;
        setReader(new BufferedReader(new InputStreamReader(socket.getInputStream())));
    }
}
