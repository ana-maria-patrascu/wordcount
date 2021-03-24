package com.erste.wordcount.service.count;

import com.erste.wordcount.exception.AllowedPatternNullException;
import com.erste.wordcount.exception.splitterNullException;
import com.erste.wordcount.service.read.ReadService;
import com.erste.wordcount.service.write.WriteService;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.stream.Collectors;

public class CountServiceFromKeyboardToDisplay implements CountService {


  private ReadService readServiceFromKeyboard;
  private WriteService writeServiceToDisplay;
  private String allowedRegexPattern = "[a-zA-Z]";
  private String splitterRegexPattern = "\\s+";


  public CountServiceFromKeyboardToDisplay(ReadService readService,
      WriteService writeService) {
    this.readServiceFromKeyboard = readService;
    this.writeServiceToDisplay = writeService;
  }

  @Override
  public long count() throws IOException {
    if (allowedRegexPattern == null || allowedRegexPattern.isEmpty()) {
      throw new AllowedPatternNullException();
    }
    if (splitterRegexPattern == null || splitterRegexPattern.isEmpty()) {
      throw new splitterNullException();
    }
    writeWelcomeMessage();
    String[] inputStringArray = ReadService.convertInputStreamTOString(readServiceFromKeyboard.read())
        .split(splitterRegexPattern);
    return filterNotAllowedWords(inputStringArray).size();
  }

  @Override
  public void setSplitter(String splitterPattern) {
    splitterRegexPattern = splitterPattern;
  }

  @Override
  public void setAllowedPattern(String allowedPattern) {
    allowedRegexPattern = allowedPattern;
  }
}
