package Functionality;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public interface IBusinessLogic
{
    String inputValidation(String[] commandLineArgs);

    ArrayList<File> getFiles(String[] args);

    void findWords(File fileList) throws FileNotFoundException;

    void print(int occurrencesNumber, ArrayList<IWord> sortedList);

    ArrayList<IWord> sort();

    int sizeOfDictionary();
}
