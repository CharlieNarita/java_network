package com.charlie.udpdemo2;

import java.net.*;
import java.util.Scanner;

public class SenderA {
    public static void main(String[] args) throws Exception {
        DatagramSocket socket = new DatagramSocket(8889);

        //read question from keyboard input
        Scanner scanner = new Scanner(System.in);
        System.out.println("please enter your question: ");
        String question = scanner.next();

        //package question into packet obj
        byte[] data = question.getBytes();
        DatagramPacket packet = new DatagramPacket(data, data.length, InetAddress.getByName("192.168.0.104"), 8888);
        socket.send(packet);

        //prepare a packet for receiving data from channel
        data = new byte[64 * 1024];
        packet = new DatagramPacket(data, data.length);

        //receive the data
        socket.receive(packet);

        //unpack the packet
        data = packet.getData();
        int dataLen = packet.getLength();
        String answer = new String(data, 0, dataLen);
        System.out.println("the answer is: " + answer);

        //close resource
        socket.close();
        System.out.println("Sender is closed");
    }
}
