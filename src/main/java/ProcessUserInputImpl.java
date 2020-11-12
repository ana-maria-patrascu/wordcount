import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;

public class ProcessUserInputImpl implements ProcessUserInput {

    private static final String DELIMETER = "\\s+";
    private static final String MATCH_PATERN =  "[a-zA-Z]+";

    public List<String> processUserInput(String input) {
        List<String> wordList = new ArrayList<>();
        List<String> validWordList = new ArrayList<>();
        StopWordDictionaryImpl stopWordDictionary = new StopWordDictionaryImpl(new LoadStopWordInputImpl());
        List<String> stopWordDictionaryList = stopWordDictionary.getStopWordDictionary();
        if (input != null) {
            String[] words = input.split(DELIMETER);
            Collections.addAll(wordList, words);
            for (String word: wordList) {
                if(!word.isEmpty() && word.matches(MATCH_PATERN)){
                 validWordList.add(word);
                }
            }
        }
        return validWordList;
    }
}
