package com.charlie.upload;

import java.io.*;
import java.net.*;

/**
 * read file from directory and send file to server
 */
@SuppressWarnings({"all"})
public class Client {
    public static void main(String[] args) throws Exception {
        //init client socket object and connect to server port 8888
        int port = 8888;
        Socket socket = new Socket(InetAddress.getLocalHost(), port);

        //create input stream for read file from disk
        String srcPath = "src\\com\\charlie\\upload\\srcdata\\SrcFile.txt";
        BufferedInputStream bis = new BufferedInputStream(new FileInputStream(srcPath));

        //byte[] to store file content by using specific method from tool class StreamUtils
        byte[] bytes = StreamUtils.streamToByteArray(bis);
        System.out.println("client read a file:\n" + (new String(bytes)));

        //create buffered OutputStream for writing file to the server
        BufferedOutputStream bos = new BufferedOutputStream(socket.getOutputStream());
        bos.write(bytes);   //wirte byte[] content into data channel
        bos.flush();
        socket.shutdownOutput();    //client write end mark to notify server

        //receive the reply msg from server then print it
        InputStream input = socket.getInputStream();
        String reply = StreamUtils.streamToString(input);
        System.out.println("client receive the reply msg from server:\n" + reply);

        //close resources
        input.close();
        bos.close();
        bis.close();
        socket.close();
        System.out.println("all resource closed");
    }
}
