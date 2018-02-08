package Functionality;

import java.util.Map;

public interface IWordsDictionary
{

    void addNewWord(String text);

    boolean containsKey(String text);

    void addWord(String text);

    int size();

    Map<String, Integer> getWords();
}
