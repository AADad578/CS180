package Server;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;

import Chat.Chat;
import Chat.MessageException;
import Database.Database;
import Item.Item;
import Message.Message;
import User.User;

/**
 * Server
 *
 * This class handles the server side of the project. It will connect to clients
 * and make changes to the database.
 *
 * @version 4/6/2025
 *
 * @author Ankur Raghavan
 */
public class Server extends Thread implements ServerInterface {
    static Database db;
    private static final Object DB_USER_GUARD = new Object();
    private static final Object DB_CHAT_GUARD = new Object();
    private static final Object DB_ITEM_GUARD = new Object();
    private Socket socket;
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
        synchronized (DB_USER_GUARD) {
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
        synchronized (DB_USER_GUARD) {
            User[] currUsers = db.getUsers();
            if (currUsers.length == 0) {
                throw new InvalidInputException("Selected User not found");
            }
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
     * Gets all the users from the database
     * 
     * @return the list of users
     */
    @Override
    public User[] getUsers() {
        return db.getUsers();
    }

    /**
     * adds an item to the database and saves it
     * 
     * @param item the item to add
     */
    @Override
    public void addItem(Item item) throws InvalidInputException {
        synchronized (DB_ITEM_GUARD) {
            Item[] currItems = db.getItems();
            Item[] newItems = new Item[currItems.length + 1];
            for (int i = 0; i < currItems.length; i++) {
                newItems[i] = currItems[i];
                if (item.equals(currItems[i])) {
                    throw new InvalidInputException("Item already exists");
                }
            }
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
        synchronized (DB_ITEM_GUARD) {
            Item[] currItems = db.getItems();
            if (currItems.length == 0) {
                throw new InvalidInputException("Selected Item not found");
            }
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
    public void addChat(Chat chat) throws InvalidInputException {
        synchronized (DB_CHAT_GUARD) {
            Chat[] currChats = db.getChats();
            Chat[] newChats = new Chat[currChats.length + 1];
            for (int i = 0; i < currChats.length; i++) {
                newChats[i] = currChats[i];
                if (chat.equals(currChats[i])) {
                    throw new InvalidInputException("Chat already exists");
                }
            }
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
        synchronized (DB_CHAT_GUARD) {
            Chat[] currChats = db.getChats();
            if (currChats.length == 0) {
                throw new InvalidInputException("Selected Chat not found");
            }
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
     * 
     * not necessary to synchronize bc this is called after all changes are complete, 
     * so it will contain all changes even if one is occuring while it runs.
     */
    @Override
    public void saveDatabase() {
         try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("database.db"))) {
             oos.writeObject(db);
             oos.flush();
             oos.close();
         } catch (IOException e) {
             e.printStackTrace();
         }
        System.out.println("saved");
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
    public Item[] searchItems(String searchTerm) {
        ArrayList<Item> newItems = new ArrayList<>();
        for (Item i : db.getItems()) {
            if (i.getItemName().contains(searchTerm)) {
                newItems.add(i);
            }
        }
        Item[] out = new Item[0];
        return newItems.toArray(out);
    }

    /**
     * Gets the chat between two users
     * @param user1
     * @param user2
     * @return one chat that is between the two users
     * @throws InvalidInputException If users have same username or there aren't any chats between the users.
     */
    @Override
    public Chat getChat(User user1, User user2) throws InvalidInputException {
        if (user1.equals(user2)) {
            throw new InvalidInputException("Users have same username");
        }
        Chat in = new Chat(user1, user2);
        for (Chat i : db.getChats()) {
            if (i.equals(in)) {
                return i;
            }
        }
        throw new InvalidInputException("No Chat Found Between Selected Users");
    }
    
    /**
     * Adds a message to the chat between two users. If no chat is found, a new one is created
     * @param message the message to add 
     * @throws InvalidInputException If the users have the same username
     */
    @Override
    public void addMessage(Message message) throws InvalidInputException {
        Chat chat;
        if (message.getSender().equals(message.getReceiver())) {
            throw new InvalidInputException("Users have same username");
        }
        try {
            chat = getChat(message.getSender(), message.getReceiver());
        } catch (InvalidInputException e) {
            chat = new Chat(message.getSender(), message.getReceiver());
            addChat(chat);
        }
        if (message.getSender().equals(message.getReceiver())) {
            throw new InvalidInputException("Users have same username");
        }
        try {
            chat.addMessage(message);
        } catch (MessageException e) {
            throw new InvalidInputException(e.getMessage());
        }
        saveDatabase();
    }

    /**
     * Checks a user's username and password against a list of all the usernames and passwords
     * @param user the user object that contains the username and password
     * @throws InvalidInputException if password is wrong or username is not found.
     */
    @Override
    public void logIn (User user) throws InvalidInputException {
        User[] allUsers;
        synchronized (DB_USER_GUARD) {
            allUsers = db.getUsers();
        }
        for (User u : allUsers) {
            if (user.getUserName().equals(u.getUserName())) {
                if (user.getPassword().equals(u.getPassword())) {
                    return;
                } else {
                    throw new InvalidInputException("Invalid Password");
                }
            }
        }
        throw new InvalidInputException("Invalid Username");
    }

    /**
     * Gets all chats from/to one user
     * @param user 
     * @return all chats that include the user
     */
    @Override
    public Chat[] getChats(User user) {
        Chat[] allChats;
        synchronized (DB_CHAT_GUARD) {
            allChats = db.getChats();
        }
        ArrayList<Chat> out = new ArrayList<>();
        for (Chat c : allChats) {
            if(c.getUsers()[0].equals(user) || c.getUsers()[1].equals(user)) {
                out.add(c);
            }
        }
        Chat[] output = new Chat[0];
        output = out.toArray(output);
        return output;
    }
    
    /**
     * The function that contains all of the logic for recieving and responding to client communications
     * 
     * Gets called on thread startup and stays until client disconnects.
     */
    @Override
    public void run() {
        try {
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream()); //from client
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream()); //to client

            //parse client requests
            while (true) {
                Request input;
                try {
                    input = (Request) ois.readObject();
                } catch (IOException | ClassNotFoundException e) {
                    oos.writeObject(new Request("ERROR", "Failed to Transmit Data Correctly"));
                    continue;
                }
                String action = input.getAction();
                if (action.equals("CreateNewUser")) {
                    User user;
                    try {
                        user = (User) input.getPayload();
                    } catch (ClassCastException e) {
                        oos.writeObject(new Request("ERROR", "Payload Not a User"));
                        continue;
                    }
                    try {
                        addUser(user);
                    } catch (InvalidInputException e) {
                        oos.writeObject(new Request("ERROR", e.getMessage()));
                        continue;
                    }
                    oos.writeObject(new Request("OK", null));
                } else if (action.equals("CreateNewItem")) {
                    Item item;
                    try {
                        item = (Item) input.getPayload();
                    } catch (ClassCastException e) {
                        oos.writeObject(new Request("ERROR", "Payload Not an Item"));
                        continue;
                    }
                    try {
                        addItem(item);
                    } catch (InvalidInputException e) {
                        oos.writeObject(new Request("ERROR", e.getMessage()));
                        continue;
                    }
                    oos.writeObject(new Request("OK", null));
                } else if (action.equals("CreateNewChat")) {
                    Chat chat;
                    try {
                        chat = (Chat) input.getPayload();
                    } catch (ClassCastException e) {
                        oos.writeObject(new Request("ERROR", "Payload Not a Chat"));
                        continue;
                    }
                    try {
                        addChat(chat);
                    } catch (InvalidInputException e) {
                        oos.writeObject(new Request("ERROR", e.getMessage()));
                        continue;
                    }
                    oos.writeObject(new Request("OK", null));
                } else if (action.equals("AddMessage")) {
                    Message message;
                    try {
                        message = (Message) input.getPayload();
                    } catch (ClassCastException e) {
                        oos.writeObject(new Request("ERROR", "Payload Not a Message"));
                        continue;
                    }
                    try {
                        addMessage(message);
                    } catch (InvalidInputException e) {
                        oos.writeObject(new Request("ERROR", e.getMessage()));
                        continue;
                    }
                    oos.writeObject(new Request("OK", null));
                } else if (action.equals("GetUsers")) {
                    User[] users = getUsers();
                    oos.writeObject(new Request("RESPONSE", users));
                } else if (action.equals("GetChats")) {
                    User user;
                    try {
                        user = (User) input.getPayload();
                    } catch (ClassCastException e) {
                        oos.writeObject(new Request("ERROR", "Payload Not a User"));
                        continue;
                    }
                    Chat[] chats;
                    chats = getChats(user);
                    oos.writeObject(new Request("RESPONSE", chats));
                } else if (action.equals("SearchItems")) {
                    String searchTerm;
                    try {
                        searchTerm = (String) input.getPayload();
                    } catch (ClassCastException e) {
                        oos.writeObject(new Request("ERROR", "Payload Not a String"));
                        continue;
                    }
                    Item[] out = searchItems(searchTerm);
                    oos.writeObject(new Request("RESPONSE", out));
                } else if (action.equals("LogInUser")) {
                    User user;
                    try {
                        user = (User) input.getPayload();
                    } catch (ClassCastException e) {
                        oos.writeObject(new Request("ERROR", "Payload Not a User"));
                        continue;
                    }
                    try {
                        logIn(user);
                    } catch (InvalidInputException e) {
                        oos.writeObject(new Request("ERROR", e.getMessage()));
                        continue;
                    }
                    oos.writeObject(new Request("OK", null));
                }
            }
        } catch (IOException e) {
            return;
        }
    }


    /**
     * Instantiates a Server object with the specified socket
     * 
     * @param socket The Socket that contains the contact with the client.
     */
    public Server(Socket socket) {
        this.socket = socket;
    }

    /**
     * Starts new threads of the server as clients connect. All clients connect to port 8000
     * 
     * @param args
     */
    public static void main(String[] args) {
        recallDatabase();
        System.out.println("Server Started");
        try (ServerSocket serverSocket = new ServerSocket(8000)) {
            while (true) {
                Socket socket = serverSocket.accept();
                Server server = new Server(socket);
                server.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }

}
