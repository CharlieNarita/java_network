package com.charlie.udpdemo2;

import java.net.*;

public class ReceiverA {
    public static void main(String[] args) throws Exception {
        DatagramSocket socket = new DatagramSocket(8888);

        //prepare a packet for store data from channel
        byte[] buf = new byte[64 * 1024];
        DatagramPacket packet = new DatagramPacket(buf, buf.length);

        //invoke receive method
        System.out.println("waiting for receive question...");
        socket.receive(packet);

        //
        buf = packet.getData();
        int bufLen = packet.getLength();
        String question = new String(buf, 0, bufLen);
        String answer = "";
        if ("Java".equalsIgnoreCase(question)) {
            answer = "Java is a computer language";
        } else {
            answer = "what are you saying?";
        }

        //write the answer to client
        buf = answer.getBytes();
        packet = new DatagramPacket(buf, buf.length, InetAddress.getByName("192.168.0.104"),8889);
        socket.send(packet);

        //close resource
        socket.close();
        System.out.println("Receiver is closed");
    }
}
