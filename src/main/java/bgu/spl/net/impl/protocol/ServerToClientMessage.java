package bgu.spl.net.impl.protocol;

import bgu.spl.net.impl.objects.MessagesData;

public abstract class ServerToClientMessage extends AbstractProtocolMessage {
    public ServerToClientMessage(MessagesData.Type type) {
        super(type);
    }
    abstract byte[] encode();
}
