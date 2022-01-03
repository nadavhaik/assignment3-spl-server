package bgu.spl.net.impl.protocol;

import bgu.spl.net.impl.ProtocolException;
import bgu.spl.net.impl.objects.MessagesData;
import bgu.spl.net.impl.objects.User;

import java.util.ArrayList;
import java.util.List;

public class LogoutMessage extends ClientToServerMessage{

    public LogoutMessage(ArrayList<Byte> message, User user){
        super(MessagesData.Type.LOGOUT, message, user);
    }

    @Override
    public void decode(ArrayList<Byte> message) {}

    @Override
    public void execute() throws ProtocolException {
        if(user == null)
            throw new ProtocolException("User is not logged in");
        user.logout();
    }

    @Override
    protected AckMessage ack() {
        return null;
    }

}
