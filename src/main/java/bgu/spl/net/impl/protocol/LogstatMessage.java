package bgu.spl.net.impl.protocol;

import bgu.spl.net.impl.ProtocolException;
import bgu.spl.net.impl.objects.MessagesData;
import bgu.spl.net.impl.objects.ServerData;
import bgu.spl.net.impl.objects.User;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


public class LogstatMessage extends ClientToServerMessage{
    private List<User> loggedInUsers;
    public LogstatMessage(ArrayList<Byte> message, User user) {
        super(MessagesData.Type.LOGGEDIN_STATES, message, user);
    }

    @Override
    public void decode(ArrayList<Byte> message) {}

    @Override
    protected void execute() throws ProtocolException {
        if(!user.isLoggedIn())
            throw new ProtocolException("User isn't logged in!");
        loggedInUsers = new LinkedList<>();
        for(User user : ServerData.getInstance().getAllUsers()) {
            if(user.isLoggedIn())
                loggedInUsers.add(user);
        }
    }

    @Override
    protected AckMessage ack() {
        List<Byte> arguments = new ArrayList<>();
        for(User user : loggedInUsers) {
            byte[] age = EncoderDecoder.encodeShort(user.getAge())
        }
    }
}
