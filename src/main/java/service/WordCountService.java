package service;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

public class WordCountService {

    public int countWords(String text, List<String> stopWords){
        return (int)Arrays.stream(text.split("[\\s.]+")).filter(t->
                t.matches("[a-zA-Z-]+"))
                .filter(t -> !stopWords.contains(t))
                .count();
    }

    public int countUniqueWords(String text, List<String> stopWords){
        return (int)Arrays.stream(text.split("[\\s.]+")).filter(t->
                t.matches("[a-zA-Z-]+"))
                .filter(t -> !stopWords.contains(t))
                .distinct()
                .count();
    }

    public double averageWordsLength(String text, List<String> stopWords){
        int numberOfWords = countWords(text,stopWords);
        if(numberOfWords == 0)
            return 0;

        double length = Arrays.stream(text.split("[\\s.]+")).filter(t->
                t.matches("[a-zA-Z-]+"))
                .filter(t -> !stopWords.contains(t))
                .map(String::length).reduce(0, (a,b) -> a+b).doubleValue();

        return length / numberOfWords;
    }


}
