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

  public String transform(String input) {
    
    String[] words = input.split("\\s(?=\\S)");

    if (words.length < 2) { // trivial case of one word
      return input;
    }

    String oddword = "", finalResult = "", swappedWordPairs = "";

    if (words.length % 2 != 0) {          // Store last word for end result
      oddword = words[words.length - 1];  // if count of words is not even
      words = Arrays.copyOf(words, words.length - 1); // and remove it from processing
    }

    List<String> listOfSplittedWords = new ArrayList<String>();
    
    for (String wordToProcess : words) {
      Pattern pattern = Pattern.compile("^(?:[^aeiouyäåö]*([aeiouyäåö]+))", Pattern.CASE_INSENSITIVE);
      Matcher matchLastOfConsecutiveVowels = pattern.matcher(wordToProcess);

      while (matchLastOfConsecutiveVowels.find()) {
        String lettersUntilLastConsecutiveVowel = wordToProcess.substring(0, matchLastOfConsecutiveVowels.end());
        String lettersAfterLastConsecutiveVowel = wordToProcess.substring(matchLastOfConsecutiveVowels.end(), wordToProcess.length());
        listOfSplittedWords.add(lettersUntilLastConsecutiveVowel);
        listOfSplittedWords.add(lettersAfterLastConsecutiveVowel);
      }
    }

    Collection<List<String>> listOfWordPairs = partitionToSize(listOfSplittedWords, 4);
    
    for (List<String> wordPairToSwap : listOfWordPairs) {
      String swappedWordPair = wordPairToSwap.get(2) + wordPairToSwap.get(1) + " " +
                               wordPairToSwap.get(0) + wordPairToSwap.get(3) + " ";
      swappedWordPairs += swappedWordPair;      
    }
    finalResult = (swappedWordPairs + oddword).trim();
    return finalResult;
  }

  static <T> Collection<List<T>> partitionToSize(List<T> inputList, int size) {
    final AtomicInteger counter = new AtomicInteger(0);
    return inputList.stream().collect(Collectors.groupingBy(s -> counter.getAndIncrement() / size)).values();
  }
}