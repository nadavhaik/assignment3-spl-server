package bgu.spl.net.api.impl;

import bgu.spl.net.api.MessagingProtocol;
import bgu.spl.net.impl.protocol.AbstractProtocolMessage;
import bgu.spl.net.impl.protocol.ClientToServerMessage;

public class MessagingProtocolImpl implements MessagingProtocol<AbstractProtocolMessage> {
    private boolean shouldTerminate;

    public MessagingProtocolImpl() {
        shouldTerminate = false;
    }
    public void terminate() {
        shouldTerminate = true;
    }
    @Override
    public AbstractProtocolMessage process(AbstractProtocolMessage msg) {
        if(!(msg instanceof ClientToServerMessage))
            throw new UnsupportedOperationException();
        ClientToServerMessage m = (ClientToServerMessage) msg;
        return m.actAndRespond();
    }

    @Override
    public boolean shouldTerminate() {
        return this.shouldTerminate;
    }
}
