package bgu.spl.net.impl.messages;

import bgu.spl.net.impl.objects.MessagesData;

import java.util.List;

public abstract class ServerToClientMessage extends AbstractProtocolMessage {
    public ServerToClientMessage(MessagesData.Type type) {
        super(type);
    }
    protected abstract List<Byte> encodeToList();
    public final byte[] encode() {
        List<Byte> bytes = encodeToList();
        if(bytes.contains((byte)';')) {
            if(bytes.get(bytes.size()-1) == ';')
                throw new IllegalArgumentException("';' shouldn't be added manually!");
            throw new IllegalArgumentException("Message is illegal - ';' is an illegal byte!");
        }

        byte[] arr = new byte[bytes.size()+1];
        for(int i=0; i<bytes.size(); i++)
            arr[i] = bytes.get(i);
        arr[bytes.size()] = ';';

        return arr;
    }
}
