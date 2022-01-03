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

    private String filter(String content) {
        StringBuilder sb = new StringBuilder(content);
        for(String word : filteredWords) {
            // Edge case -  Word equals whole content
            if(sb.toString().equals(word)) {
                sb = new StringBuilder(word);
                continue;
            }

            // 1. Word is the prefix
            if(sb.toString().startsWith(word + " "))
                sb.replace(0, word.length()-1, replacementWord);
            // 2. Word has occurrences inside
            for(int start = sb.indexOf(" " + word + " "); start != -1;
                    start = sb.indexOf(" " + word + " ")) {
                int end = start + 1 + (word.length()-1) + 1 ; // including both spaces
                sb.replace(start+1, end-1, replacementWord);
            }
            // 3. Word is the postfix
            if(sb.toString().endsWith(" " + word))
                sb.replace(sb.length()-1-word.length(), sb.length()-1, replacementWord);
        }
        return sb.toString();
    }
    public String getContent() {
        return content;
    }
    public User getAuthor() {
        return author;
    }
}
