package bgu.spl.net.impl.protocol;

public abstract class ServerToClientMessage extends AbstractProtocolMessage {
    abstract byte[] encode();
}
