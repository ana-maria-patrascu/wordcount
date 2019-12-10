package wordcount;

import org.junit.Test;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static org.junit.Assert.assertEquals;

public class ValidWordTokenizerTest {

    private void testTokenizer(final String input, final List<String> tokens) {
        final ValidWordTokenizer tokenizer = new ValidWordTokenizer(new StringReader(input));
        final List<String> result = new ArrayList<>();
        Optional<String> tokenOpt;
        while ((tokenOpt = tokenizer.nextToken()).isPresent()) {
            result.add(tokenOpt.get());
        }
        assertEquals(tokens, result);
    }

    @Test
    public void empty() {
        testTokenizer("", emptyList());
    }

    @Test
    public void singleValidWord() {
        testTokenizer("FooBar", asList("FooBar"));
    }

    @Test
    public void blockOfCharsWithoutWordChars() {
        testTokenizer("  @!#@#$923874897 ", emptyList());
    }

    @Test
    public void blockWithBothWordAndNonWordChars() {
        testTokenizer("Foo$Bar", emptyList());
    }

    @Test
    public void multipleWords() {
        testTokenizer("Quick brown Fox", asList("Quick", "brown", "Fox"));
        testTokenizer("Quick  brown Fox", asList("Quick", "brown", "Fox"));
        testTokenizer(" Quick brown Fox", asList("Quick", "brown", "Fox"));
        testTokenizer("Quick brown Fox ", asList("Quick", "brown", "Fox"));
    }

    @Test
    public void multipleWordsAndNonWords() {
        testTokenizer("$Quick brown Fox", asList("brown", "Fox"));
        testTokenizer("Quick brown Fox$", asList("Quick", "brown"));
        testTokenizer("Quick $brown Fox", asList("Quick", "Fox"));
        testTokenizer("Quick br$own Fox", asList("Quick", "Fox"));
        testTokenizer("Quick brown$ Fox", asList("Quick", "Fox"));
        testTokenizer("$ Quick brown Fox", asList("Quick", "brown", "Fox"));
        testTokenizer("Quick brown Fox $", asList("Quick", "brown", "Fox"));
    }

}