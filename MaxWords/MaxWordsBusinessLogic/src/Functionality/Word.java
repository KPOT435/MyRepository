package Functionality;

public class Word implements Comparable, IWord
{

    public int getValue()
    {
        return value;
    }

    private String key;
    private int value;

    Word(String key, int value)
    {
        this.key = key;
        this.value = value;
    }

    /**
     *
     * @param compareValue
     * @return
     */
    @Override
    public int compareTo(Object compareValue)
    {
        int compareCount = ((Word) compareValue).getValue();
        return compareCount - this.value;

    }

    @Override
    public String toString()
    {
        return "Word: '" + key + "' \t\tOccurred: '" + value + "' times";
    }


}
