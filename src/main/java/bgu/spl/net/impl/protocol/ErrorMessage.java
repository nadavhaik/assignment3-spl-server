package bgu.spl.net.impl.protocol;

import bgu.spl.net.impl.objects.MessagesData;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ErrorMessage extends ResponseMessage {

    public ErrorMessage(MessagesData.Type originalMessageType) {
        super(originalMessageType, MessagesData.Type.ERROR);
    }

    @Override
    protected List<Byte> encodeToList() {
        byte[] opCode = BytesEncoderDecoder.encodeShort(MessagesData.getInstance()
                .getOP(MessagesData.Type.ERROR));
        byte[] messageOpCode = BytesEncoderDecoder.encodeShort(MessagesData.getInstance()
                .getOP(originalMessageType));

        return Arrays.asList(opCode[0], opCode[1], messageOpCode[0], messageOpCode[1]);
    }

}