package bgu.spl.net.impl.protocol;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class EncoderDecoder {
    public static short decodeShort(byte[] byteArr) {
        short result = (short)((byteArr[0] & 0xff) << 8);
        result += (short)(byteArr[1] & 0xff);
        return result;
    }

    public static byte[] encodeShort(short num) {
        byte[] bytesArr = new byte[2];
        bytesArr[0] = (byte)((num >> 8) & 0xFF);
        bytesArr[1] = (byte)(num & 0xFF);
        return bytesArr;
    }

    public static String decodeString(byte[] s) {
        return new String(s, StandardCharsets.UTF_8);
    }

    public static byte[] encodeString(String s) {
        return s.getBytes(StandardCharsets.UTF_8);
    }

    public static List<Byte> encodeStringToList(String s) {
        List<Byte> bytes = new ArrayList<>();
        byte[] arr = s.getBytes(StandardCharsets.UTF_8);
        for(byte b : arr)
            bytes.add(b);
        return bytes;
    }
}
