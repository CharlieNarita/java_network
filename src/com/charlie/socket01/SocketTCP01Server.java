package com.charlie.socket01;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketTCP01Server {
    public static void main(String[] args) throws IOException {
        //1.listening local designate port, waiting for connection...
        //attention, precondition is no other server occupy this port
        int port = 9999;
        ServerSocket serverSocket = new ServerSocket(port);
        System.out.println("server is listening at port " + serverSocket.getLocalPort() + ", waiting for connection...");

        //2.server will block to waiting when no connection request
        //accept() method will return a Socket object when receive client request
        Socket socket = serverSocket.accept();//now ready to receive
        System.out.println("server socket return = " + socket.getClass());

        //3.receive data from channel by getInputStream() method
        /*
        InputStream input = socket.getInputStream();
        byte[] buf = new byte[1024];
        int readLen = 0;
        while((readLen = input.read(buf)) != -1) {
            System.out.println(new String(buf, 0, readLen));
        }
         */
        InputStreamReader isr = new InputStreamReader(socket.getInputStream());
        BufferedReader br = new BufferedReader(isr);
        String line;
        while ((line = br.readLine()) != null) {
            System.out.println("receiving msg is: " + line);
        }
        //close resource
        br.close();
        socket.close();
        serverSocket.close();
        System.out.println("server close!");
    }
}
