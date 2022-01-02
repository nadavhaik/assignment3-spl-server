package bgu.spl.net.impl.protocol;

import bgu.spl.net.impl.ProtocolException;
import bgu.spl.net.impl.objects.MessagesData;
import bgu.spl.net.impl.objects.User;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class FollowOrUnfollowMessage extends ClientToServerMessage{
    private boolean follow;
    private String usernameToFollow;
    private User otherUser;
    public FollowOrUnfollowMessage(ArrayList<Byte> message, User user, User otherUser){
        super(MessagesData.Type.FOLLOW_OR_UNFOLLOW, message, user);
        this.otherUser = otherUser;
    }

    @Override
    public void decode(ArrayList<Byte> message) {
        int lastIndex = beginIndex;
        this.follow = message.get(lastIndex) == 0;
        List<Byte> usernameBytes = new ArrayList<>();
        for(lastIndex++; lastIndex < message.size(); lastIndex++) {
            usernameBytes.add(message.get(lastIndex));
        }
        this.usernameToFollow = EncoderDecoder.decodeString(toArr(usernameBytes));
    }

    @Override
    protected void execute() throws ProtocolException {
        boolean unFollow = !follow;
        if(otherUser == null)
            throw new ProtocolException("OTHER USER IS NULL");
        if(!user.isLoggedIn())
            throw new ProtocolException("USER IS NOT LOGGEDIN");
        if (follow) {
            if(user.follow(otherUser))
                throw new ProtocolException("CAN NOT EXECUTE FOLLOW");
        }
        if (unFollow) {
            if (user.unfollow(otherUser))
                throw new ProtocolException("CAN NOT EXECUTE UNFOLLOW");
        }
    }

    @Override
    protected AckMessage ack() {
        byte[] followParams = otherUser.getUsername().getBytes(StandardCharsets.UTF_8);
        AckMessage followAck = new AckMessage(MessagesData.Type.FOLLOW_OR_UNFOLLOW,followParams);
        return followAck;
    }
}