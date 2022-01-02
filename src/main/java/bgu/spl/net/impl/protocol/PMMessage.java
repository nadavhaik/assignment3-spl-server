package bgu.spl.net.impl.protocol;

import bgu.spl.net.impl.ProtocolException;
import bgu.spl.net.impl.objects.MessagesData;
import bgu.spl.net.impl.objects.ServerData;
import bgu.spl.net.impl.objects.User;

import java.util.ArrayList;
import java.util.List;

public class PMMessage extends ClientToServerMessage{
    private String content;
    private String sendingTimeAndDate;
    private User otherUser;

    public PMMessage(ArrayList<Byte> message, User user) {
        super(MessagesData.Type.PRIVATE_MESSAGE, message, user);
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
        this.otherUser = ServerData.getInstance().
                getUser(BytesEncoderDecoder.decodeString(toArr(usernameBytes)));
        this.content = BytesEncoderDecoder.decodeString(toArr(contentBytes));
        this.sendingTimeAndDate = BytesEncoderDecoder.decodeString(toArr(sendingTimeAndDateBytes));

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
