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

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    if (!sc.hasNextLine()) {
      return;
    }
    int e = Integer.parseInt(sc.nextLine().trim());
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

  static void applyExpense(Map<String, Double> balances, String line) {
    throw new UnsupportedOperationException("TODO");
  }

  static List<Transfer> settle(Map<String, Double> balances) {
    throw new UnsupportedOperationException("TODO");
  }
}
