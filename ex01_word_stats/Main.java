package ex01_word_stats;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    LanguageInput input = readLanguage(sc);
    printInstructions(input.lang);
    String firstLine = input.firstLine;
    if (firstLine == null) {
      if (!sc.hasNextLine()) {
        return;
      }
      firstLine = sc.nextLine();
    }
    int topN = Integer.parseInt(firstLine.trim());
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

  static class LanguageInput {
    String lang;
    String firstLine;
  }

  static LanguageInput readLanguage(Scanner sc) {
    System.err.println("Seleziona lingua / Select language: IT o EN");
    LanguageInput input = new LanguageInput();
    input.lang = "IT";
    if (!sc.hasNextLine()) {
      return input;
    }
    String line = sc.nextLine().trim();
    if (line.isEmpty()) {
      return input;
    }
    String upper = line.toUpperCase();
    String shortCode = upper.length() > 2 ? upper.substring(0, 2) : upper;
    // Match IT/EN on the first two chars; otherwise infer by the presence of I/T or E/N.
    if (shortCode.equals("EN")) {
      input.lang = "EN";
      return input;
    }
    if (shortCode.equals("IT")) {
      input.lang = "IT";
      return input;
    }
    if (upper.contains("I") || upper.contains("T")) {
      input.lang = "IT";
      return input;
    }
    if (upper.contains("E") || upper.contains("N")) {
      input.lang = "EN";
      return input;
    }
    input.firstLine = line;
    return input;
  }

  static void printInstructions(String lang) {
    if ("EN".equals(lang)) {
      System.err.println("Exercise 01 - Word Stats");
      System.err.println("Goal: normalize a text and print the top N most frequent words.");
      System.err.println("Input:");
      System.err.println("1) Line 1: integer N (how many words to print)");
      System.err.println("2) Next lines: text");
      System.err.println("3) Line END to finish");
      System.err.println("Reading input and computing the most frequent words.");
    } else {
      System.err.println("Esercizio 01 - Word Stats");
      System.err.println("Obiettivo: normalizzare un testo e stampare le N parole piu frequenti.");
      System.err.println("Input:");
      System.err.println("1) Riga 1: intero N (quante parole stampare)");
      System.err.println("2) Righe successive: testo");
      System.err.println("3) Riga END per terminare");
      System.err.println("Sto leggendo l input e calcolo le parole piu frequenti.");
    }
  }

  static Map<String, Integer> countWords(String text) {
    Map<String, Integer> freq = new HashMap<>();
    StringBuilder token = new StringBuilder();
    for (int i = 0; i < text.length(); i++) {
      char c = text.charAt(i);
      if (Character.isLetterOrDigit(c)) {
        token.append(Character.toLowerCase(c));
      } else if (token.length() > 0) {
        String word = token.toString();
        freq.put(word, freq.getOrDefault(word, 0) + 1);
        token.setLength(0);
      }
    }
    if (token.length() > 0) {
      String word = token.toString();
      freq.put(word, freq.getOrDefault(word, 0) + 1);
    }
    return freq;
  }

  static List<Map.Entry<String, Integer>> topN(Map<String, Integer> freq, int n) {
    List<Map.Entry<String, Integer>> entries = new ArrayList<>(freq.entrySet());
    if (n <= 0) {
      return new ArrayList<>();
    }
    entries.sort((a, b) -> {
      int cmp = Integer.compare(b.getValue(), a.getValue());
      if (cmp != 0) {
        return cmp;
      }
      return a.getKey().compareTo(b.getKey());
    });
    if (entries.size() > n) {
      return new ArrayList<>(entries.subList(0, n));
    }
    return entries;
  }
}
