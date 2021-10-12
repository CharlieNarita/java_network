package com.charlie.socket02;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;


/**
 * client send msg to server and receive msg from server
 */
@SuppressWarnings({"all"})
public class SocketTCP02Client {
    public static void main(String[] args) throws Exception {
        int port = 6666;
        Socket socket = new Socket(InetAddress.getLocalHost(), port);
        System.out.println("try to connect server...");
        //send msg to server
        String msg = "hello, server";
        OutputStream output = socket.getOutputStream();
        output.write(msg.getBytes());
        System.out.println("send msg to server: " + msg);
        //shut down output avoid block for wating...
        socket.shutdownOutput();

        //receive msg from server
        InputStream input = socket.getInputStream();
        byte[] buf = new byte[1024];
        int readLen;
        while ((readLen = input.read(buf)) != -1) {
            String msg2 = new String(buf, 0, readLen);
            System.out.println("receive server reply msg: " + msg2);
        }
        socket.shutdownInput();

        //close resource
        output.close();
        input.close();
        socket.close();
        System.out.println("all resource closed!");
    }
}
