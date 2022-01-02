package bgu.spl.net.impl.protocol;

import bgu.spl.net.impl.ProtocolException;
import bgu.spl.net.impl.objects.MessagesData;
import bgu.spl.net.impl.objects.User;

import java.util.ArrayList;

public class ErrorMessage extends ServerToClientMessage {
    private String params;
    public ErrorMessage(MessagesData.Type originalMessageType, String params) {
        super(originalMessageType, MessagesData.Type.ERROR);
        this.params = params;
    }

    @Override
    byte[] encode() {
        return new byte[0];
    }

}
