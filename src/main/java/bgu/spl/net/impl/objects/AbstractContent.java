package bgu.spl.net.impl.objects;

import java.util.regex.Pattern;

public abstract class AbstractContent extends ObjectWithCreationTime {
    private final static String[] filteredWords = {
            "ILLUMINATI",
            "LIZARD PEOPLE",
            "FLAT EARTH",
            "KAHANA WAS RIGHT",
            "COVFEFE"
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

    public String filter(String content) {
        for(String word : filteredWords)
            content = content.replaceAll(Pattern.quote(word), replacementWord);
        return content;
    }
    public String getContent() {
        return content;
    }
    public User getAuthor() {
        return author;
    }
}
