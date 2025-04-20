package Item;

import static org.junit.Assert.*;

import Message.Message;
import User.User;
import org.junit.*;

/**
 * Team Project Phase 2 -- ItemTester Class
 *
 * This class is the testing class for the Item class
 *
 * @author Vincent Holloway, lab sec 24
 *
 * @version April 20, 2025
 *
 */
public class ItemTester {
    private Item item; //test item object
    private User owner; //test owner User object
    private static final double EPSILON = 0.001; //very small epsilon value for double comparisons

    /**
     * A setUp() that sets up this exact Item object for each test method
     */
    @Before
    public void setUp() {
        Item[] items = new Item[2]; 
        owner = new User("Billy", 24.65, items, "bfollow", "etgfrv493");
                                //test owner User object
        items[0] = new Item("iPhone7", 140.99, "Portland", "image1.jpg", owner);
        items[1] = new Item("Galaxy Phone", 199.99, "Miami", "image2.jpg", owner); 
                                    //test list of items for User object    
        item = new Item("iPhone8", 340.99, "Chicago", "itemImage.jpg", owner);
                            //test item object
    }

    /**
     * Tests the getItemName() method
     */
    @Test
    public void testGetItemName() {
        assertEquals("Error in getItemName()", "iPhone8", item.getItemName());
    }

    /**
     * Tests the getItemPrice() method
     */
    @Test
    public void testGetItemPrice() {
        assertEquals("Error in getItemPrice()", 340.99, item.getItemPrice(), EPSILON);
    }

    /**
     * Tests the getItemLocation() method
     */
    @Test
    public void testGetItemLocation() {
        assertEquals("Error in getItemLocation()", "Chicago", item.getItemLocation());
    }

    /**
     * Tests the getItemPictureFileName() method
     */
    @Test
    public void testGetItemPictureFileName() {
        assertEquals("Error in getItemPictureFileName()", "itemImage.jpg", item.getItemPictureFileName());
    }

    /**
     * Tests the getItemOwner() method
     */
    @Test
    public void testGetItemOwner() {
        assertEquals("Error in getItemOwner()", owner, item.getItemOwner());
    }

    /**
     * Tests the setItemName() method
     */
    @Test
    public void testSetItemName() {
        item.setItemName("iPhone9");
        assertEquals("Error in setItemName()", "iPhone9", item.getItemName());
    }

    /**
     * Tests the setItemPrice() method
     */
    @Test
    public void testSetItemPrice() {
        item.setItemPrice(360.99);
        assertEquals("Error in setItemPrice()", 360.99, item.getItemPrice(), EPSILON);
    }

    /**
     * Tests the setItemLocation() method
     */
    @Test
    public void testSetItemLocation() {
        item.setItemLocation("New York");
        assertEquals("Error in setItemLocation()", "New York", item.getItemLocation());
    }

    /**
     * Tests the setItemPictureFileName() method
     */
    @Test
    public void testSetItemPictureFileName() {
        item.setItemPictureFileName("newItemPicture.jpg");
        assertEquals("Error in setItemPictureFileName()", "newItemPicture.jpg", item.getItemPictureFileName());
    }

    /**
     * Tests the setItemOwner() method
     */
    @Test
    public void testSetItemOwner() {
        item.setItemOwner(owner);
        assertEquals("Error in setItemOwner()", owner, item.getItemOwner());
    }

    /**
     * Tests the toString() method
     */
    @Test
    public void testToString() {
        String expected = String.format("Name: %s\nPrice: $%.2f\nLocation: %s\nOwner: %s\n",
                "iPhone8", 340.99, "Chicago", "Billy"); //string of expected output
        assertEquals("Error in toString method", expected.trim(), item.toString().trim());
    }

    /**
     * Tests the equals() method
     */
    @Test
    public void testEquals() {
        Item item2 = new Item("iPhone8", 340.99, "Chicago", "itemImage.jpg", owner);
                                                                        //test item object that's equal to item
        Item item3 = new Item("iPhone8", 340.98, "Chicago", "itemImage.jpg", owner);
                                                                //test item object that's not equal to item with differing price
        Item item4 = new Item("iPhone8", 340.99, "New York", "itemImage.jpg", owner);
                                            //test item object that's not equal to item with differing location
        Item item5 = new Item("NotIPhone8", 340.99, "Chicago", "itemImage.jpg", owner);
                                                        //test item object that's not equal to item with differing name
        Item item6 =  new Item("iPhone8", 340.99, "Chicago", "newItemImage.jpg", owner);
                                        //test item object that's not equal to item with differing picture file name 
        User newOwner = new User("Radny", 53.65, items, "ranllow", "ejdfijdfoijf");    //new owner User object                           
        Item item7 =  new Item("iPhone8", 340.99, "Chicago", "newItemImage.jpg", newOwner);
                                //test item object that's not equal to item with differing owner                                                                

        Item[] messageItems = {
                new Item("iPhone7", 140.99, "Portland", "image1.jpg", owner),
                new Item("Galaxy Phone", 199.99, "Miami", "image2.jpg", owner),
        }; //test list of Item objects for User objects
        User sender = new User("Vincent", 54.65, messageItems, "vhollow", "etefev443"); //test User object for Message object
        User receiver = new User("NotVincent", 64.65, messageItems, "novince", "teertk43j"); //test User object for Message object
        Message message = new Message("Can I buy your phone?", 1756,
                sender, receiver); // test Message object

        assertTrue("Item should equal Item2", item.equals(item2));
        assertFalse("Item shouldn't equal Item3", item.equals(item3));
        assertFalse("Item shouldn't equal Item4", item.equals(item4));
        assertFalse("Item shouldn't equal item5", item.equals(item5));
        assertFalse("Item shouldn't equal item6", item.equals(item6));
        assertFalse("Item shouldn't equal item7", item.equals(item7));
        assertFalse("Item shouldn't equal a non-Item", item.equals(message));
    }
}
