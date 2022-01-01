package bgu.spl.net.impl.protocol;

import bgu.spl.net.impl.ProtocolException;
import bgu.spl.net.impl.objects.User;

import java.util.ArrayList;

public class AckMessage extends ServerToClientMessage {
    private String params;
    public AckMessage(ArrayList<Byte> message, User user, String params) throws ProtocolException {
        super(message, user);

    }

    @Override
    byte[] encode() {
        return new byte[0];
    }

}
