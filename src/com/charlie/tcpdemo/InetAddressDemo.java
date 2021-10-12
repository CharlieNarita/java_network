package com.charlie.tcpdemo;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * demonstrate INetAddress usage
 */
public class InetAddressDemo {
    public static void main(String[] args) throws UnknownHostException {
        //get local InetAddress object
        InetAddress localHost = InetAddress.getLocalHost();
        System.out.println("local host = "+localHost);

        //get InetAddress object by designated host
        String hostName = InetAddress.getLocalHost().getHostName();
        System.out.println("host name = " + hostName);
        InetAddress host1 = InetAddress.getByName(hostName);
        System.out.println("host1 = " + host1);

        //get InetAddress object by domain name (DNS)
        InetAddress host2 = InetAddress.getByName("www.google.com");
        System.out.println("domain = " + host2);

        //get address by InetAddress object
        String hostAddress = host2.getHostAddress();
        System.out.println("domain address = " + hostAddress);

        //get host name or domain name by InetAddress object
        String hostName2 = host2.getHostName();
        System.out.println("domain name = " + hostName2);
    }
}
