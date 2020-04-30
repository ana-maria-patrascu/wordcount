package wordreader;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class FileWordReader extends ReaderBase {

    private final String fileName;

    public FileWordReader(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public Collection<String> readWords() {

        List<String> words = new ArrayList<>();

        try {
            File myObj = new File(fileName);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                words.addAll(getWordsFromSentence(data));
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
            e.printStackTrace();
        }

        return words;
    }

}
