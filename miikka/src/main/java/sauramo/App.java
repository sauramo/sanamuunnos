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
    String[] arr = stringToTranspose.split(" ");

    if (arr.length < 2) { // trivial case of one word
      return stringToTranspose;
    }

    String oddword = "";

    if (arr.length % 2 != 0) { // check if words have odd or even count and store last value for end result
      oddword = arr[arr.length - 1]; // after removing it from processing
      arr = Arrays.copyOf(arr, arr.length - 1);
    }

    final List<String> result = new ArrayList<String>();
    
    for (final String ss : arr) {
      final Pattern pattern = Pattern.compile("[aeiouyäåö]", Pattern.CASE_INSENSITIVE);
      final Matcher matcher = pattern.matcher(ss);

      while (matcher.find()) {
        final String first = ss.substring(0, matcher.end());
        final String last = ss.substring(matcher.end(), ss.length());
        final Matcher matcher2;
        if (last.length() > 0) {
          matcher2 = pattern.matcher(last.substring(0, 1));
        } else {
          matcher2 = pattern.matcher(last);
        }

        if (!matcher2.find()) {
          result.add(first);
          result.add(last);
          break;
        }
      }
    }

    Collection<List<String>> partitionedList = partitionToSize(result, 4);

    String finalResult = "";
    String rearrangedWordPairs = "";
    int count = 0;
    for (List<String> groupOfFours : partitionedList) {

      final String[] stringArray = groupOfFours.stream().toArray(String[]::new);
      String rearrangedParts = stringArray[2] + stringArray[1] + " " + stringArray[0] + stringArray[3] + " ";
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