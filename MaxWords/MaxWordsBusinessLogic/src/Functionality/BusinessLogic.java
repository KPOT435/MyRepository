package Functionality;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class BusinessLogic implements IBusinessLogic
{
    private IWordsDictionary wordsDictionary;

    public BusinessLogic()
    {
        wordsDictionary = WordsDictionary.getInstance();
    }

    /**
     * validate that files and folders do excites
     * the method receives the commandline arguments and
     * go thw all of them
     *
     * @param commandlineArgs
     * @return
     */
    public String inputValidation(String[] commandlineArgs)
    {
        if (commandlineArgs.length < 2)
        {
            System.out.println("Wrong number of commandline arguments");
            printSyntax();
            return "wrong num agrs";
        }

        if (!isNumeric(commandlineArgs[0]))
        {
            System.out.println("Second argument should by numeric value");
            printSyntax();
            return "sec arg num";
        }

        String pathValidation = null;

        for (int i = 1; commandlineArgs.length > i; i++)
        {
            File path = new File(commandlineArgs[i]);
            pathValidation = pathValidation(path);
        }
        return pathValidation;
    }

    private String pathValidation(File path)
    {
        if (!path.isFile() && !path.isDirectory())
        {
            System.out.println("The path '" + path + "' is invalid");
            printSyntax();
            return ("path invalid");
        }
        return null;
    }

    /**
     * collecting all relevant files to a list
     * in order to iterate then later
     *
     * @param commandlineArgs
     * @return
     */
    @Override
    public ArrayList<File> getFiles(String[] commandlineArgs)
    {
        ArrayList<File> pathList = new ArrayList<>();

        for (int i = 1; commandlineArgs.length > i; i++)
        {
            File path = new File(commandlineArgs[i]);

            if (path.isFile() && path.getName().contains(".txt"))
            {
                pathList.add(path);
                continue;
            }

            findFilesInFolder(path.toString(), pathList);
        }
        return pathList;
    }

    /**
     * recursively go through all nested folders
     *
     * @param directoryName
     * @param files
     */
    private void findFilesInFolder(String directoryName, ArrayList<File> files)
    {
        File directory = new File(directoryName);

        File[] fileList = directory.listFiles();
        Arrays.stream(fileList).forEach(file ->
        {
            if (file.isFile() && file.getName().contains(".txt"))
            {
                files.add(file);
            }
            else if (file.isDirectory())
            {
                findFilesInFolder(file.getAbsolutePath(), files);
            }
        });
    }

    /**
     * iterate through file and get words one by one
     * only words starting with a setters
     * and add the to a dictionary
     *
     * @param file
     * @throws FileNotFoundException
     */
    @Override
    public void findWords(File file) throws FileNotFoundException
    {
        Scanner scanner = new Scanner(file);
        scanner.useDelimiter("[^A-Za-z]+");

        while (scanner.hasNext())
        {
            String text = scanner.next().toLowerCase();

            if (!wordsDictionary.containsKey(text))
            {
                wordsDictionary.addNewWord(text);
            }
            else
            {
                wordsDictionary.addWord(text);
            }
        }
    }

    /**
     * returns the size of the dictionary
     * for testing purposes
     *
     * @return
     */
    public int sizeOfDictionary()
    {
        return wordsDictionary.size();
    }

    /**
     * printing the list of words by the number according to user request
     *
     * @param occurrencesNumber
     * @param sortedList
     */
    @Override
    public void print(int occurrencesNumber, ArrayList<IWord> sortedList)
    {
        System.out.println("The top " + occurrencesNumber + " words are:");
        int counter = 0;
        for (IWord entry : sortedList)
        {
            counter++;
            if (counter > occurrencesNumber)
            {
                System.out.println("\nThank you for counting words with us");
                System.exit(0);
            }
            System.out.println(counter + ". " + entry.toString());
        }
    }

    /**
     * moving the dictionary to a list
     * in order to sort it by value
     *
     * @return
     */
    public ArrayList<IWord> sort()
    {
        ArrayList<IWord> arrayList = new ArrayList<IWord>();
        wordsDictionary.getWords().forEach((key, value) -> arrayList.add(new Word(key, value)));
        Collections.sort(arrayList);

        return arrayList;
    }

    private boolean isNumeric(String str)
    {
        try
        {
            double d = Double.parseDouble(str);
        }
        catch (NumberFormatException nfe)
        {
            return false;
        }
        return true;
    }

    private void printSyntax()
    {
        System.out.println("\nSyntax command line: ~ java -jar MaxWords.jar <number> <filePath/folderPAth> <filePath/folderPAth>");
    }
}
