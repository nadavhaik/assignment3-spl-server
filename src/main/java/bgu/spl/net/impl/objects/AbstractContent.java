package bgu.spl.net.impl.objects;

public abstract class AbstractContent extends ObjectWithCreationTime {
    private final static String[] offensiveWords = {
            // Hard coded - no null or empty strings allowed here!
            // Also, the reserved word '<filtered>' is not allowed.
            "Trump",
            "war",
            "אילומינטי",
            "אנשי לטאה",
            "כדור הארץ שטוח",
            "כהנא צדק",
            "covfefe",
            "התקווה 6 דווקא סבבה",
            "הבוחן באלגו לא היה כזה קשה"
    };
    private final static String replacementWord = "<filtered>";

    protected String content;
    protected User author;
    public AbstractContent(User author, String content, boolean shouldFilter) {
        super();
        this.author = author;
        if(shouldFilter)
            this.content = filter(content);
        else
            this.content = content;
    }

    private String filter(String content) {
        for(String word : offensiveWords)
            content = content.replace(word, replacementWord);
        return content;
    }

    public String getContent() {
        return content;
    }

    public User getAuthor() {
        return author;
    }
}
