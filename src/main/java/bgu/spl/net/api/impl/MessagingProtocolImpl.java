package bgu.spl.net.api.impl;
import bgu.spl.net.api.MessagingProtocol;
import bgu.spl.net.impl.messages.*;

public class MessagingProtocolImpl implements MessagingProtocol<AbstractProtocolMessage> {
    private boolean shouldTerminate;

    public MessagingProtocolImpl() {
        shouldTerminate = false;
    }
    @Override
    public AbstractProtocolMessage process(AbstractProtocolMessage msg) {
        if(!(msg instanceof ClientToServerMessage))
            throw new UnsupportedOperationException();
        ClientToServerMessage m = (ClientToServerMessage) msg;
        ResponseMessage response = m.actAndRespond();
        if(m instanceof LogoutMessage && response instanceof AckMessage)
            shouldTerminate = true;

        return response;
    }

    @Override
    public boolean shouldTerminate() {
        return this.shouldTerminate;
    }
}
