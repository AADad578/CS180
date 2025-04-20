import java.io.Serializable;
/**
 * Team Project Phase 2 -- Item Class
 *
 * This class is for items in a marketplace where
 * items can be bought or sold
 *
 * @author Vincent Holloway, lab sec 24
 *
 * @version April 20, 2025
 *
 */
public class Item implements ItemInterface, Serializable {
    private String itemName; //name of item
    private double itemPrice; //price of item
    private String itemLocation; //location of item
    private String itemPictureFileName; //picture file name of item
    private User itemOwner; //owner of item
    private static final double EPSILON = 0.001; //small epsilon value for double comparisons

    /**
     * Item Constructor Method
     * with four parameters
     *
     * @param itemName item name of item
     * @param itemPrice price of item
     * @param itemLocation location of item
     * @param itemPictureFileName picture file name of item
     * @param itemOwner owner of item
     */
    public Item(String itemName, double itemPrice, String itemLocation,
                String itemPictureFileName, User itemOwner) {
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.itemLocation = itemLocation;
        this.itemPictureFileName = itemPictureFileName;
        this.itemOwner = itemOwner;
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
     * A getter method for item owner
     *
     * @return item owner
     */
    public User getItemOwner() {
        return itemOwner;
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
     * A setter method for item owner
     * @param itemOwner item owner
     */
    public void setItemOwner(User itemOwner) {
        this.itemOwner = itemOwner;
    }

    /**
     * A toString() method that returns a formatted String including
     * item name, item price, item location, and item owner (but not item picture file name)
     *
     * @return the formatted String
     */
    public String toString() {
        return String.format("Name: %s\nPrice: $%.2f\nLocation: %s\nOwner: %s\n", itemName, itemPrice, 
                                itemLocation, itemOwner.getName());
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
     * @method getItemOwner() of Item Class
     */
    public boolean equals(Object o) {
        if (o instanceof Item) {
            Item item = (Item) o; //object o is cast as a Item object
            return this.itemName.equals(item.getItemName()) && Math.abs(this.itemPrice - item.getItemPrice()) < EPSILON &&
                    this.itemLocation.equals(item.getItemLocation()) && this.itemPictureFileName.equals(item.getItemPictureFileName())
                    && this.itemOwner.equals(item.getItemOwner());
        }
        return false;
    }
}