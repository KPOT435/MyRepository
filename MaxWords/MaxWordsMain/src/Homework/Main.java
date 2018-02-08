package Homework;


import Functionality.BusinessLogic;
import Functionality.IBusinessLogic;
import Functionality.IWord;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class Main
{
    /**
     *finding the word count for all given files
     *
     * @param args
     * example parameters:
     *       <number> <filePath/folderPAth> <filePath/folderPAth>
     */
    public static void main(String[] args)
    {
        try
        {
            IBusinessLogic func = new BusinessLogic();

            if (func.inputValidation(args) != null)
            {
                System.exit(1);
            }

            List<File> fileList = func.getFiles(args);

            fileList.parallelStream().forEach(file ->
            {
                try
                {
                    func.findWords(file);
                }
                catch (FileNotFoundException e)
                {
                    e.printStackTrace();
                }
            });

            ArrayList<IWord> sortedList = func.sort();
            int occurrencesNumber = Integer.parseInt(args[0]);
            func.print(occurrencesNumber, sortedList);
        }
        catch (NumberFormatException e)
        {
            e.printStackTrace();
        }
    }
}
