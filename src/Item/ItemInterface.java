public interface ItemInterface {
    void itemCreate(Item item);
    void itemDelete(Item item);
    String itemSearch(String itemName, String itemLocation);
    String getItemName();
    double getItemPrice();
    String getItemLocation();
    void setItemName(String itemName);
    void setItemPrice(double itemPrice);
    void setItemLocation(String itemLocation);
    String toString();
}
