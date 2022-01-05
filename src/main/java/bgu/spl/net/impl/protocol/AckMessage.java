package bgu.spl.net.impl.protocol;
import bgu.spl.net.impl.objects.MessagesData;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AckMessage extends ResponseMessage {
    private final List<Byte> params;

    public AckMessage(MessagesData.Type originalMessageType, List<Byte> params) {
        super(MessagesData.Type.ACK, originalMessageType);
        this.params = params;
    }

    public AckMessage(MessagesData.Type originalMessageType, byte[] params) {
        super(MessagesData.Type.ACK, originalMessageType);
        this.params = new ArrayList<>();
        for(byte b : params)
            this.params.add(b);
    }

    public AckMessage(MessagesData.Type originalMessageType) {
        super(MessagesData.Type.ACK, originalMessageType);
        this.params = null;
    }

    @Override
    protected List<Byte> encodeToList() {
        byte[] opCode = BytesEncoderDecoder.encodeShort(MessagesData.getInstance()
                .getOP(MessagesData.Type.ACK));
        byte[] messageOpCode = BytesEncoderDecoder.encodeShort(MessagesData.getInstance()
                .getOP(originalMessageType));


        List<Byte> response = new ArrayList<>(Arrays.asList(opCode[0], opCode[1], messageOpCode[0], messageOpCode[1]));
        // Arrays.asList returns an abstract list that doesn't support adding -
        // we must convert it to ArrayList before adding params.

        if(params != null)
            response.addAll(params);
        return response;
    }
}
