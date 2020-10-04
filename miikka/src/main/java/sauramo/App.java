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

  public String muunna(String stringToTranspose) {
    
    String[] words = stringToTranspose.split("\\s(?=\\S)");

    if (words.length < 2) { // trivial case of one word
      return stringToTranspose;
    }

    String oddword = "", finalResult = "", rearrangedWordPairs = "";

    if (words.length % 2 != 0) {          // Store last word for end result
      oddword = words[words.length - 1];  // if count of words is not even
      words = Arrays.copyOf(words, words.length - 1);
    }

    List<String> result = new ArrayList<String>();
    
    for (String wordToProcess : words) {
      Pattern pattern = Pattern.compile("[aeiouyäåö]", Pattern.CASE_INSENSITIVE);
      Matcher matchFirstVowel = pattern.matcher(wordToProcess);

      while (matchFirstVowel.find()) {
        String lettersUntilFirstVowel = wordToProcess.substring(0, matchFirstVowel.end());
        String lettersAfterFirstVowel = wordToProcess.substring(matchFirstVowel.end(), wordToProcess.length());
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
    for (List<String> groupOfFours : partitionedList) {
      String rearrangedParts = groupOfFours.get(2) + groupOfFours.get(1) + " " +
                               groupOfFours.get(0) + groupOfFours.get(3) + " ";
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