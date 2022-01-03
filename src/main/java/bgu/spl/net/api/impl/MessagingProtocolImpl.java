package bgu.spl.net.api.impl;

import bgu.spl.net.api.MessagingProtocol;
import bgu.spl.net.impl.objects.User;
import bgu.spl.net.impl.protocol.AbstractProtocolMessage;
import bgu.spl.net.impl.protocol.ClientToServerMessage;
import bgu.spl.net.impl.protocol.LogoutMessage;

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
        if(m instanceof LogoutMessage)
            shouldTerminate = true;
        return m.actAndRespond();
    }

    @Override
    public boolean shouldTerminate() {
        return this.shouldTerminate;
    }
}
