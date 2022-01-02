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
        List<Byte> params = new ArrayList<>();
        for(User user : loggedInUsers) {
            byte[] age = EncoderDecoder.encodeShort(user.getAge());
            byte[] numOfPosts = EncoderDecoder.encodeShort(user.getNumberOfPosts());
            byte[] numOfFollowers = EncoderDecoder.encodeShort(user.getNumberOfFollowers());
            byte[] numOfFollowing = EncoderDecoder.encodeShort(user.getNumberOfFollowing());

            params.add(age[0]);
            params.add(age[1]);
            params.add(numOfPosts[0]);
            params.add(numOfPosts[1]);
            params.add(numOfFollowers[0]);
            params.add(numOfFollowers[1]);
            params.add(numOfFollowing[0]);
            params.add(numOfFollowing[1]);
        }

        return new AckMessage(MessagesData.Type.LOGGEDIN_STATES, params);
    }
}
