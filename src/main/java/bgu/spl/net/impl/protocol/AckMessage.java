package bgu.spl.net.impl.protocol;

import bgu.spl.net.impl.ProtocolException;
import bgu.spl.net.impl.objects.MessagesData;
import bgu.spl.net.impl.objects.User;

import java.util.ArrayList;
import java.util.List;

public class AckMessage extends ServerToClientMessage {
    private List<Byte> params;
    public AckMessage(MessagesData.Type originalMessageType, List<Byte> params) {
        super(MessagesData.Type.ACK, originalMessageType);
        this.params = params;
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
