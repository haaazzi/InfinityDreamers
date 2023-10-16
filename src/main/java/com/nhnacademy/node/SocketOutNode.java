package com.nhnacademy.node;

import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class SocketOutNode extends OutputNode {
    Socket socket;

    public SocketOutNode() {
        super();
        setWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
    }
}
