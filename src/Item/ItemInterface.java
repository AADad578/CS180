package Item;

/**
 * Team Project Phase 2 -- Item Interface
 *
 * This interface is for the interface for the
 * Item Class
 *
 * @author Vincent Holloway, lab sec 24
 *
 * @version April 20, 2025
 *
 */
public interface ItemInterface {
    /**
     * A getter method for item name
     */
    String getItemName();

    /**
     * A getter method for item price
     */
    double getItemPrice();

    /**
     * A getter method for item location
     */
    String getItemLocation();

    /**
     * A getter method for item picture file name
     */
    String getItemPictureFileName();

    /**
     * A getter method for item owner
     */
    User getItemOwner();

    /**
     * A setter method for item name
     */
    void setItemName(String itemName);

    /**
     * A setter method for item price
     */
    void setItemPrice(double itemPrice);

    /**
     * A setter method for item location
     */
    void setItemLocation(String itemLocation);

    /**
     * A setter method for item picture file name
     */
    void setItemPictureFileName(String itemPictureFileName);

    /**
     * A setter method for item owner
     */
    void setItemOwner(User itemOwner);

    /**
     * A toString() method that returns a formatted String including
     * item name, item price, item location, and item owner (but not item picture file name)
     */
    @Override
    String toString();

    /**
     * A equals() method that determines whether two Item objects
     * are equal to each other
     */
    @Override
    boolean equals(Object o);
}
