package com.charlie.clientqueryserver;

import java.io.*;
import java.net.*;

public class QueryServer {
    public static void main(String[] args) throws IOException {
        //create a server socket obj listening port
        int port = 9999;
        ServerSocket serverSocket = new ServerSocket(port);
        System.out.println("server is listening at port " + port);

        //create a socket obj to accept data
        Socket accept = serverSocket.accept();

        //read query from client
        BufferedReader reader = new BufferedReader(new InputStreamReader(accept.getInputStream()));
        String s = reader.readLine();
        String answer;
        if("name".equals(s)) {
            answer = "I'm developer_K";
        } else if("hobby".equals(s)) {
            answer = "coding Java";
        } else {
            answer = "what are you saying?";
        }

        //write answer to client
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(accept.getOutputStream()));
        writer.write(answer);
        writer.newLine();
        writer.flush();

        //close resources
        writer.close();
        reader.close();
        accept.close();
        serverSocket.close();
    }
}
