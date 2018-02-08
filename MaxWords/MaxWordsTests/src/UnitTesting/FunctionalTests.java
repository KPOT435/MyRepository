package UnitTesting;

import Functionality.BusinessLogic;
import Functionality.IBusinessLogic;
import Functionality.IWord;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;

public class FunctionalTests
{
    private IBusinessLogic func;
    private String desktop = System.getProperty("user.home") + "/Desktop";

    private String wrongNumberOfArgs = "wrong num agrs";
    private String secondArgIsNotNumber = "sec arg num";
    private String invalidPath = "path invalid";
    private int fileListSize = 3;
    private int wordsCountInFile = 145;

    @Before
    public void beforeTest()
    {
        func = new BusinessLogic();
    }

    @Test
    public void inputValidationTest1()
    {
        String[] arr = {"10"};
        String inputValidation = func.inputValidation(arr);
        assertEquals(wrongNumberOfArgs, inputValidation);
    }

    @Test
    public void inputValidationTest2()
    {
        String[] arr = {"a", desktop + "/Parallel Execution.txt", desktop + "/test"};
        String inputValidation = func.inputValidation(arr);
        assertEquals(secondArgIsNotNumber, inputValidation);
    }

    @Test
    public void inputValidationTest3()
    {
        String[] arr = {"10", desktop + "/Parallel Execution", desktop + "/tests"};
        String inputValidation = func.inputValidation(arr);
        assertEquals(invalidPath, inputValidation);
    }

    @Test
    public void inputValidationTest4()
    {
        String[] arr = {"10", desktop + "/Parallel Execution.txt", desktop + "/test"};
        String inputValidation = func.inputValidation(arr);
        assertEquals(null, inputValidation);
    }

    @Test
    public void getFilesTest()
    {
        String[] arr = {"10", desktop + "/Parallel Execution.txt", desktop + "/test"};
        ArrayList<File> fileList = func.getFiles(arr);
        assertEquals(fileListSize, fileList.size());
    }

    @Test
    public void findWordsTest() throws FileNotFoundException
    {
        File file = new File(desktop + "/Parallel Execution.txt");
        func.findWords(file);
        int sizeOfDictionaty = func.sizeOfDictionary();

        assertEquals(wordsCountInFile, sizeOfDictionaty);
    }

    @Test
    public void sortTest() throws FileNotFoundException
    {
        findWordsTest();
        ArrayList<IWord> listOfWords = func.sort();

        int temp =listOfWords.get(0).getValue() + 1;
        for(IWord entry : listOfWords )
        {
            assertTrue (entry.getValue() <= temp);
            temp = entry.getValue();
        }
    }

}
