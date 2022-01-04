package bgu.spl.net.impl.objects;

import bgu.spl.net.api.impl.EncoderDecoderImpl;
import bgu.spl.net.api.impl.MessagingProtocolImpl;
import bgu.spl.net.srv.Server;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class ReactorMain {
    public static void main(String[] args) throws UnknownHostException {
        int port = 9400;//Integer.parseInt(args[0]);
        int numOfThreads = 1;//Integer.parseInt(args[1]);
        InetAddress localhost = InetAddress.getLocalHost();
        System.out.println("SYSTEM IP: " + localhost.getHostAddress());
        Server.reactor(numOfThreads, port, MessagingProtocolImpl::new,
                EncoderDecoderImpl::new).serve();
    }
}