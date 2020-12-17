package wordcount;

import org.junit.Test;
import wordcount.counters.StopWordCounter;
import wordcount.counters.WordCounter;
import wordcount.readers.StopWordReader;
import wordcount.readers.WordReader;

import java.io.*;
import java.util.Arrays;

public class CountAppTest {
    @Test
    public void testWordCount() {
        InputStream inputStream = new ByteArrayInputStream("Mary had a little lamb".getBytes());
        CountApp countApp = new CountApp(new WordReader(inputStream), new WordCounter());

        int count = countApp.count();

        assert count == 5;
    }

    @Test
    public void testWordCountWithStopWords() {
        InputStream inputStream = new ByteArrayInputStream("Mary had a little lamb".getBytes());
        CountApp countApp = new CountApp(new WordReader(inputStream), new StopWordCounter(Arrays.asList("a", "on", "off", "the")));

        int count = countApp.count();

        assert count == 4;
    }

    @Test
    public void testWordCountWithStopWordsFromFile() throws FileNotFoundException {
        CountApp countApp = new CountApp(
                new WordReader(new FileInputStream(new File("src\\test\\resources\\input.txt"))),
                new StopWordCounter(new StopWordReader("stopwords.txt"))
        );

        int count = countApp.count();

        assert count == 4;
    }
}
