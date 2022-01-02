package bgu.spl.net.impl.protocol;

import bgu.spl.net.impl.ProtocolException;
import bgu.spl.net.impl.objects.MessagesData;
import bgu.spl.net.impl.objects.ServerData;
import bgu.spl.net.impl.objects.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class LoginMessage extends ClientToServerMessage{
    private String username;
    private String password;
    private boolean captcha;

    public LoginMessage(ArrayList<Byte> message) throws ProtocolException {
        super(message, null); // user will be parsed from message
        this.type = MessagesData.Type.LOGIN;
    }

    @Override
    public void decode(ArrayList<Byte> message) {
        int lastIndex;
        List<Byte> usernameBytes = new ArrayList<>();
        for(lastIndex = beginIndex; message.get(lastIndex) != '\0'; lastIndex++) {
            usernameBytes.add(message.get(lastIndex));
        }
        List<Byte> passwordBytes = new ArrayList<>();
        for(lastIndex++; message.get(lastIndex) != '\0'; lastIndex++) {
            passwordBytes.add(message.get(lastIndex));
        }
        this.captcha = message.get(message.size()-1) == 1;
        this.username = EncoderDecoder.decodeString(toArr(usernameBytes));
        this.password = EncoderDecoder.decodeString(toArr(passwordBytes));
    }

    @Override
    public void execute() throws ProtocolException {
        if(!captcha)
            throw new ProtocolException("BLABLABLA");
        User user = ServerData.getInstance().getUser(username);
        if(user == null)
            throw new ProtocolException("BLABLABLA");
        else if(user.isLoggedIn())
            throw new ProtocolException("BLABLABLA");
        else if(!user.getPassword().equals(password))
            throw new ProtocolException("BLABLABLA");

        user.login();

    }

    @Override
    protected AckMessage ack() {
        return null;
    }
}
