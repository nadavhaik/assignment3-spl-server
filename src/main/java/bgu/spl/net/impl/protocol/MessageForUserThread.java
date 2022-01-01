package bgu.spl.net.impl.protocol;

import bgu.spl.net.impl.ProtocolException;
import bgu.spl.net.impl.objects.User;

import java.util.ArrayList;
import java.util.List;

public abstract class MessageForUserThread extends ClientToServerMessage {
    protected User user;
    public MessageForUserThread(ArrayList<Byte> message, User user) throws ProtocolException {
        super(message);
        this.user = user;
    }
}
