package bgu.spl.net.impl.objects;

public abstract class ObjectWithCreationTime {
    private final long creationTime;

    public ObjectWithCreationTime() {
        this.creationTime = System.currentTimeMillis();
    }

    public long getCreationTime() {
        return creationTime;
    }
}
