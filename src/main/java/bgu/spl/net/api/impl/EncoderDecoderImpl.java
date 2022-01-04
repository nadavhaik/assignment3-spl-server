package bgu.spl.net.api.impl;

import bgu.spl.net.api.MessageEncoderDecoder;
import bgu.spl.net.impl.objects.MessagesData;
import bgu.spl.net.impl.objects.ServerData;
import bgu.spl.net.impl.objects.User;
import bgu.spl.net.impl.protocol.*;

import java.util.ArrayList;
import java.util.List;

public class EncoderDecoderImpl implements MessageEncoderDecoder<AbstractProtocolMessage> {
    private ArrayList<Byte> messageBytes;
    private User user;

    public EncoderDecoderImpl() {
        this.messageBytes = new ArrayList<>();
        this.user = null;
    }

    private boolean isConnected() {
        return user != null;
    }


    private AbstractProtocolMessage decodeMessage() {
        byte[] opCode = {messageBytes.get(0), messageBytes.get(1)};
        ClientToServerMessage message;
        short opOrd = BytesEncoderDecoder.decodeShort(opCode);
        MessagesData.Type messageType = MessagesData.getInstance().getType(opOrd);
        if(messageType == null)
            throw new UnsupportedOperationException("Unsupported OP-Code: " + opOrd);
        switch (messageType) {
            case REGISTER:
                message = new RegisterMessage(messageBytes);
                break;
            case LOGIN:
                message = new LoginMessage(messageBytes, user);
                this.user = ((LoginMessage)message).getUser();
                break;
            case LOGOUT:
                message = new LogoutMessage(messageBytes, user);
                user = null;
                break;
            case FOLLOW_OR_UNFOLLOW:
                message = new FollowOrUnfollowMessage(messageBytes, user);
                break;
            case POST:
                message = new PostMessage(messageBytes, user);
                break;
            case PRIVATE_MESSAGE:
                message = new PMMessage(messageBytes, user);
                break;
            case LOGGEDIN_STATES:
                message = new LogstatMessage(messageBytes, user);
                break;
            case STATISTICS:
                message = new StatMessage(messageBytes, user);
                break;
            case BLOCK:
                message = new BlockMessage(messageBytes, user);
                break;
            case FETCH_NOTIFICATION:
                message = new FetchNotificationMessage(messageBytes, user);
                break;
            default:
                throw new UnsupportedOperationException("Unsupported Type: " + messageType.name());
        }
        return message;

        }

    @Override
    public AbstractProtocolMessage decodeNextByte(byte nextByte) {
        if(nextByte == ';') {
            AbstractProtocolMessage message = decodeMessage();
            messageBytes = new ArrayList<>();
            return message;
        }
        messageBytes.add(nextByte);
        return null;
    }

    @Override
    public byte[] encode(AbstractProtocolMessage message) {
        if(!(message instanceof ServerToClientMessage))
            throw new UnsupportedOperationException();

        return ((ServerToClientMessage)message).encode();
    }
}
