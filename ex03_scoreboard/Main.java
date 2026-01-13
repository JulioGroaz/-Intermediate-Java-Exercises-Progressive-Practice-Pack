package ex03_scoreboard;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {
  static class TeamStats {
    String name;
    int points;
    int goalsFor;
    int goalsAgainst;

    int goalDiff() {
      return goalsFor - goalsAgainst;
    }
  }

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    if (!sc.hasNextLine()) {
      return;
    }
    int g = Integer.parseInt(sc.nextLine().trim());
    Map<String, TeamStats> stats = new HashMap<>();
    for (int i = 0; i < g; i++) {
      if (!sc.hasNextLine()) {
        return;
      }
      String line = sc.nextLine();
      applyGame(stats, line);
    }

    List<TeamStats> ranking = rankTeams(stats);
    for (TeamStats team : ranking) {
      System.out.println(team.name + " " + team.points + " " + team.goalDiff() + " " + team.goalsFor);
    }
  }

  static void applyGame(Map<String, TeamStats> stats, String line) {
    throw new UnsupportedOperationException("TODO");
  }

  static List<TeamStats> rankTeams(Map<String, TeamStats> stats) {
    throw new UnsupportedOperationException("TODO");
  }
}
