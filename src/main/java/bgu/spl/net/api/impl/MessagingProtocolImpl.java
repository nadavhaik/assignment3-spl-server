package bgu.spl.net.api.impl;

import bgu.spl.net.api.MessagingProtocol;
import bgu.spl.net.impl.objects.User;
import bgu.spl.net.impl.protocol.*;

public class MessagingProtocolImpl implements MessagingProtocol<AbstractProtocolMessage> {
    private boolean shouldTerminate;
    private User user;
    public MessagingProtocolImpl() {
        this.user = null;
        shouldTerminate = false;
    }
    @Override
    public AbstractProtocolMessage process(AbstractProtocolMessage msg) {
        if(!(msg instanceof ClientToServerMessage))
            throw new UnsupportedOperationException();
        ClientToServerMessage m = (ClientToServerMessage) msg;
        ResponseMessage response = m.actAndRespond();
        if(m instanceof LogoutMessage || m instanceof RegisterMessage ||
                (m instanceof LoginMessage && response instanceof ErrorMessage))
            shouldTerminate = true;
        else if (m instanceof LoginMessage) {

        }
        return response;
    }

    @Override
    public boolean shouldTerminate() {
        return this.shouldTerminate;
    }
}
