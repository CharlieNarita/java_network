package com.charlie.socket01;

import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.Socket;

public class SocketTCP01Client {
    public static void main(String[] args) throws Exception {
        //1.connect the server(ip, port)
        //getLocalHost() return a host contain (name + ip)
        int port = 9999;
        Socket socket = new Socket(InetAddress.getLocalHost(), port);
        System.out.println("client sending connection request to server port " + port);
        System.out.println("client socket return = " + socket.getClass());

        //2.generate a Socket object when connect server successful
        //3.sending data into channel through socket.getOutputStream();
        /*
        OutputStream output = socket.getOutputStream();
        output.write("hello, server!".getBytes());
         */
        OutputStreamWriter osw = new OutputStreamWriter(socket.getOutputStream());
        BufferedWriter bw = new BufferedWriter(osw);
        String msg = "hello server!";
        bw.write(msg);
        System.out.println("sending \"" + msg + "\"");
        bw.newLine();
        //release resource
        bw.close();
        System.out.println("msg sent successfully!");
        socket.close();
        System.out.println("client close!");
    }
}
