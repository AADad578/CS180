import java.io.*;
import java.util.ArrayList;

public class Item implements ItemInterface {
    private String itemName;
    private double itemPrice;
    private String itemLocation;

    public Item(String itemName, double itemPrice, String itemLocation) {
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.itemLocation = itemLocation;
    }

    public void itemCreate(Item item) {
        try (PrintWriter pw = new PrintWriter(new
                FileOutputStream("itemlog.txt", true))) {
            pw.format("%s, $%.2f, %s\n", item.itemName, item.itemPrice, item.itemLocation);
            System.out.println("Item created and added to log.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void itemDelete(Item item) {
        ArrayList<String> lineList = new ArrayList<String>();
        try (BufferedReader bfr = new BufferedReader(new FileReader("itemlog.txt"))) {
            String itemRemove = String.format("%s, $%.2f, %s\n", item.itemName,
                    item.itemPrice, item.itemLocation);
            String line = bfr.readLine();
            int lineCount = 0;
            while (line != null) {
                if (!line.equals(itemRemove)) {
                    lineList.add(line);
                }
                line = bfr.readLine();
                lineCount++;
            }
            PrintWriter pw = new PrintWriter(new FileOutputStream("itemlog.txt"));
            for (String str : lineList) {
                pw.println(str);
            }
            if (lineCount == lineList.size()) {
                System.out.println("No item deleted!");
            } else {
                System.out.println("Item deleted from log.");
            }
            pw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String itemSearch(String itemName, String itemLocation) {
        try (BufferedReader bfr = new BufferedReader(new FileReader("itemlog.txt"))) {
            String line = bfr.readLine();
            while (line != null) {
                if (line.substring(0, itemName.length()).equals(itemName)
                        && line.contains(itemLocation)) {
                    return line;
                }
                line = bfr.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "No item found!";
    }

    public String getItemName() {
        return itemName;
    }

    public double getItemPrice() {
        return itemPrice;
    }

    public String getItemLocation() {
        return itemLocation;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public void setItemPrice(double itemPrice) {
        this.itemPrice = itemPrice;
    }

    public void setItemLocation(String itemLocation) {
        this.itemLocation = itemLocation;
    }

    public String toString() {
        return String.format("Name: %s\nPrice: $%.2f\nLocation: %s\n", itemName, itemPrice, itemLocation);
    }
}