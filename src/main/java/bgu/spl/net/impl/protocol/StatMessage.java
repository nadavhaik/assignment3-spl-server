package bgu.spl.net.impl.protocol;

import bgu.spl.net.impl.ProtocolException;
import bgu.spl.net.impl.objects.MessagesData;
import bgu.spl.net.impl.objects.ServerData;
import bgu.spl.net.impl.objects.User;

import java.util.ArrayList;
import java.util.List;

public class StatMessage extends ClientToServerMessage{
    ArrayList<String> userNamesForStatistics;
    ArrayList<User> usersForStatistics;

    public StatMessage(ArrayList<Byte> message, User user) {
        super(MessagesData.Type.STATISTICS, message, user);
        userNamesForStatistics = new ArrayList<>();
    }

    @Override
    public void decode(ArrayList<Byte> message) {
        int lastIndex = beginIndex;
        List<Byte> usernameBytes = new ArrayList<>();
        for(lastIndex = beginIndex; message.get(lastIndex) != '\0'; lastIndex++) {
            if (message.get(lastIndex) != '|')
                usernameBytes.add(message.get(lastIndex));
            else {
                userNamesForStatistics.add(BytesEncoderDecoder.decodeString(toArr(usernameBytes)));
                usernameBytes = new ArrayList<>();
            }
        }
    }

    @Override
    protected void execute() throws ProtocolException {
        if(!user.isLoggedIn())
            throw new ProtocolException("the user is not logged in");
        for (String userName : userNamesForStatistics){
            User otherUser = ServerData.getInstance().getUser(userName);
            if(otherUser == null)
                throw new ProtocolException("no such user register");
            usersForStatistics.add(otherUser);
        }
    }

    @Override
    protected AckMessage ack() {
        List<Byte> params = new ArrayList<>();
        for(User user : usersForStatistics) {
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