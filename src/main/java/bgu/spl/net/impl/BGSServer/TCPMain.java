package bgu.spl.net.impl.BGSServer;

import bgu.spl.net.api.impl.EncoderDecoderImpl;
import bgu.spl.net.api.impl.MessagingProtocolImpl;
import bgu.spl.net.srv.Server;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class TCPMain {
    public static void main(String[] args) throws UnknownHostException {
        if(args.length == 0) {
            System.out.println("Usage: port");
            return;
        }
        int port = Integer.parseInt(args[0]);
        InetAddress localhost = InetAddress.getLocalHost();
        System.out.println("SYSTEM IP: " + localhost.getHostAddress());
        Server.threadPerClient(port, MessagingProtocolImpl::new,
                EncoderDecoderImpl::new).serve();
    }
}
