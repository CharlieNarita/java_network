package com.charlie.download;

import java.net.*;
import java.io.*;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) throws Exception {
        //connect to server
        Socket socket = new Socket(InetAddress.getLocalHost(), 9999);

        //read file name from keyboard input
        Scanner scanner = new Scanner(System.in);
        System.out.println("please enter file name: ");
        String fileName = scanner.next();

        //put the fileName into channel
        BufferedWriter bufferedWriter =
                new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        bufferedWriter.write(fileName);
        bufferedWriter.newLine();
        bufferedWriter.flush();
        socket.shutdownOutput();

        //read the file from channel
        BufferedInputStream bis = new BufferedInputStream(socket.getInputStream());
        byte[] bytes = StreamUtils.streamToByteArray(bis);
        socket.shutdownInput();
        System.out.println("file received from channel...");

        //save the file into destination
        String filePath = "src\\com\\charlie\\download\\destination\\java_doc_copy.pdf";
        FileOutputStream fos = new FileOutputStream(filePath);
        fos.write(bytes);
        fos.flush();
        System.out.println("file saved into destination directory!");

        //close resources
        fos.close();
        bis.close();
        bufferedWriter.close();
        socket.close();
        System.out.println("Client is closed");
    }
}
