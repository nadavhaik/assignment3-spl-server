package bgu.spl.net.impl.messages;

import bgu.spl.net.impl.BytesEncoderDecoder;
import bgu.spl.net.impl.ProtocolException;
import bgu.spl.net.impl.objects.MessagesData;
import bgu.spl.net.impl.objects.User;

import java.util.ArrayList;

public class PostMessage extends ClientToServerMessage {
    private String content;
    public PostMessage(ArrayList<Byte> message, User user) {
        super(MessagesData.Type.POST, message, user);
    }

    @Override
    public void decode(ArrayList<Byte> message) {
        // removing the op-code bytes and the terminating byte:
        message.remove(0);
        message.remove(0);

        this.content = BytesEncoderDecoder.decodeString(toArr(message));
    }

    @Override
    protected void execute() throws ProtocolException {
        if(user == null)
            throw new ProtocolException("User is not logged in");
        user.post(content);
    }
}
