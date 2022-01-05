package bgu.spl.net.impl.objects;

import bgu.spl.net.api.impl.EncoderDecoderImpl;
import bgu.spl.net.api.impl.MessagingProtocolImpl;
import bgu.spl.net.srv.Server;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class ThreadPerClientMain {
    public static void main(String[] args) throws UnknownHostException {
        int port = Integer.parseInt(args[0]);
        InetAddress localhost = InetAddress.getLocalHost();
        System.out.println("SYSTEM IP: " + localhost.getHostAddress());
        Server.threadPerClient(port, MessagingProtocolImpl::new,
                EncoderDecoderImpl::new).serve();
    }
}
