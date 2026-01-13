package ex05_meeting_planner;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    if (!sc.hasNextLine()) {
      return;
    }
    String[] workParts = sc.nextLine().trim().split(";");
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

  static int parseTime(String hhmm) {
    throw new UnsupportedOperationException("TODO");
  }

  static String formatTime(int minutes) {
    throw new UnsupportedOperationException("TODO");
  }

  static List<int[]> clampToWorkHours(List<int[]> meetings, int workStart, int workEnd) {
    throw new UnsupportedOperationException("TODO");
  }

  static List<int[]> mergeMeetings(List<int[]> meetings) {
    throw new UnsupportedOperationException("TODO");
  }

  static List<int[]> findFreeSlots(int workStart, int workEnd, List<int[]> merged, int minDuration) {
    throw new UnsupportedOperationException("TODO");
  }
}
