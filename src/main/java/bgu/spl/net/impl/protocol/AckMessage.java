package bgu.spl.net.impl.protocol;

import bgu.spl.net.impl.ProtocolException;
import bgu.spl.net.impl.objects.MessagesData;
import bgu.spl.net.impl.objects.User;

import java.util.ArrayList;

public class AckMessage extends ServerToClientMessage {
    private String params;
    public AckMessage(User user, String params) throws ProtocolException {
        this.params = params;
        this.type = MessagesData.Type.ACK;
    }

    @Override
    byte[] encode() {
        return new byte[0];
    }

}
