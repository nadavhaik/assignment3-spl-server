package bgu.spl.net.impl.protocol;
import bgu.spl.net.impl.objects.MessagesData;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class AckMessage extends ServerToClientMessage {
    private final List<Byte> params;
    
    public AckMessage(MessagesData.Type originalMessageType, List<Byte> params) {
        super(MessagesData.Type.ACK, originalMessageType);
        this.params = params;
    }

    public AckMessage(MessagesData.Type originalMessageType) {
        super(MessagesData.Type.ACK, originalMessageType);
        this.params = new LinkedList<>();
    }

    @Override
    byte[] encode() {
        byte[] response = new byte[4 + params.size() + 1]; // OP + MESSAGE OP + PARAMS + ';'
        byte[] opCode = EncoderDecoder.encodeShort(MessagesData.getInstance()
                .getOP(MessagesData.Type.ACK));
        byte[] messageOpCode = EncoderDecoder.encodeShort(MessagesData.getInstance()
                .getOP(originalMessageType));
        response[0] = opCode[0];
        response[1] = opCode[1];
        response[2] = messageOpCode[0];
        response[3] = messageOpCode[1];
        int i = 4;
        for(Byte b : params) {
            response[i] = b;
            i++;
        }
        response[response.length-1] = ';';

        return response;

    }

}
