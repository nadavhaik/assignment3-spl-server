package bgu.spl.net.impl.protocol;

import bgu.spl.net.impl.ProtocolException;
import bgu.spl.net.impl.objects.MessagesData;
import bgu.spl.net.impl.objects.User;

import java.util.ArrayList;
import java.util.List;

public class PrivateMessage extends ClientToServerMessage{
    private String userName;
    private String content;
    private String sendingTimeAndDate;
    private User otherUser;

    public PrivateMessage(ArrayList<Byte> message, User user,User otherUser) {
        super(MessagesData.Type.PRIVATE_MESSAGE, message, user);
        this.otherUser = otherUser;
    }

    @Override
    public void decode(ArrayList<Byte> message) {
        int lastIndex;
        List<Byte> usernameBytes = new ArrayList<>();
        for(lastIndex = beginIndex; message.get(lastIndex) != '\0'; lastIndex++) {
            usernameBytes.add(message.get(lastIndex));
        }
        List<Byte> contentBytes = new ArrayList<>();
        for(lastIndex++; message.get(lastIndex) != '\0'; lastIndex++) {
            contentBytes.add(message.get(lastIndex));
        }
        List<Byte> sendingTimeAndDateBytes = new ArrayList<>();
        for(lastIndex++; message.get(lastIndex) != '\0'; lastIndex++) {
            sendingTimeAndDateBytes.add(message.get(lastIndex));
        }
        this.userName = EncoderDecoder.decodeString(toArr(usernameBytes));
        this.content = EncoderDecoder.decodeString(toArr(contentBytes));
        this.sendingTimeAndDate = EncoderDecoder.decodeString(toArr(sendingTimeAndDateBytes));

    }

    @Override
    protected void execute() throws ProtocolException {
        if(!user.isLoggedIn())
            throw new ProtocolException("THE SENDER USER IS NOT LOGGEDIN");
        if(!user.sendPM(otherUser,content,sendingTimeAndDate))
            throw new ProtocolException("CANNOT SEND PM TO OTHERUSER");
    }

    @Override
    protected AckMessage ack() { // no ack for this method ?
        return null;
    }
}
