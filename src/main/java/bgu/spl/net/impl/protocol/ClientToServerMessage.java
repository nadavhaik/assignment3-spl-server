package bgu.spl.net.impl.protocol;

import bgu.spl.net.impl.ProtocolException;
import bgu.spl.net.impl.objects.User;

import java.util.ArrayList;

public abstract class ClientToServerMessage extends AbstractProtocolMessage {
    protected User user;
    public ClientToServerMessage(ArrayList<Byte> message, User user) {
        super();
        this.user = user;
        decode(message);
    }

    public abstract void decode(ArrayList<Byte> message);
    protected abstract void execute() throws ProtocolException;
    protected abstract AckMessage ack();
    private ErrorMessage error(String errorMessage) {
        return new ErrorMessage(errorMessage);
    }
    final public ServerToClientMessage actAndRespond() {
        try {
            execute();
        } catch (ProtocolException e) {
            return error(e.getMessage());
        }
        return ack();
    }

}
