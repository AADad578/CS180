package Server;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.util.ArrayList;

import Chat.Chat;
import Database.Database;
import Item.Item;
import User.User;

public class Server extends Thread implements ServerInterface {
    private static Database db;
    private static final Object dbUserGuard = new Object();
    private static final Object dbChatGuard = new Object();
    private static final Object dbItemGuard = new Object();
    private final ServerSocket socket;
    public boolean hasClient;

    /**
     * adds a user to the database and saves it
     * check if ID already exists
     * 
     * @param user the user to add
     * @throws InvalidInputException if user.equals any existing item
     */
    @Override
    public void addUser(User user) throws InvalidInputException {
        synchronized (dbUserGuard) {
            User[] currUsers = db.getUsers();
            User[] newUsers = new User[currUsers.length + 1];
            for (int i = 0; i < currUsers.length; i++) {
                newUsers[i] = currUsers[i];
                if (user.equals(currUsers[i])) {
                    throw new InvalidInputException("Username already exists");
                }
            }
            newUsers[currUsers.length] = user;
            db.setUsers(newUsers);
        }
        saveDatabase();
    }

    /**
     * removes the first user from the database that .equals and saves it
     * 
     * @param user the user to add
     * @throws InvalidInputException if user is not found
     */
    @Override
    public void removeUser(User user) throws InvalidInputException {
        synchronized (dbUserGuard) {
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
                    throw new InvalidInputException("Selected User not found");
                }
                newUsers[index] = currUsers[i];
                index++;
            }
            db.setUsers(newUsers);
        }
        saveDatabase();
    }

    /**
     * adds an item to the database and saves it
     * 
     * @param item the item to add
     */
    @Override
    public void addItem(Item item) {
        synchronized (dbItemGuard) {
            Item[] currItems = db.getItems();
            Item[] newItems = new Item[currItems.length + 1];
            System.arraycopy(currItems, 0, newItems, 0, currItems.length);
            newItems[currItems.length] = item;
            db.setItems(newItems);
        }
        saveDatabase();
    }

    /**
     * removes the first item from the database that exactly match and saves it
     * 
     * @param item the item to add
     * @throws InvalidInputException if item is not found
     */
    @Override
    public void removeItem(Item item) throws InvalidInputException {
        synchronized (dbItemGuard) {
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
                    throw new InvalidInputException("Selected Item not found");
                }
                newItems[index] = currItems[i];
                index++;
            }
            db.setItems(newItems);
        }
        saveDatabase();
    }

    /**
     * adds a chat to the database and saves it
     * 
     * @param chat the chat to add
     */
    @Override
    public void addChat(Chat chat) {
        synchronized (dbChatGuard) {
            Chat[] currChats = db.getChats();
            Chat[] newChats = new Chat[currChats.length + 1];
            System.arraycopy(currChats, 0, newChats, 0, currChats.length);
            newChats[currChats.length] = chat;
            db.setChats(newChats);
        }
        saveDatabase();
    }

    /**
     * removes a chat from the database if there is an exact match and saves it
     * 
     * @param chat the chat to add
     * @throws InvalidInputException if the chat is not found
     */
    @Override
    public void removeChat(Chat chat) throws InvalidInputException {
        synchronized (dbChatGuard) {
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
                    throw new InvalidInputException("Selected Chat not found");
                }
                newChats[index] = currChats[i];
                index++;
            }
            db.setChats(newChats);
        }
        saveDatabase();
    }

    /**
     * Saves the database to a file.
     * FileName = "database.db"
     * Should be called after any change to db
     */
    @Override
    public void saveDatabase() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("database.db"))) {
            synchronized (dbChatGuard) {
                synchronized (dbItemGuard) {
                    synchronized (dbUserGuard) {
                        oos.writeObject(db);
                    }
                }
            }
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
     * if file is not present IOException causes new database to be created
     */
    public static void recallDatabase() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("database.db"))) {
            Object o = ois.readObject();
            db = (Database) o;
        } catch (IOException e) {
            db = new Database(new Item[0], new Chat[0], new User[0]);
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
    @Override
    public ArrayList<Item> searchItems(String searchTerm) {
        ArrayList<Item> newItems = new ArrayList<>();
        for (Item i : db.getItems()) {
            if (i.getItemName().contains(searchTerm)) {
                newItems.add(i);
            }
        }
        return newItems;
    }

    @Override
    public void run() {
        if (!hasClient) {
            try {
                socket.accept();
                hasClient = true;
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    public Server(int port) throws IOException {
        this.socket = new ServerSocket(port);
        this.hasClient = false;
    }

    public static void main(String[] args) {
        recallDatabase();
        System.out.println("Server Started");
        ArrayList<Server> serverThreads = new ArrayList<>();
        int portNum = 8000;
        try {
            Server s = new Server(portNum);
            serverThreads.add(s);
            s.start();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        portNum++;
        while (true) {
            if (serverThreads.get(serverThreads.size() - 1).hasClient) {
                try {
                    Server s = new Server(portNum);
                    serverThreads.add(s);
                    s.start();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                portNum++;
            }
        }
    }

}
