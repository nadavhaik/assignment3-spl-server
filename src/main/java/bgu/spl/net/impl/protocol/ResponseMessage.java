package bgu.spl.net.impl.protocol;

import bgu.spl.net.impl.objects.MessagesData;

public abstract class ResponseMessage extends ServerToClientMessage{
    protected MessagesData.Type originalMessageType;
    public ResponseMessage(MessagesData.Type type, MessagesData.Type originalMessageType) {
        super(type);
        this.originalMessageType = originalMessageType;
    }
}
