package bgu.spl.net.impl.protocol;

import bgu.spl.net.impl.ProtocolException;
import bgu.spl.net.impl.objects.MessagesData;
import bgu.spl.net.impl.objects.User;

import java.util.ArrayList;

public class LogstatMessage extends ClientToServerMessage{

    public LogstatMessage(ArrayList<Byte> message, User user) {
        super(MessagesData.Type.LOGGEDIN_STATES, message, user);
    }

    @Override
    public void decode(ArrayList<Byte> message) {}

    @Override
    protected void execute() throws ProtocolException {

    }

    @Override
    protected AckMessage ack() {
        return null;
    }
}
