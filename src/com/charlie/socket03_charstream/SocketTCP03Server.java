package com.charlie.socket03_charstream;

import java.io.*;
import java.net.*;

public class SocketTCP03Server {
    public static void main(String[] args) throws Exception {
        int port = 8888;
        ServerSocket serverSocket = new ServerSocket(port);
        System.out.println("server socket return = " + serverSocket.getClass());
        System.out.println("server is waiting for connection request...");
        Socket accept = serverSocket.accept();

        //char stream InputStreamReader to BufferedReader
        InputStream inputStream = accept.getInputStream();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        String line;
        line = bufferedReader.readLine();
        System.out.println("server receive msg from client: " + line);


        //reply msg to client
        OutputStream outputStream = accept.getOutputStream();
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream));
        String reply = "hello, client";
        bufferedWriter.write(reply);
        //newLine() as an end mark
        bufferedWriter.newLine();
        //must write flush() method!
        bufferedWriter.flush();
        System.out.println("server reply msg to client: " + reply);


        //close resource
        bufferedWriter.close();
        bufferedReader.close();
        accept.close();
        serverSocket.close();
        System.out.println("all resources closed");
    }
}
