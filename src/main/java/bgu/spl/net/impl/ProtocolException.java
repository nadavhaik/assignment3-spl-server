package bgu.spl.net.impl;

public class ProtocolException extends Exception{
    private String message;
    public ProtocolException(String message) {
        super();
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
