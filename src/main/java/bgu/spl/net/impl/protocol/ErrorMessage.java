package bgu.spl.net.impl.protocol;

import bgu.spl.net.impl.ProtocolException;
import bgu.spl.net.impl.objects.MessagesData;
import bgu.spl.net.impl.objects.User;

import java.util.ArrayList;

public class ErrorMessage extends ServerToClientMessage {
    public ErrorMessage(MessagesData.Type originalMessageType) {
        super(originalMessageType, MessagesData.Type.ERROR);
    }

    @Override
    byte[] encode() {
        return new byte[0];
    }

}
