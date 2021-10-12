package com.charlie.socket03_charstream;

import java.io.*;
import java.net.*;

public class SocketTCP03Client {
    public static void main(String[] args) throws Exception {
        int port = 8888;
        Socket socket = new Socket(InetAddress.getLocalHost(), port);
        System.out.println("client socket return = " + socket.getClass());
        System.out.println("client is trying to connect the server...");

        //send msg to server
        OutputStream outputStream = socket.getOutputStream();
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream));
        String msg = "hello, server";
        bufferedWriter.write(msg);
        //newLine() also is end mark of writing
        bufferedWriter.newLine();
        //must write flush() method, or data can not be written into channel
        bufferedWriter.flush();
        System.out.println("client send msg to server: " + msg);


        //receive the reply from server
        InputStream inputStream = socket.getInputStream();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        String line;
        line = bufferedReader.readLine();
        System.out.println("client receive reply from server: " + line);


        //close resources
        bufferedReader.close();
        bufferedWriter.close();
        socket.close();
        System.out.println("all resources closed");
    }
}
