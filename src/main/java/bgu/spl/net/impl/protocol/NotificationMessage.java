package bgu.spl.net.impl.protocol;

import bgu.spl.net.impl.objects.MessagesData;

public class NotificationMessage extends ServerToClientMessage{
    public NotificationMessage(MessagesData.Type originalMessageType) {
        super(MessagesData.Type.NOTIFICATION, originalMessageType);
    }

    @Override
    byte[] encode() {
        return new byte[0];
    }
}
