package bgu.spl.net.impl.protocol;


import bgu.spl.net.impl.ProtocolException;
import bgu.spl.net.impl.objects.AbstractContent;
import bgu.spl.net.impl.objects.MessagesData;
import bgu.spl.net.impl.objects.User;

import java.util.ArrayList;

public class FetchNotificationMessage extends ClientToServerMessage{
    private AbstractContent nextNotification;

    public FetchNotificationMessage(ArrayList<Byte> message, User user) {
        super(MessagesData.Type.FETCH_NOTIFICATION, message, user);
        this.nextNotification = null;
    }

    @Override
    public void decode(ArrayList<Byte> message) {}

    @Override
    protected void execute() throws ProtocolException {
        if(user == null)
            throw new ProtocolException("User is not logged in!");
        nextNotification = user.getNextNotification();
    }

    @Override
    public ResponseMessage actAndRespond() {
        try {
            execute();
        } catch (ProtocolException e) {
            return error();
        }
        if(nextNotification == null)
            return ack();

        return new NotificationMessage(nextNotification);
    }

    @Override
    protected AckMessage ack() {
        return new AckMessage(MessagesData.Type.FETCH_NOTIFICATION);
    }
}
