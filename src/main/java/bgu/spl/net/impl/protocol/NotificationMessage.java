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
    protected List<Byte> encodeToList() {
        List<Byte> response = new ArrayList<>();
        byte[] opCode = BytesEncoderDecoder.encodeShort(MessagesData.getInstance().
                getOP(MessagesData.Type.NOTIFICATION));
        byte notificationType = (post instanceof PrivateMessage) ? (byte) 0 : (byte) 1;
        List<Byte> postingUser = BytesEncoderDecoder.encodeStringToList(post.getAuthor().getUsername());
        List<Byte> content = BytesEncoderDecoder.encodeStringToList(post.getContent());

        response.add(opCode[0]);
        response.add(opCode[1]);
        response.add(notificationType);
        response.addAll(postingUser);
        response.add((byte)'\0');
        response.addAll(content);
        response.add((byte)'\0');

        return response;
    }
}
