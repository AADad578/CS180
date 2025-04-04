package Server;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import Database.Database;
import User.User;

public class Server {
    private static Database db;

    /**
     * adds a user to the database and saves it
     * 
     * @param user the user to add
     */
    public static void addUser(User user) {
        User[] currUsers = db.getUsers();
        User[] newUsers = new User[currUsers.length + 1];
        for (int i = 0; i < currUsers.length; i++) {
            newUsers[i] = currUsers[i];
        }
        newUsers[currUsers.length] = user;
        db.setUsers(newUsers);
        saveDatabase();
    }

    /**
     * removes the first user from the database that exactly matches and saves it
     * 
     * @param user the user to add
     * @throws InvalidInputException if user is not found
     */
    public static void removeUser(User user) throws InvalidInputException {
        User[] currUsers = db.getUsers();
        User[] newUsers = new User[currUsers.length - 1];
        boolean searching = true;
        int index = 0;
        for (int i = 0; i < currUsers.length; i++) {
            if (searching && currUsers[i].equals(user)) {
                searching = false;
                index--;
                continue;
            } else if (i + 1 == currUsers.length) {
                // on the last item and didn't find a match
                throw new InvalidInputException();
            }
            newUsers[index] = currUsers[i];
            index++;
        }
        db.setUsers(newUsers);
        saveDatabase();
    }

    /**
     * adds an item to the database and saves it
     * 
     * @param item the item to add
     */
    public static void addItem(Item item) {
        Item[] currItems = db.getUsers();
        Item[] newItems = new Item[currItems.length + 1];
        for (int i = 0; i < currItems.length; i++) {
            newItems[i] = currItems[i];
        }
        newItems[currItems.length] = item;
        db.setItems(newItems);
        saveDatabase();
    }

    /**
     * removes an item from the database if there is an exact match and saves it
     * 
     * @param item the item to add
     * @throws InvalidInputException if item is not found
     */
    public static void removeItem(Item item) throws InvalidInputException {
        Item[] currItems = db.getItems();
        Item[] newItems = new Item[currItems.length - 1];
        boolean searching = true;
        int index = 0;
        for (int i = 0; i < currItems.length; i++) {
            if (searching && currItems[i].equals(item)) {
                searching = false;
                index--;
                continue;
            } else if (i + 1 == currItems.length) {
                // on the last item and didn't find a match
                throw new InvalidInputException();
            }
            newItems[index] = currItems[i];
            index++;
        }
        db.setItems(newItems);
        saveDatabase();
    }

    /**
     * adds a chat to the database and saves it
     * 
     * @param chat the chat to add
     */
    public static void addChat(Chat chat) {
        Chat[] currChats = db.getUsers();
        Chat[] newChats = new Chat[currChats.length + 1];
        for (int i = 0; i < currChats.length; i++) {
            newChats[i] = currChats[i];
        }
        newChats[currChats.length] = chat;
        db.setChats(newChats);
        saveDatabase();
    }

    /**
     * removes a chat from the database if there is an exact match and saves it
     * 
     * @param chat the chat to add
     */
    public static void removeChat(Chat chat) {
        Chat[] currChats = db.getChats();
        Chat[] newChats = new Chat[currChats.length - 1];
        boolean searching = true;
        int index = 0;
        for (int i = 0; i < currChats.length; i++) {
            if (searching && currChats[i].equals(chat)) {
                searching = false;
                index--;
                continue;
            } else if (i + 1 == currChats.length) {
                // on the last item and didn't find a match
                throw new InvalidInputException();
            }
            newChats[index] = currChats[i];
            index++;
        }
        db.setChats(newChats);
        saveDatabase();
    }

    /**
     * Saves the database to a file.
     * FileName = "database.db"
     * Should be called after any change to db
     */
    public static void saveDatabase() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("database.db"))) {
            oos.writeObject(db);
            oos.flush();
            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * recalls the database from a file.
     * FileName = "database.db"
     * Should be called on startup
     */
    public static void recallDatabase() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("database.db"))) {
            Object o = ois.readObject();
            db = (Database) o;
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * returns all items that match the search term
     * 
     * @param searchTerm the term to match with
     * @return all items that match
     */
    public static Item[] searchItems(String searchTerm) {

    }

    public static void main(String[] args) {

    }

}
