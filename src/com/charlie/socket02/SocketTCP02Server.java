package com.charlie.socket02;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * server receive msg from client and reply msg to client
 */
@SuppressWarnings({"all"})
public class SocketTCP02Server {
    public static void main(String[] args) throws Exception {
        int port = 6666;
        //init a ServerSocket object
        ServerSocket serverSocket = new ServerSocket(port);
        System.out.println("server is waiting for connection at port " + port);
        //invoke accept() to return Socket object
        Socket socket = serverSocket.accept();

        //receive msg from client
        InputStream input = socket.getInputStream();
        byte[] buf = new byte[1024];
        int readLen;
        while ((readLen = input.read(buf)) != -1) {
            String msg = new String(buf, 0, readLen);
            System.out.println("receive msg from client: " + msg);
        }
        //shut down input avoid block for waiting...
        socket.shutdownInput();

        //reply msg to client
        String msg2 = "hello, client";
        OutputStream output = socket.getOutputStream();
        output.write(msg2.getBytes());
        System.out.println("reply msg to client: " + msg2);
        socket.shutdownOutput();

        //close resource
        input.close();
        output.close();
        socket.close();
        serverSocket.close();
        System.out.println("all resource closed!");
    }
}
