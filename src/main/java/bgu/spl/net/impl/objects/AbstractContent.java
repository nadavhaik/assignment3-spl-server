package bgu.spl.net.impl.objects;

public abstract class AbstractContent extends ObjectWithCreationTime {
    public AbstractContent() {
        super();
    }
    public abstract String getContent();
}
