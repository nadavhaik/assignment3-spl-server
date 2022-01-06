package bgu.spl.net.impl.messages;

import bgu.spl.net.impl.BytesEncoderDecoder;
import bgu.spl.net.impl.ProtocolException;
import bgu.spl.net.impl.objects.MessagesData;
import bgu.spl.net.impl.objects.ServerData;
import bgu.spl.net.impl.objects.User;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PMMessage extends ClientToServerMessage{
    private String content;
    private Date sendingTimeAndDate;
    private User otherUser;
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy hh:mm");
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
        try {
            this.sendingTimeAndDate = dateFormat.parse(BytesEncoderDecoder.
                    decodeString(toArr(sendingTimeAndDateBytes)));
        } catch (ParseException e) {
            this.sendingTimeAndDate = null;
        }

    }

    @Override
    protected void execute() throws ProtocolException {
        if(user == null)
            throw new ProtocolException("User is not logged in");
        if(sendingTimeAndDate == null)
            throw new ProtocolException("ERROR PARSING SENDING TIME");
        if(!user.sendPM(otherUser,content,sendingTimeAndDate))
            throw new ProtocolException("CANNOT SEND PM TO OTHER USER");
    }
}
