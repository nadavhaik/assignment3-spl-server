package bgu.spl.net.impl.messages;

import bgu.spl.net.impl.BytesEncoderDecoder;
import bgu.spl.net.impl.ProtocolException;
import bgu.spl.net.impl.objects.MessagesData;
import bgu.spl.net.impl.objects.ServerData;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RegisterMessage extends ClientToServerMessage{
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

    private String username;
    private String password;
    private Date birthday;

    public RegisterMessage(ArrayList<Byte> message) {
        super(MessagesData.Type.REGISTER, message, null);
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
        List<Byte> bdayBytes = new ArrayList<>();
        for(lastIndex++; message.get(lastIndex) != '\0'; lastIndex++) {
            bdayBytes.add(message.get(lastIndex));
        }

        this.username = BytesEncoderDecoder.decodeString(toArr(usernameBytes));
        this.password = BytesEncoderDecoder.decodeString(toArr(passwordBytes));
        try {
            this.birthday = dateFormat.parse(BytesEncoderDecoder.decodeString(toArr(bdayBytes)));
        } catch (ParseException e) {
            this.birthday = null;
        }
    }

    @Override
    public void execute() throws ProtocolException {
        if(this.birthday == null)
            throw new ProtocolException("Could not parse birthday");
        if(!ServerData.getInstance().register(username, password, birthday))
            throw new ProtocolException("Registration failed");
    }
}
