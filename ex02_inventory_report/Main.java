package ex02_inventory_report;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Scanner;

public class Main {
  static class Item {
    String sku;
    String name;
    int qty;
    double unitPrice;

    double totalValue() {
      return qty * unitPrice;
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
    int m = Integer.parseInt(firstLine.trim());
    Map<String, Item> items = new HashMap<>();
    for (int i = 0; i < m; i++) {
      if (!sc.hasNextLine()) {
        return;
      }
      String line = sc.nextLine();
      applyMovement(items, line);
    }
    if (!sc.hasNextLine()) {
      return;
    }
    int lowThreshold = Integer.parseInt(sc.nextLine().trim());

    List<Item> report = sortByValue(items);
    for (Item item : report) {
      System.out.printf(Locale.US, "%s %s %d %.2f%n", item.sku, item.name, item.qty, item.totalValue());
    }

    System.out.println("LOW STOCK");
    List<Item> low = lowStock(items, lowThreshold);
    if (low.isEmpty()) {
      System.out.println("NONE");
    } else {
      for (Item item : low) {
        System.out.println(item.sku + " " + item.name + " " + item.qty);
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
      System.err.println("Exercise 02 - Inventory Report");
      System.err.println("Goal: update quantities and prices, then print report and low stock list.");
      System.err.println("Input:");
      System.err.println("1) Line 1: integer M (number of movements)");
      System.err.println("2) Next M lines: sku;name;qty;unitPrice");
      System.err.println("3) Last line: integer lowThreshold");
      System.err.println("Computing inventory report and low stock list.");
      System.err.println("Example:");
    } else {
      System.err.println("Esercizio 02 - Inventory Report");
      System.err.println("Obiettivo: aggiornare quantita e prezzi e stampare report e scorte basse.");
      System.err.println("Input:");
      System.err.println("1) Riga 1: intero M (numero di movimenti)");
      System.err.println("2) Prossime M righe: sku;nome;qty;prezzoUnitario");
      System.err.println("3) Ultima riga: intero lowThreshold");
      System.err.println("Sto calcolando report inventario e scorte basse.");
      System.err.println("Esempio:");
    }
    System.err.println("4");
    System.err.println("A12;Mouse;10;15.50");
    System.err.println("B77;Keyboard;5;30.00");
    System.err.println("A12;Mouse;-3;15.50");
    System.err.println("C90;Monitor;2;120.00");
    System.err.println("5");
  }

  static void applyMovement(Map<String, Item> items, String line) {
    String[] parts = line.split(";");
    String sku = parts[0].trim();
    String name = parts[1].trim();
    int qty = Integer.parseInt(parts[2].trim());
    double unitPrice = Double.parseDouble(parts[3].trim());
    Item item = items.get(sku);
    if (item == null) {
      item = new Item();
      item.sku = sku;
      items.put(sku, item);
    }
    item.name = name;
    item.qty += qty;
    item.unitPrice = unitPrice;
  }

  static List<Item> sortByValue(Map<String, Item> items) {
    List<Item> list = new ArrayList<>(items.values());
    list.sort((a, b) -> {
      int cmp = Double.compare(b.totalValue(), a.totalValue());
      if (cmp != 0) {
        return cmp;
      }
      return a.sku.compareTo(b.sku);
    });
    return list;
  }

  static List<Item> lowStock(Map<String, Item> items, int threshold) {
    List<Item> list = new ArrayList<>();
    for (Item item : items.values()) {
      if (item.qty <= threshold) {
        list.add(item);
      }
    }
    list.sort((a, b) -> {
      int cmp = Integer.compare(a.qty, b.qty);
      if (cmp != 0) {
        return cmp;
      }
      return a.sku.compareTo(b.sku);
    });
    return list;
  }
}
