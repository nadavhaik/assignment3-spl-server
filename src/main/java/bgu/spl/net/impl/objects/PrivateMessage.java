package bgu.spl.net.impl.objects;
import java.text.SimpleDateFormat;
import java.time.OffsetDateTime;
import java.util.Date;

public class PrivateMessage extends AbstractContent {
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

    public PrivateMessage(String content, User sender, Date sendDatetime) {
        super(sender, content, true);
        this.content += " " + dateFormat.format(sendDatetime);
    }
}
