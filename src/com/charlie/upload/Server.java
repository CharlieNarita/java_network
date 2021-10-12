package com.charlie.upload;

import java.net.*;
import java.io.*;

/**
 *  receive file from client then save file to destination
 *  reply msg to client
 */
@SuppressWarnings({"all"})
public class Server {
    public static void main(String[] args) throws Exception {
        //server listening port 8888
        int port = 8888;
        ServerSocket serverSocket = new ServerSocket(port);
        System.out.println("server is listening at port " + port + " and waiting for connection");

        //waiting for connection
        Socket accept = serverSocket.accept();

        //create InputStream to receive file from the client
        BufferedInputStream bis = new BufferedInputStream(accept.getInputStream());
        //read byte[] content from data channel
        byte[] bytes = StreamUtils.streamToByteArray(bis);
        System.out.println("server receive this file and save it to database:\n" + (new String(bytes)));

        //wirte byte[] content data to designate path file
        String destPath = "src\\com\\charlie\\upload\\database\\SrcFileCopy.txt";
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(destPath));
        bos.write(bytes);   //write the byte[] content data to destination path
        bos.flush();

        //send reply msg to client
        String reply = "server received the file and write it to database successfully!";
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(accept.getOutputStream()));
        writer.write(reply);
        writer.flush(); //refresh content to data channel
        accept.shutdownOutput();    //end mark

        //close resources
        writer.close();
        bos.close();
        bis.close();
        accept.close();
        serverSocket.close();
        System.out.println("all resources closed");
    }
}
