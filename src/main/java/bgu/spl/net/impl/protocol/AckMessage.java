package bgu.spl.net.impl.protocol;

import bgu.spl.net.impl.ProtocolException;
import bgu.spl.net.impl.objects.MessagesData;
import bgu.spl.net.impl.objects.User;

import java.util.ArrayList;

public class AckMessage extends ServerToClientMessage {
    private String params;
    public AckMessage(User user, String params, MessagesData.Type originalMessageType) throws ProtocolException {
        super(MessagesData.Type.ACK, originalMessageType);
        this.params = params;
    }

    @Override
    byte[] encode() {
        throw new UnsupportedOperationException();
    }

}
