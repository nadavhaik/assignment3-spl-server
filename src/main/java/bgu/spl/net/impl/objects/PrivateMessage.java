package bgu.spl.net.impl.objects;
import java.time.OffsetDateTime;

public class PrivateMessage extends AbstractContent {
    private String content;
    private User sender;
    private User receiver;
    private OffsetDateTime sendTime;

    public PrivateMessage(String content, User sender, User receiver, String sendDatetime) {
        super();
        this.content = content;
        this.sender = sender;
        this.receiver = receiver;
        this.sendTime = OffsetDateTime.parse(sendDatetime);
    }
}
