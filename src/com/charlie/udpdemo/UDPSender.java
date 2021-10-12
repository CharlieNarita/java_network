package com.charlie.udpdemo;

import java.net.*;

public class UDPSender {
    public static void main(String[] args) throws Exception {
        //create socket object to send msg from port 9998
        DatagramSocket socket = new DatagramSocket(9998);

        //create packet object to store msg
        String msg = "hi, go to eating!";
        byte[] data = msg.getBytes();
        //InetAddress.getLocalHost() if two sides in one computer
        DatagramPacket packet = new DatagramPacket(data, data.length, InetAddress.getByName("192.168.0.104"), 9999);

        //send the msg packet to receiver
        socket.send(packet);
        System.out.println("sender say: " + msg);

        //waiting for reply from receiver
        data = new byte[64 * 1024];
        DatagramPacket packet1 = new DatagramPacket(data, data.length);
        socket.receive(packet1);    //block if not data receive

        //open the packet then get real data
        int length = packet1.getLength();
        data = packet1.getData();
        String reply = new String(data, 0, length);
        System.out.println("receiver say: " + reply);

        //close resource
        socket.close();
        System.out.println("resources all closed");
    }
}
