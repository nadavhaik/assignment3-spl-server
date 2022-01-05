package bgu.spl.net.impl.protocol;

import bgu.spl.net.impl.ProtocolException;
import bgu.spl.net.impl.objects.MessagesData;
import bgu.spl.net.impl.objects.ServerData;
import bgu.spl.net.impl.objects.User;

import java.util.ArrayList;
import java.util.List;

public class BlockMessage extends ClientToServerMessage{
    private String username;

    public BlockMessage(ArrayList<Byte> message, User user) {
        super(MessagesData.Type.BLOCK, message, user);
    }

    @Override
    public void decode(ArrayList<Byte> message) {
        int lastIndex;
        List<Byte> usernameBytes = new ArrayList<>();
        for(lastIndex = beginIndex; message.get(lastIndex) != '\0'; lastIndex++) {
            usernameBytes.add(message.get(lastIndex));
        }
        this.username = BytesEncoderDecoder.decodeString(toArr(usernameBytes));
    }

    @Override
    protected void execute() throws ProtocolException {
        if(user == null)
            throw new ProtocolException("User is not logged in!");
        User otherUser = ServerData.getInstance().getUser(username);
        if(otherUser == null)
            throw new ProtocolException("no such user register");
        user.block(otherUser);
    }
}
