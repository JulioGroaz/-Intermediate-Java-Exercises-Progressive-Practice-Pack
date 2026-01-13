import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    LanguageInput input = readLanguage(sc);
    printInstructions(input.lang);
    if ("EN".equals(input.lang)) {
      System.out.println("See README.md for the list of exercises.");
    } else {
      System.out.println("Vedi README.md per la lista degli esercizi.");
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
      System.err.println("Exercises launcher");
      System.err.println("Goal: run the Main of a specific exercise.");
      System.err.println("No input required.");
      System.err.println("Open README.md and run the Main of the exercise.");
    } else {
      System.err.println("Launcher esercizi");
      System.err.println("Obiettivo: avviare il Main del singolo esercizio.");
      System.err.println("Nessun input richiesto.");
      System.err.println("Apri README.md e avvia il Main del singolo esercizio.");
    }
  }
}
