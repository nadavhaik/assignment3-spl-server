package bgu.spl.net.impl.objects;
import java.time.OffsetDateTime;

public class PrivateMessage extends AbstractContent {
    private User receiver;
    private OffsetDateTime sendTime;

    public PrivateMessage(String content, User sender, User receiver, String sendDatetime) {
        super(sender, content, true);
        this.content = content;
        this.receiver = receiver;
        this.sendTime = OffsetDateTime.parse(sendDatetime);
    }
}
