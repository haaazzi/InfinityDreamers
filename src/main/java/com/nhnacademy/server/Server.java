package com.nhnacademy.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

public class Server {
    static int port = 80;

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(port);
        Socket socket = serverSocket.accept();

        BlackList blackList = new BlackList();
        for (String i : blackList.getBlackList()) {
            if (socket.getInetAddress().toString().equals(i)) {
                System.out.println("나쁜사람!!!!!!!!!");
                socket.close();
                System.exit(0);
            } else {
                System.out.println("접속시간: " + new Date().toString());
                System.out.println(
                        "서버 " + socket.getInetAddress() + " 클라이언트와 " + socket.getLocalPort() + " 포트로 연결되었습니다.");
            }
        }

    }
}