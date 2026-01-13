package ex05_meeting_planner;

import java.util.ArrayList;
import java.util.List;
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
    String[] workParts = firstLine.trim().split(";");
    int workStart = parseTime(workParts[0]);
    int workEnd = parseTime(workParts[1]);

    int m = Integer.parseInt(sc.nextLine().trim());
    List<int[]> meetings = new ArrayList<>();
    for (int i = 0; i < m; i++) {
      if (!sc.hasNextLine()) {
        return;
      }
      String line = sc.nextLine();
      String[] parts = line.trim().split(";");
      meetings.add(new int[] {parseTime(parts[0]), parseTime(parts[1])});
    }
    if (!sc.hasNextLine()) {
      return;
    }
    int minDuration = Integer.parseInt(sc.nextLine().trim());

    List<int[]> clamped = clampToWorkHours(meetings, workStart, workEnd);
    List<int[]> merged = mergeMeetings(clamped);
    List<int[]> free = findFreeSlots(workStart, workEnd, merged, minDuration);

    if (free.isEmpty()) {
      System.out.println("NONE");
    } else {
      for (int[] slot : free) {
        System.out.println(formatTime(slot[0]) + "-" + formatTime(slot[1]));
      }
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
      System.err.println("Exercise 05 - Meeting Planner");
      System.err.println("Goal: find free slots inside working hours.");
      System.err.println("Input:");
      System.err.println("1) Line 1: workStart;workEnd (HH:MM)");
      System.err.println("2) Line 2: integer M (number of meetings)");
      System.err.println("3) Next M lines: start;end (HH:MM)");
      System.err.println("4) Last line: minDuration (minutes)");
      System.err.println("Finding free slots inside working hours.");
    } else {
      System.err.println("Esercizio 05 - Meeting Planner");
      System.err.println("Obiettivo: trovare gli slot liberi dentro l orario di lavoro.");
      System.err.println("Input:");
      System.err.println("1) Riga 1: inizioLavoro;fineLavoro (HH:MM)");
      System.err.println("2) Riga 2: intero M (numero meeting)");
      System.err.println("3) Prossime M righe: inizio;fine (HH:MM)");
      System.err.println("4) Ultima riga: minDuration (minuti)");
      System.err.println("Sto cercando gli slot liberi nell orario di lavoro.");
    }
  }

  static int parseTime(String hhmm) {
    String[] parts = hhmm.trim().split(":");
    int hours = Integer.parseInt(parts[0]);
    int mins = Integer.parseInt(parts[1]);
    return hours * 60 + mins;
  }

  static String formatTime(int minutes) {
    int hours = minutes / 60;
    int mins = minutes % 60;
    String hh = hours < 10 ? "0" + hours : String.valueOf(hours);
    String mm = mins < 10 ? "0" + mins : String.valueOf(mins);
    return hh + ":" + mm;
  }

  static List<int[]> clampToWorkHours(List<int[]> meetings, int workStart, int workEnd) {
    List<int[]> clamped = new ArrayList<>();
    for (int[] meeting : meetings) {
      int start = Math.max(meeting[0], workStart);
      int end = Math.min(meeting[1], workEnd);
      if (end > start) {
        clamped.add(new int[] {start, end});
      }
    }
    return clamped;
  }

  static List<int[]> mergeMeetings(List<int[]> meetings) {
    if (meetings.isEmpty()) {
      return new ArrayList<>();
    }
    List<int[]> sorted = new ArrayList<>(meetings);
    sorted.sort((a, b) -> {
      int cmp = Integer.compare(a[0], b[0]);
      if (cmp != 0) {
        return cmp;
      }
      return Integer.compare(a[1], b[1]);
    });
    List<int[]> merged = new ArrayList<>();
    int currentStart = sorted.get(0)[0];
    int currentEnd = sorted.get(0)[1];
    for (int i = 1; i < sorted.size(); i++) {
      int[] next = sorted.get(i);
      if (next[0] <= currentEnd) {
        currentEnd = Math.max(currentEnd, next[1]);
      } else {
        merged.add(new int[] {currentStart, currentEnd});
        currentStart = next[0];
        currentEnd = next[1];
      }
    }
    merged.add(new int[] {currentStart, currentEnd});
    return merged;
  }

  static List<int[]> findFreeSlots(int workStart, int workEnd, List<int[]> merged, int minDuration) {
    List<int[]> free = new ArrayList<>();
    int current = workStart;
    for (int[] meeting : merged) {
      int start = meeting[0];
      if (start - current >= minDuration) {
        free.add(new int[] {current, start});
      }
      current = Math.max(current, meeting[1]);
    }
    if (workEnd - current >= minDuration) {
      free.add(new int[] {current, workEnd});
    }
    return free;
  }
}
