package bgu.spl.net.impl.protocol;

import bgu.spl.net.impl.objects.AbstractContent;
import bgu.spl.net.impl.objects.MessagesData;
import bgu.spl.net.impl.objects.PrivateMessage;

import java.util.ArrayList;
import java.util.List;

public class NotificationMessage extends ResponseMessage {

    private final AbstractContent post;
    public NotificationMessage(AbstractContent post) {
        super(MessagesData.Type.NOTIFICATION, MessagesData.Type.FETCH_NOTIFICATION);
        this.post = post;
    }

    @Override
    public byte[] encode() {
        List<Byte> bytes = new ArrayList<>();
        byte[] opCode = BytesEncoderDecoder.encodeShort(MessagesData.getInstance().
                getOP(MessagesData.Type.NOTIFICATION));

        byte notificationType = (post instanceof PrivateMessage) ? (byte) 0 : (byte) 1;
        List<Byte> postingUser = BytesEncoderDecoder.encodeStringToList(post.getAuthor().getUsername());
        List<Byte> content = BytesEncoderDecoder.encodeStringToList(post.getContent());

        bytes.add(opCode[0]);
        bytes.add(opCode[1]);
        bytes.add(notificationType);
        bytes.addAll(postingUser);
        bytes.add((byte)'\0');
        bytes.addAll(content);
        bytes.add((byte)'\0');
        bytes.add((byte)';');

        return toArr(bytes);
    }
}
