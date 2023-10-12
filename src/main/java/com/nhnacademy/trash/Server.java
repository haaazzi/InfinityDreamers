package com.nhnacademy.trash;
// package com.nhnacademy.server;

// import java.io.BufferedReader;
// import java.io.IOException;
// import java.io.InputStreamReader;
// import java.net.ServerSocket;
// import java.net.Socket;
// import java.util.Date;
// import java.util.LinkedList;
// import java.util.Queue;

// public class Server {
// static int port = 80;

// public static void main(String[] args) {
// try (ServerSocket serverSocket = new ServerSocket(port);
// Socket socket = serverSocket.accept()) {

// // BlackList blackList = new BlackList();
// // for (String i : blackList.getBlackList()) {
// // if (socket.getInetAddress().toString().equals(i)) {
// // System.out.println("나쁜사람!!!!!!!!!");
// // socket.close();
// // System.exit(0);
// // } else {
// // System.out.println("접속시간: " + new Date().toString());
// // System.out.println(
// // "서버 " + socket.getInetAddress() + " 클라이언트와 " + socket.getLocalPort() + "
// 포트로
// // 연결되었습니다.");

// BufferedReader reader = new BufferedReader(new
// InputStreamReader(socket.getInputStream()));

// String url = reader.readLine().split(" ")[1];
// // String url = "/ep/temperature?count=10&st=1696772438&et=1696772438";

// Request request = new Request(url, socket);
// Options options = new Options();
// options.run();

// // }
// // }
// } catch (Exception e) {
// // TODO: handle exception
// }
// }

// }