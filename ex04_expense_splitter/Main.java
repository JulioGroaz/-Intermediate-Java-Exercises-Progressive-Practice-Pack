package ex04_expense_splitter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Scanner;

public class Main {
  static class Transfer {
    String from;
    String to;
    double amount;
  }

  static class PersonBalance {
    String name;
    double balance;
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
    int e = Integer.parseInt(firstLine.trim());
    Map<String, Double> balances = new HashMap<>();
    for (int i = 0; i < e; i++) {
      if (!sc.hasNextLine()) {
        return;
      }
      String line = sc.nextLine();
      applyExpense(balances, line);
    }

    List<String> names = new ArrayList<>(balances.keySet());
    names.sort(String::compareTo);
    System.out.println("BALANCES");
    for (String name : names) {
      System.out.printf(Locale.US, "%s %.2f%n", name, balances.get(name));
    }

    List<Transfer> transfers = settle(balances);
    System.out.println("TRANSFERS");
    if (transfers.isEmpty()) {
      System.out.println("NONE");
    } else {
      for (Transfer t : transfers) {
        System.out.printf(Locale.US, "%s -> %s %.2f%n", t.from, t.to, t.amount);
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
      System.err.println("Exercise 04 - Expense Splitter");
      System.err.println("Goal: split expenses and compute transfers to settle.");
      System.err.println("Input:");
      System.err.println("1) Line 1: integer E (number of expenses)");
      System.err.println("2) Next E lines: payer;amount;participants");
      System.err.println("   Participants separated by comma, e.g. Alice,Bob,Carla");
      System.err.println("Computing balances and transfers.");
    } else {
      System.err.println("Esercizio 04 - Expense Splitter");
      System.err.println("Obiettivo: dividere le spese e calcolare i trasferimenti.");
      System.err.println("Input:");
      System.err.println("1) Riga 1: intero E (numero spese)");
      System.err.println("2) Prossime E righe: pagante;importo;partecipanti");
      System.err.println("   Partecipanti separati da virgola, es. Alice,Bob,Carla");
      System.err.println("Sto calcolando saldi e trasferimenti.");
    }
  }

  static void applyExpense(Map<String, Double> balances, String line) {
    String[] parts = line.split(";");
    String payer = parts[0].trim();
    double amount = Double.parseDouble(parts[1].trim());
    String[] participants = parts[2].split(",");
    int count = participants.length;
    double share = amount / count;

    balances.put(payer, balances.getOrDefault(payer, 0.0) + amount);
    for (String p : participants) {
      String name = p.trim();
      balances.put(name, balances.getOrDefault(name, 0.0) - share);
    }
  }

  static List<Transfer> settle(Map<String, Double> balances) {
    double eps = 0.000001;
    List<PersonBalance> creditors = new ArrayList<>();
    List<PersonBalance> debtors = new ArrayList<>();
    for (Map.Entry<String, Double> entry : balances.entrySet()) {
      double bal = entry.getValue();
      if (bal > eps) {
        PersonBalance pb = new PersonBalance();
        pb.name = entry.getKey();
        pb.balance = bal;
        creditors.add(pb);
      } else if (bal < -eps) {
        PersonBalance pb = new PersonBalance();
        pb.name = entry.getKey();
        pb.balance = bal;
        debtors.add(pb);
      }
    }

    creditors.sort((a, b) -> {
      int cmp = Double.compare(b.balance, a.balance);
      if (cmp != 0) {
        return cmp;
      }
      return a.name.compareTo(b.name);
    });
    debtors.sort((a, b) -> {
      int cmp = Double.compare(a.balance, b.balance);
      if (cmp != 0) {
        return cmp;
      }
      return a.name.compareTo(b.name);
    });

    List<Transfer> transfers = new ArrayList<>();
    int i = 0;
    int j = 0;
    while (i < creditors.size() && j < debtors.size()) {
      PersonBalance c = creditors.get(i);
      PersonBalance d = debtors.get(j);
      double amount = Math.min(c.balance, -d.balance);
      if (amount > eps) {
        Transfer t = new Transfer();
        t.from = d.name;
        t.to = c.name;
        t.amount = amount;
        transfers.add(t);
      }
      c.balance -= amount;
      d.balance += amount;
      if (c.balance <= eps) {
        i++;
      }
      if (d.balance >= -eps) {
        j++;
      }
    }
    return transfers;
  }
}
