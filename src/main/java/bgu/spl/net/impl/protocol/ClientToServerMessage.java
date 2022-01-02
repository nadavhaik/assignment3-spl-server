package bgu.spl.net.impl.protocol;

import bgu.spl.net.impl.ProtocolException;
import bgu.spl.net.impl.objects.User;

import java.util.ArrayList;

public abstract class ClientToServerMessage extends AbstractProtocolMessage {
    public ClientToServerMessage(ArrayList<Byte> message) throws ProtocolException {
        super();
        decode(message);
    }

    public abstract void decode(ArrayList<Byte> message) throws ProtocolException;
    protected abstract void execute() throws ProtocolException;
    protected abstract AckMessage ack();
    private ErrorMessage error(String errorMessage) {
        return new ErrorMessage(errorMessage);
    }
    public ServerToClientMessage act() {
        try {
            execute();
        } catch (ProtocolException e) {
            return error(e.getMessage());
        }
        return ack();
    }

}
