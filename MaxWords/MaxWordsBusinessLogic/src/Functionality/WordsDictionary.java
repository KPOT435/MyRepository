package Functionality;

import java.util.HashMap;
import java.util.Map;

public class WordsDictionary implements IWordsDictionary
{
    private static volatile WordsDictionary ourInstance; //= new WordsDictionary();

    public static WordsDictionary getInstance()
    {
        WordsDictionary localInstance = ourInstance;
        if (localInstance == null) {
            synchronized (WordsDictionary.class) {
                localInstance = ourInstance;
                if (localInstance == null) {
                    ourInstance = localInstance = new WordsDictionary();
                }
            }
        }
        return localInstance;
    }

    public Map<String, Integer> getWords()
    {
        return words;
    }

    private Map<String, Integer> words;

    private WordsDictionary()
    {
        words = new HashMap<>();
    }

    public void addWord(String key)
    {
        int count = words.get(key);
        words.put(key, count + 1);
    }

    public void addNewWord(String key)
    {
        words.put(key, 1);
    }

    public boolean containsKey(String key)
    {
        return words.containsKey(key);
    }

    public int size()
    {
        return words.size();
    }
}
