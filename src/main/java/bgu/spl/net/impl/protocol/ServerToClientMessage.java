package bgu.spl.net.impl.protocol;

import bgu.spl.net.impl.objects.MessagesData;

public abstract class ServerToClientMessage extends AbstractProtocolMessage {
    protected MessagesData.Type originalMessageType;
    public ServerToClientMessage(MessagesData.Type type, MessagesData.Type originalMessageType) {
        super(type);
        this.originalMessageType = originalMessageType;
    }
    abstract byte[] encode();
}
