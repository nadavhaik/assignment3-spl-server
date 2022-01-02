package bgu.spl.net.impl.protocol;
import bgu.spl.net.impl.objects.MessagesData;
import java.util.List;

public abstract class AbstractProtocolMessage {
    final protected static int beginIndex = 2;
    protected MessagesData.Type type;
    public AbstractProtocolMessage(MessagesData.Type type) {
        this.type = type;
    }

    protected byte[] toArr(List<Byte> list) {
        byte[] arr = new byte[list.size()];
        for(int i=0; i<arr.length;i++) {
            arr[i] = list.get(i);
        }

        return arr;
    }


}
