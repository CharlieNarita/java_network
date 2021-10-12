package com.charlie.clientqueryserver;

import java.net.*;
import java.io.*;
import java.util.Scanner;

public class QueryClient {
    public static void main(String[] args) throws Exception{
        //connect server(ip, port)
        Socket socket = new Socket(InetAddress.getLocalHost(), 9999);

        //get writer
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

        //read user query from keyboard
        Scanner scanner = new Scanner(System.in);
        System.out.println("please enter your query: ");
        String query = scanner.next();

        //write query to server
        writer.write(query);
        writer.newLine();
        writer.flush();

        //read return msg from server
        BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        String answer = reader.readLine();
        System.out.println("the answer is: " + answer);

        //close resource
        reader.close();
        writer.close();
        socket.close();
    }
}
