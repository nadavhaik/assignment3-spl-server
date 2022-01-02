package bgu.spl.net.impl.protocol;

import bgu.spl.net.impl.ProtocolException;
import bgu.spl.net.impl.objects.MessagesData;
import bgu.spl.net.impl.objects.User;

import java.util.ArrayList;

public class ErrorMessage extends ServerToClientMessage {
    public ErrorMessage(MessagesData.Type originalMessageType) {
        super(originalMessageType, MessagesData.Type.ERROR);
    }

    @Override
    byte[] encode() {
        byte[] opCode = EncoderDecoder.encodeShort(MessagesData.getInstance()
                .getOP(MessagesData.Type.ERROR));
        byte[] messageOpCode = EncoderDecoder.encodeShort(MessagesData.getInstance()
                .getOP(originalMessageType));

        return new byte[]{opCode[0], opCode[1], messageOpCode[0], messageOpCode[1], ';'};
    }

}
