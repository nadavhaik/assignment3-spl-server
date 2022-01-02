package bgu.spl.net.impl.protocol;

import bgu.spl.net.impl.objects.MessagesData;

public class ErrorMessage extends ResponseMessage {
    public ErrorMessage(MessagesData.Type originalMessageType) {
        super(originalMessageType, MessagesData.Type.ERROR);
    }

    @Override
    public byte[] encode() {
        byte[] opCode = BytesEncoderDecoder.encodeShort(MessagesData.getInstance()
                .getOP(MessagesData.Type.ERROR));
        byte[] messageOpCode = BytesEncoderDecoder.encodeShort(MessagesData.getInstance()
                .getOP(originalMessageType));

        return new byte[]{opCode[0], opCode[1], messageOpCode[0], messageOpCode[1], ';'};
    }

}
