import java.io.Serializable;
/**
 * Team Project Phase 1 -- Item Class
 *
 * This class is for items in a marketplace where
 * items can be bought or sold
 *
 * @author Vincent Holloway, lab sec 24
 *
 * @version April 5, 2025
 *
 */
public class Item implements ItemInterface, Serializable {
    private String itemName; //name of item
    private double itemPrice; //price of item
    private String itemLocation; //location of item
    private String itemPictureFileName; //picture file name of item
    private static final double EPSILON = 0.001; //small epsilon value for double comparisons

    /**
     * Item Constructor Method
     * with four parameters
     *
     * @param itemName item name of item
     * @param itemPrice price of item
     * @param itemLocation location of item
     * @param itemPictureFileName picture file name of item
     */
    public Item(String itemName, double itemPrice, String itemLocation,
                String itemPictureFileName) {
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.itemLocation = itemLocation;
        this.itemPictureFileName = itemPictureFileName;
    }

    /**
     * A getter method for item name
     *
     * @return item name
     */
    public String getItemName() {
        return itemName;
    }

    /**
     * A getter method for item price
     *
     * @return item price
     */
    public double getItemPrice() {
        return itemPrice;
    }

    /**
     * A getter method for item location
     *
     * @return item location
     */
    public String getItemLocation() {
        return itemLocation;
    }

    /**
     * A getter method for item picture file name
     *
     * @return item picture file name
     */
    public String getItemPictureFileName() {
        return itemPictureFileName;
    }

    /**
     * A setter method for item name
     *
     * @param itemName item name
     */
    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    /**
     * A setter method for item price
     *
     * @param itemPrice item price
     */
    public void setItemPrice(double itemPrice) {
        this.itemPrice = itemPrice;
    }

    /**
     * A setter method for item location
     *
     * @param itemLocation item location
     */
    public void setItemLocation(String itemLocation) {
        this.itemLocation = itemLocation;
    }

    /**
     * A setter method for item picture file name
     *
     * @param itemPictureFileName item picture file name
     */
    public void setItemPictureFileName(String itemPictureFileName) {
        this.itemPictureFileName = itemPictureFileName;
    }

    /**
     * A toString() method that returns a formatted String including
     * item name, item price, and item location (but not item picture file name)
     *
     * @return the formatted String
     */
    public String toString() {
        return String.format("Name: %s\nPrice: $%.2f\nLocation: %s\n", itemName, itemPrice, itemLocation);
    }

    /**
     * A equals() method that determines whether two Item objects
     * are equal to each other
     *
     * @return whether two Item objects are equal to each other
     * @method getItemName() of Item Class
     * @method getItemPrice() of Item Class
     * @method getItemLocation() of Item Class
     * @method getItemPictureFileName() of Item Class
     */
    public boolean equals(Object o) {
        if (o instanceof Item) {
            Item item = (Item) o; //object o is cast as a Item object
            return this.itemName.equals(item.getItemName()) && Math.abs(this.itemPrice - item.getItemPrice()) < EPSILON &&
                    this.itemLocation.equals(item.getItemLocation()) && this.itemPictureFileName.equals(item.getItemPictureFileName());
        }
        return false;
    }
}
