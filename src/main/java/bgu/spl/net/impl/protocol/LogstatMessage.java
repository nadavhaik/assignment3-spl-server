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
        if(user == null)
            throw new ProtocolException("User is not logged in");
        loggedInUsers = new LinkedList<>();
        for(User otherUser : ServerData.getInstance().getAllUsers()) {
            if(otherUser.isLoggedIn() && !user.hasBlocked(otherUser) && !otherUser.hasBlocked(user))
                loggedInUsers.add(otherUser);
        }
    }

    @Override
    protected AckMessage ack() {
        List<Byte> params = new ArrayList<>();
        for(User user : loggedInUsers) {
            byte[] age = BytesEncoderDecoder.encodeShort(user.getAge());
            byte[] numOfPosts = BytesEncoderDecoder.encodeShort(user.getNumberOfPosts());
            byte[] numOfFollowers = BytesEncoderDecoder.encodeShort(user.getNumberOfFollowers());
            byte[] numOfFollowing = BytesEncoderDecoder.encodeShort(user.getNumberOfFollowing());

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
