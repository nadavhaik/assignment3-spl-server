package bgu.spl.net.impl.protocol;

import bgu.spl.net.impl.ProtocolException;
import bgu.spl.net.impl.objects.MessagesData;
import bgu.spl.net.impl.objects.User;
import com.sun.xml.internal.ws.api.message.Message;

import java.util.ArrayList;
import java.util.List;

public class LogoutMessage extends ClientToServerMessage{

    public LogoutMessage(ArrayList<Byte> message, User user) throws ProtocolException{
        super(message, user);
        this.type = MessagesData.Type.LOGOUT;
    }

    @Override
    public void decode(ArrayList<Byte> message) throws ProtocolException {}

    @Override
    public void execute() throws ProtocolException {
        user.logout();
    }

    @Override
    protected AckMessage ack() {
        return null;
    }

}
