package com.erste.wordcount.service.count;

import com.erste.wordcount.service.read.ReadService;
import com.erste.wordcount.service.write.WriteService;
import com.erste.wordcount.service.write.WriteServiceToDisplay;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CountServiceFromKeyboardToDisplayTest {

  private ReadService readService;
  private WriteService writeService;
  private CountService countService;



  @Test
  void count_when_givenSampleWithOneSpaceBetweenWords_returnCorrectNumber() {
    mockDependencies("here is a sample String");
    writeService = new WriteServiceToDisplay();
    countService = new CountServiceFromKeyboardToDisplay(readService, writeService);
    Long numberOfTheWords = countService.count();
    Assertions.assertEquals(numberOfTheWords, 5);
  }

  @Test
  void count_when_givenSampleStringWithMoreThanOneSpace_returnCorrectNumber() {
    mockDependencies("here is a sample       String");
    writeService = new WriteServiceToDisplay();
    countService = new CountServiceFromKeyboardToDisplay(readService, writeService);
    Long numberOfTheWords = countService.count();
    Assertions.assertEquals(numberOfTheWords, 5);
  }

  @Test
  void count_when_givenSampleStringHasNotAllowedChars_returnCorrectNumber() {
    mockDependencies("here ! ? is && a sampleString");
    writeService = new WriteServiceToDisplay();
    countService = new CountServiceFromKeyboardToDisplay(readService, writeService);
    Long numberOfTheWords = countService.count();
    Assertions.assertEquals(4, numberOfTheWords);
  }

 @Test
  public void filterNotAllowedWords_when_giveStringWithNotAllowedChars_returnFilteredArray() {
    CountService countService = mockDependencies("just to instantiate");
    String[] sampleArray = {"correctString","!!!"," stop!","AnotherCorrectString"};
    List<String> strings = countService.filterNotAllowedWords(sampleArray);
    Assertions.assertEquals(2,strings.size());
  }

  private CountService mockDependencies(String sampleString) {

    readService = new ReadService() {
      @Override
      public String read() {
        return sampleString;
      }
    };

    return new CountServiceFromKeyboardToDisplay(readService, new WriteServiceToDisplay());
  }
}
