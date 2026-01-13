package ex01_word_stats;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    if (!sc.hasNextLine()) {
      return;
    }
    int topN = Integer.parseInt(sc.nextLine().trim());
    StringBuilder sb = new StringBuilder();
    while (sc.hasNextLine()) {
      String line = sc.nextLine();
      if (line.trim().equals("END")) {
        break;
      }
      sb.append(line).append(' ');
    }

    Map<String, Integer> freq = countWords(sb.toString());
    List<Map.Entry<String, Integer>> top = topN(freq, topN);
    for (Map.Entry<String, Integer> e : top) {
      System.out.println(e.getKey() + " " + e.getValue());
    }
  }

  static Map<String, Integer> countWords(String text) {
    throw new UnsupportedOperationException("TODO");
  }

  static List<Map.Entry<String, Integer>> topN(Map<String, Integer> freq, int n) {
    throw new UnsupportedOperationException("TODO");
  }
}
