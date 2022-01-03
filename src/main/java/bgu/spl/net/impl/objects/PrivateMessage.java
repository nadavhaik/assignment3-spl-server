package bgu.spl.net.impl.objects;
import java.time.OffsetDateTime;
import java.util.Date;

public class PrivateMessage extends AbstractContent {
    private User receiver;
    private Date sendTime;

    public PrivateMessage(String content, User sender, User receiver, Date sendDatetime) {
        super(sender, content, true);
        this.content = content;
        this.receiver = receiver;
        this.sendTime = sendDatetime;
    }
}
