package bgu.spl.net.impl.protocol;

import bgu.spl.net.impl.objects.AbstractContent;
import bgu.spl.net.impl.objects.MessagesData;
import bgu.spl.net.impl.objects.PrivateMessage;

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

        byte notificationType = (post instanceof PrivateMessage) ? (byte) 0 : (byte) 1;
        List<Byte> postingUser = EncoderDecoder.encodeStringToList(post.getAuthor().getUsername());
        List<Byte> content = EncoderDecoder.encodeStringToList(post.getContent());
        
        bytes.add(opCode[0]);
        bytes.add(opCode[1]);
        bytes.add(notificationType);
        bytes.addAll(postingUser);
        bytes.add((byte)'\0');
        bytes.addAll(content);
        bytes.add((byte)'\0');

        return toArr(bytes);

    }
}
