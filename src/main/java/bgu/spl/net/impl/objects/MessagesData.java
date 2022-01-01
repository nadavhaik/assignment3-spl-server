package bgu.spl.net.impl.objects;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

public class MessagesData {

    private static MessagesData instance = null;

    public enum Type{
        REGISTER,
        LOGIN,
        LOGOUT,
        FOLLOW_OR_UNFOLLOW,
        POST,
        PRIVATE_MESSAGE,
        LOGGEDIN_STATES,
        STATISTICS,
        NOTIFICATION,
        ACK,
        ERROR,
        BLOCK
    }

    final private Map<Short, Type> opCodes;
    final private Map<Type, Short> opTypes;
    private MessagesData() {
        opCodes = new HashMap<Short, Type>() {{
            put((short) 1,  Type.REGISTER);
            put((short) 2,  Type.LOGIN);
            put((short) 3,  Type.LOGOUT);
            put((short) 4,  Type.FOLLOW_OR_UNFOLLOW);
            put((short) 5,  Type.POST);
            put((short) 6,  Type.PRIVATE_MESSAGE);
            put((short) 7,  Type.LOGGEDIN_STATES);
            put((short) 8,  Type.STATISTICS);
            put((short) 9,  Type.NOTIFICATION);
            put((short) 10, Type.ACK);
            put((short) 11, Type.ERROR);
            put((short) 12, Type.BLOCK);
        }};


        opTypes = new HashMap<Type, Short>() {{
            put(Type.REGISTER,          (short) 1);
            put(Type.LOGIN,             (short) 2);
            put(Type.LOGOUT,            (short) 3);
            put(Type.FOLLOW_OR_UNFOLLOW,(short) 4);
            put(Type.POST,              (short) 5);
            put(Type.PRIVATE_MESSAGE,   (short) 6);
            put(Type.LOGGEDIN_STATES,   (short) 7);
            put(Type.STATISTICS,        (short) 8);
            put(Type.NOTIFICATION,      (short) 9);
            put(Type.ACK,               (short) 10);
            put(Type.ERROR,             (short) 11);
            put(Type.BLOCK,             (short) 12);
        }};
    }

    public MessagesData getInstance() {
        if(instance == null)
            instance = new MessagesData();
        return instance;
    }

    public Type getType(short opCode) {
        Type t = opCodes.get(opCode);
        if(t == null)
            throw new NoSuchElementException();
        return t;
    }

    public short getOP(Type type) {
        Short op = opTypes.get(type);
        if(op == null)
            throw new NoSuchElementException();
        return op;
    }


}
