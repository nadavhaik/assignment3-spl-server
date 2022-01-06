package bgu.spl.net.impl.messages;

import bgu.spl.net.impl.ProtocolException;
import bgu.spl.net.impl.objects.MessagesData;
import bgu.spl.net.impl.objects.User;
import java.util.ArrayList;

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
}
