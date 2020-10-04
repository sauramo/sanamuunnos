package sauramo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class App {

  public String muunna(final String stringToTranspose) {
    
    String[] wordsIncludingTrailingWhitespace = stringToTranspose.split("\\s(?=\\S)");

    if (wordsIncludingTrailingWhitespace.length < 2) { // trivial case of one word
      return stringToTranspose;
    }

    String oddword = "", finalResult = "", rearrangedWordPairs = "";

    if (wordsIncludingTrailingWhitespace.length % 2 != 0) { // check if words have odd or even count and store last value for end result
      oddword = wordsIncludingTrailingWhitespace[wordsIncludingTrailingWhitespace.length - 1]; // after removing it from processing
      wordsIncludingTrailingWhitespace = Arrays.copyOf(wordsIncludingTrailingWhitespace, wordsIncludingTrailingWhitespace.length - 1);
    }

    final List<String> result = new ArrayList<String>();
    
    for (final String ss : wordsIncludingTrailingWhitespace) {
      final Pattern pattern = Pattern.compile("[aeiouyäåö]", Pattern.CASE_INSENSITIVE);
      final Matcher matchFirstVowel = pattern.matcher(ss);

      while (matchFirstVowel.find()) {
        final String lettersUntilFirstVowel = ss.substring(0, matchFirstVowel.end());
        final String lettersAfterFirstVowel = ss.substring(matchFirstVowel.end(), ss.length());
        Matcher matchConsecutiveVowels = pattern.matcher(lettersAfterFirstVowel);
        if (lettersAfterFirstVowel.length() > 0) {
          matchConsecutiveVowels = pattern.matcher(lettersAfterFirstVowel.substring(0, 1));
        }

        if (!matchConsecutiveVowels.find()) {
          result.add(lettersUntilFirstVowel);
          result.add(lettersAfterFirstVowel);
          break;
        }
      }
    }

    Collection<List<String>> partitionedList = partitionToSize(result, 4);

    int count = 0;
    for (final List<String> groupOfFours : partitionedList) {
      String rearrangedParts = groupOfFours.get(2) + groupOfFours.get(1) + " " +groupOfFours.get(0) + groupOfFours.get(3) + " ";
      rearrangedWordPairs += rearrangedParts;
      count++;
      if (count == partitionedList.size()) { // add to constructed string at last iteration only
        finalResult = (rearrangedWordPairs + oddword).trim();
      }
    }
    return finalResult;
  }

  static <T> Collection<List<T>> partitionToSize(List<T> inputList, int size) {
    final AtomicInteger counter = new AtomicInteger(0);
    return inputList.stream().collect(Collectors.groupingBy(s -> counter.getAndIncrement() / size)).values();
  }
}