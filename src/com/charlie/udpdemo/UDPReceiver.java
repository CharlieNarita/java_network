package com.charlie.udpdemo;

import java.net.*;

public class UDPReceiver {
    public static void main(String[] args) throws Exception {
        //create a DatagramSocket object for receiving data from designate port
        DatagramSocket socket = new DatagramSocket(9999);

        //create a DatagramPacket object for receiving data
        byte[] buf = new byte[64 * 1024];
        DatagramPacket packet = new DatagramPacket(buf, buf.length);    //have an empty packet

        //invoke receive method, it is blocking method
        System.out.println("receiver is waiting for data...");
        socket.receive(packet); //block if not data received

        //open the packet then get data
        int length = packet.getLength();    //get data real length
        buf = packet.getData();     //get data itself
        String msg = new String(buf, 0, length);
        System.out.println("sender say: " + msg);

        //reply msg to package
        String reply = "ok, let's have nice food";
        buf = reply.getBytes();
        DatagramPacket packet1 = new DatagramPacket(buf, buf.length, InetAddress.getByName("192.168.0.104"), 9998);

        //send msg package to sender
        socket.send(packet1);
        System.out.println("receiver say: " + reply);

        //close resource
        socket.close();
        System.out.println("all resources closed");
    }
}
