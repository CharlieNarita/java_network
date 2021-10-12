package com.charlie.download;

import java.net.*;
import java.io.*;
@SuppressWarnings({"all"})
public class Server {
    public static void main(String[] args) throws Exception{
        //listening at port
        ServerSocket serverSocket = new ServerSocket(9999);
        System.out.println("server is listening...");
        Socket accept = serverSocket.accept();

        //accept file name from channel
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(accept.getInputStream()));
        String fileName = bufferedReader.readLine();
        System.out.println("file name = " + fileName);
        accept.shutdownInput();

        //check file name whether exsits in data base or not
        String filePath = "src\\com\\charlie\\download\\database\\" + fileName;
        File file = new File(filePath);
        if(file.exists()) {
            System.out.println("file is found and ready to return...");
        } else {
            System.out.println("file is not exists!");
        }

        //return the file to channel
        FileInputStream fis = new FileInputStream(filePath);
        byte[] bytes = StreamUtils.streamToByteArray(fis);
        BufferedOutputStream bos = new BufferedOutputStream(accept.getOutputStream());
        bos.write(bytes);
        bos.flush();
        accept.shutdownOutput();

        //close resource
        bos.close();
        bufferedReader.close();
        accept.close();
        serverSocket.close();
        System.out.println("Server is closed");
    }
}
