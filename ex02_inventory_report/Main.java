package ex02_inventory_report;

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
    if (!sc.hasNextLine()) {
      return;
    }
    int m = Integer.parseInt(sc.nextLine().trim());
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

  static void applyMovement(Map<String, Item> items, String line) {
    throw new UnsupportedOperationException("TODO");
  }

  static List<Item> sortByValue(Map<String, Item> items) {
    throw new UnsupportedOperationException("TODO");
  }

  static List<Item> lowStock(Map<String, Item> items, int threshold) {
    throw new UnsupportedOperationException("TODO");
  }
}
