package Item;
import static org.junit.Assert.*;
import org.junit.*;
import java.io.*;

public class ItemTester {
    private final PrintStream originalOutput = System.out;
    @SuppressWarnings("FieldCanBeLocal")
    private ByteArrayOutputStream testOut;

    @Before
    public void outputStart() {
        testOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(testOut));
    }

    @After
    public void restoreOutput() {
        System.setOut(originalOutput);
    }

    private String getOutput() {
        return testOut.toString();
    }

    @Test(timeout = 1000)
    public void testItemMethods() {
        Item item = new Item("iPhone8", 340.99, "Chicago");

        String expected = "Item created and added to log.\n";
        item.itemCreate(item);
        String stuOut = getOutput();
        stuOut = stuOut.replace("\r\n", "\n");
        assertEquals("Error in creating item", expected.trim(), stuOut.trim());

        expected = "iPhone8, $340.99, Chicago\n";
        String searchResult = item.itemSearch("iPhone8", "Chicago");
        assertEquals("Error searching for item before delete", expected.trim(), searchResult.trim());

        expected = "Item deleted from log.\n";
        item.itemDelete(item);
        stuOut = getOutput();
        assertEquals("Error deleting item", expected.trim(), stuOut.trim());

        expected = "No item found!\n";
        searchResult = item.itemSearch("iPhone8", "Chicago");
        assertEquals("Error searching for item after delete", expected.trim(), searchResult.trim());
    }

    @Test
    public void testGetItemName() {
        Item item = new Item("iPhone8", 340.99, "Chicago");
        assertEquals("Error in getItemName()", "iPhone8", item.getItemName());
    }

    @Test
    public void testGetItemPrice() {
        Item item = new Item("iPhone8", 340.99, "Chicago");
        assertEquals("Error in getItemPrice()", 340.99, item.getItemPrice(), 0.001);
    }

    @Test
    public void testGetItemLocation() {
        Item item = new Item("iPhone8", 340.99, "Chicago");
        assertEquals("Error in getItemLocation()", "Chicago", item.getItemLocation());
    }

    @Test
    public void testSetItemName() {
        Item item = new Item("iPhone8", 340.99, "Chicago");
        item.setItemName("iPhone9");
        assertEquals("Error in setItemName()", "iPhone9", item.getItemName());
    }

    @Test
    public void testSetItemPrice() {
        Item item = new Item("iPhone8", 340.99, "Chicago");
        item.setItemPrice(360.99);
        assertEquals("Error in setItemPrice()", 360.99, item.getItemPrice(), 0.001);
    }

    @Test
    public void testSetItemLocation() {
        Item item = new Item("iPhone8", 340.99, "Chicago");
        item.setItemLocation("New York");
        assertEquals("Error in setItemLocation()", "New York", item.getItemLocation());
    }
}
