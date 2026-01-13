package ex03_scoreboard;

import java.util.ArrayList;
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
    LanguageInput input = readLanguage(sc);
    printInstructions(input.lang);
    String firstLine = input.firstLine;
    if (firstLine == null) {
      if (!sc.hasNextLine()) {
        return;
      }
      firstLine = sc.nextLine();
    }
    int g = Integer.parseInt(firstLine.trim());
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
      System.err.println("Exercise 03 - Scoreboard");
      System.err.println("Goal: compute the ranking from results and tie-breakers.");
      System.err.println("Input:");
      System.err.println("1) Line 1: integer G (number of games)");
      System.err.println("2) Next G lines: teamA;teamB;scoreA;scoreB");
      System.err.println("Computing ranking with points and goal difference.");
    } else {
      System.err.println("Esercizio 03 - Scoreboard");
      System.err.println("Obiettivo: calcolare la classifica da risultati e spareggi.");
      System.err.println("Input:");
      System.err.println("1) Riga 1: intero G (numero partite)");
      System.err.println("2) Prossime G righe: squadraA;squadraB;scoreA;scoreB");
      System.err.println("Sto calcolando la classifica con punti e differenza reti.");
    }
  }

  static void applyGame(Map<String, TeamStats> stats, String line) {
    String[] parts = line.split(";");
    String teamA = parts[0].trim();
    String teamB = parts[1].trim();
    int scoreA = Integer.parseInt(parts[2].trim());
    int scoreB = Integer.parseInt(parts[3].trim());

    TeamStats a = stats.get(teamA);
    if (a == null) {
      a = new TeamStats();
      a.name = teamA;
      stats.put(teamA, a);
    }
    TeamStats b = stats.get(teamB);
    if (b == null) {
      b = new TeamStats();
      b.name = teamB;
      stats.put(teamB, b);
    }

    a.goalsFor += scoreA;
    a.goalsAgainst += scoreB;
    b.goalsFor += scoreB;
    b.goalsAgainst += scoreA;

    if (scoreA > scoreB) {
      a.points += 3;
    } else if (scoreA < scoreB) {
      b.points += 3;
    } else {
      a.points += 1;
      b.points += 1;
    }
  }

  static List<TeamStats> rankTeams(Map<String, TeamStats> stats) {
    List<TeamStats> list = new ArrayList<>(stats.values());
    list.sort((a, b) -> {
      int cmp = Integer.compare(b.points, a.points);
      if (cmp != 0) {
        return cmp;
      }
      cmp = Integer.compare(b.goalDiff(), a.goalDiff());
      if (cmp != 0) {
        return cmp;
      }
      cmp = Integer.compare(b.goalsFor, a.goalsFor);
      if (cmp != 0) {
        return cmp;
      }
      return a.name.compareTo(b.name);
    });
    return list;
  }
}
