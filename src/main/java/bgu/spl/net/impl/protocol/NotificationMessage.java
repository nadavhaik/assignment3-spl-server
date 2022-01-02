package bgu.spl.net.impl.protocol;

import bgu.spl.net.impl.objects.AbstractContent;
import bgu.spl.net.impl.objects.MessagesData;

import java.util.ArrayList;
import java.util.List;

public class NotificationMessage extends ServerToClientMessage {

    private AbstractContent post;
    public NotificationMessage(AbstractContent post) {
        super(MessagesData.Type.NOTIFICATION);
        this.post = post;
    }

    @Override
    byte[] encode() {
        List<Byte> bytes = new ArrayList<>();
        byte[] opCode = EncoderDecoder.encodeShort(MessagesData.getInstance().
                getOP(MessagesData.Type.NOTIFICATION));
        
    }
}
