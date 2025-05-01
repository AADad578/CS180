package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.time.LocalTime;

import Chat.Chat;
import Client.Client;
import Item.Item;
import Message.Message;
import User.User;

/**
 * Team Project Phase 3 -- GUI Class
 *
 * This class is the GUI for the Marketplace
 *
 * @author Vincent Holloway, lab sec 24
 *
 * @version May 1, 2025
 *
 */

public class GUI implements Runnable, GUI_Interface {
    private JFrame frame; //frame of GUI

    private JButton loginButton; //login button on welcome screen
    private JButton loginEnterButton; //enter button for login screen
    private JButton createButton; //create account button on welcome screen
    private JButton createEnterButton; //enter button for create screen

    private JButton searchButton; //search button for item search
    private JButton chatButton; //chat button that leads to chat screen
    private JButton itemButton; //item button that leads to item screen
    private JButton userButton; //view user profile button that leads to user profile

    private JTextField itemSearch; //text field for item search
    private JButton itemDownButton; //down button for search item screen

    private JButton createChatButton; //creates chat button in chat screen
    private JButton addMessageButton; //add message button in chat screen
    private JTextField userSendToField; //text field of user receving message
    private JButton createChatEnterButton; //enter button of creating chat screen
    private JButton viewChatsEnterButton; //enter button of view chats screen
    private JButton chatDownButton; //down button for view chats screen
    private JButton addMessageEnterButton; //enter button of add message screen
    private JTextField messageField; //text field for message content
    
    private JTextField itemNameField; //item name 
    private JTextField itemPriceField; //item price 
    private JTextField itemLocationField; //item location 
    private JTextField itemPictureFileNameField; //item picture file name 
    private JButton itemEnterButton; //item enter button 

    private JButton updateUserButton; //update user button
    private JButton updateUserEnterButton; //enter button for updating user

    private JButton exitButton; //back button in the chat, search, balance,
                                //item, view user profile screens

    private JTextField usernameField; //text field for username
    private JTextField passwordField; //text field for password
    private JTextField nameField; //text field for first and last name
    private JTextField balanceField; //text field for balance
    private String username; //username of user
    private String password; //password of user
    private String name; //name of user
    private double balance; //balance of user

    private Client client = new Client(); //client object
    private User user = null; //user object
    private User userSendTo = null; //user object of user receving message     
    private Chat[] chatList = null;  
    private int chatListIndex;   
    private Item[] itemList = null;
    private int itemListIndex;

    /**
     * The main method for GUI class
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new GUI());
    }

    /**
     * The run() method for GUI class
     */
    public void run() {
        this.welcome();
    }

    /**
     * The actionListener method for GUI class
     *
     * @param ActionEvent e Any action event resulting from button press
     */
    ActionListener actionListener = new ActionListener() { //new ActionListener Object
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == loginButton) {
                frame.dispose();
                GUI.this.login();
            }
            if (e.getSource() == createButton) {
                frame.dispose();
                GUI.this.createAccount();
            }
            if (e.getSource() == loginEnterButton) {
                try {
                    username = usernameField.getText();
                    password = passwordField.getText();                                             
                    User[] userList = client.getUsers();                   
                    for (User aUser : userList) {
                        if (aUser.getUserName().equals(username) && aUser.getPassword().equals(password)) {
                            user = new User(aUser.getName(), aUser.getBalance(), username, password);
                            break;
                        }
                    }   
                    name = user.getName(); 
                    balance = user.getBalance();            
                    client.logInUser(user);
                    frame.dispose();
                    GUI.this.defaultView();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Unable To Login In", 
                                            "Error", JOptionPane.ERROR_MESSAGE);     
                    GUI.this.defaultView(); //REMOVE THIS                                         
                }        
            }
            if (e.getSource() == createEnterButton) {
                try {
                    username = usernameField.getText();
                    password = passwordField.getText();                 
                    name = nameField.getText();
                    balance = 0;  
                    user = new User(name, balance, username, password);                  
                    client.createNewUser(user);                  
                    frame.dispose();
                    GUI.this.defaultView();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Unable To Create New User", 
                                            "Error", JOptionPane.ERROR_MESSAGE);
                }        
            }
            if (e.getSource() == searchButton) {
                try {
                    String searchTerm = itemSearch.getText();
                    itemList = client.searchItems(searchTerm);                                   
                    frame.dispose();
                    GUI.this.search();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Unable To Search Item", 
                                            "Error", JOptionPane.ERROR_MESSAGE);
                }                 
            }
            if (e.getSource() == itemDownButton) {
                try {
                    frame.dispose();
                    frame = new JFrame("Search"); //new frame
                    JPanel centerPanel = new JPanel(); //new JPanel Object for panel
                    centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        
                    if (itemList.length > itemListIndex) {
                        Item item = itemList[itemListIndex];
                        JLabel itemNameLabel = new JLabel(String.format("Name of Item: %s", item.getItemName()));
                        itemNameLabel.setFont(new Font("SansSerif", Font.BOLD, 24));
                        centerPanel.add(itemNameLabel);
                
                        JLabel itemPriceLabel = new JLabel(String.format("Price of Item: %.2f", item.getItemPrice()));
                        itemPriceLabel.setFont(new Font("SansSerif", Font.BOLD, 24));
                        centerPanel.add(itemPriceLabel);
                
                        JLabel itemLocationLabel = new JLabel(String.format("Location of Item: %s", 
                                                                item.getItemLocation()));
                        itemLocationLabel.setFont(new Font("SansSerif", Font.BOLD, 24));
                        centerPanel.add(itemLocationLabel);
                
                        JLabel itemPictureFileNameLabel = new JLabel(String.format("Picture of Item: %s", 
                                                                        item.getItemPictureFileName()));
                        itemPictureFileNameLabel.setFont(new Font("SansSerif", Font.BOLD, 24));
                        centerPanel.add(itemPictureFileNameLabel);
        
                        itemListIndex++;
                        itemDownButton = new JButton("↓");
                        centerPanel.add(itemDownButton);
                        itemDownButton.addActionListener(actionListener);    
                        frame.add(centerPanel, BorderLayout.CENTER);      
                    }
                    
        
                    JPanel bottomPanel = new JPanel(); //bottom panel
                    exitButton = new JButton("Back");
                    bottomPanel.add(exitButton);
                    exitButton.addActionListener(actionListener);
                    frame.add(bottomPanel, BorderLayout.SOUTH);
        
                    exitGUI();
        
                    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    frame.setSize(800, 600);
                    frame.setLocationRelativeTo(null);
                    frame.setVisible(true);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Unable To Search Item", 
                    "Error", JOptionPane.ERROR_MESSAGE);
                    GUI.this.defaultView();
                }
            }
            if (e.getSource() == chatButton) {
                frame.dispose();
                GUI.this.chat();
            }
            if (e.getSource() == itemButton) {
                frame.dispose();
                GUI.this.item();
            }
            if (e.getSource() == userButton) {
                frame.dispose();
                GUI.this.user();
            }
            if (e.getSource() == exitButton) {
                frame.dispose();
                GUI.this.defaultView();
            }
            if (e.getSource() == createChatButton) {
                frame.dispose();
                GUI.this.createChat();
            }
            if (e.getSource() == createChatEnterButton) {
                try {
                    boolean valid = false;
                    String userSendToUserName = userSendToField.getText();
                    User[] userList = client.getUsers();    
                    for (User aUser : userList) {
                        if (aUser.getUserName().equals(userSendToUserName)) {
                            valid = true;
                            userSendTo = aUser;
                            break;
                        }
                    } 
                    if (valid) {
                        Chat chat = new Chat(user, userSendTo);                 
                        client.createNewChat(chat);             
                        JOptionPane.showMessageDialog(null, "Chat Successfully Created!", 
                                            "New Chat", JOptionPane.PLAIN_MESSAGE);         
                        addMessage();                        
                    } else {
                        JOptionPane.showMessageDialog(null, "Unable To Find User", 
                                            "Error", JOptionPane.ERROR_MESSAGE);                                           
                    }                                
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Unable To Create New Chat", 
                                            "Error", JOptionPane.ERROR_MESSAGE);
                }    
            }
            if (e.getSource() == addMessageButton) {
                frame.dispose();
                GUI.this.viewChats();
            }
            if (e.getSource() == viewChatsEnterButton) {
                frame.dispose();
                GUI.this.addMessage();
            }
            if (e.getSource() == chatDownButton) {
                try {
                    frame.dispose();
                    frame = new JFrame("View Chats"); //new frame
                    JPanel centerPanel = new JPanel(); //new JPanel Object for panel
                    centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS)); 
        
                    if (chatList.length > chatListIndex) {
                        User[] users = chatList[chatListIndex].getUsers();
                        if (!users[0].equals(user)) {
                            userSendTo = users[0];
                        } else {
                            userSendTo = users[1];
                        }
                        JLabel userSendToLabel = new JLabel(String.format("Chat with %s", userSendTo.getName()));
                        userSendToLabel.setFont(new Font("SansSerif", Font.BOLD, 24));
                        centerPanel.add(userSendToLabel);
        
                        viewChatsEnterButton = new JButton("Add Message");
                        centerPanel.add(viewChatsEnterButton);
                        viewChatsEnterButton.addActionListener(actionListener); 
        
                        chatListIndex++;
                        chatDownButton = new JButton("↓");
                        centerPanel.add(chatDownButton);
                        chatDownButton.addActionListener(actionListener);     
                        frame.add(centerPanel, BorderLayout.CENTER);           
                    }
        
                    JPanel bottomPanel = new JPanel(); //bottom panel
                    exitButton = new JButton("Back");
                    bottomPanel.add(exitButton);
                    exitButton.addActionListener(actionListener);
                    frame.add(bottomPanel, BorderLayout.SOUTH);

                    exitGUI();

                    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    frame.setSize(600, 400);
                    frame.setLocationRelativeTo(null);
                    frame.setVisible(true);   
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Unable To View Chats", 
                    "Error", JOptionPane.ERROR_MESSAGE);
                    GUI.this.defaultView();
                }
            }
            if (e.getSource() == addMessageEnterButton) {
                try {
                    String messageContent = messageField.getText();
                    if (messageContent.trim().isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Message Cannot Be Empty", 
                                                      "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    LocalTime currentTime = LocalTime.now();
                    int hour = currentTime.getHour() * 100; // 0-23
                    int minute = currentTime.getMinute(); // 0-59
                    int timeSent = hour + minute;
                    Message message = new Message(messageContent, timeSent, user, userSendTo);     
                    client.addMessage(message);
                    JOptionPane.showMessageDialog(null, "Message Successfully Sent!", 
                                            "Success", JOptionPane.PLAIN_MESSAGE);
                    frame.dispose();
                    GUI.this.defaultView();
                } catch (Exception ex) { 
                    JOptionPane.showMessageDialog(null, "Unable To Create New Message", 
                                            "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
            if (e.getSource() == itemEnterButton) {
                try {
                    String itemName = itemNameField.getText();
                    double itemPrice = Double.parseDouble(itemPriceField.getText());              
                    String itemLocation = itemLocationField.getText();
                    String itemPictureFileName = itemPictureFileNameField.getText();
                    Item item = new Item(itemName, itemPrice, itemLocation, itemPictureFileName, user);                  
                    client.createNewItem(item);             
                    JOptionPane.showMessageDialog(null, "Item Successfully Created!", 
                                            "New Item", JOptionPane.PLAIN_MESSAGE);                  
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Unable To Create New Item", 
                                            "Error", JOptionPane.ERROR_MESSAGE);
                }    
            }           
            if (e.getSource() == updateUserButton) {
                frame.dispose();
                GUI.this.updateUser();
            }
            if (e.getSource() == updateUserEnterButton) {
                try {
                    User[] userList = client.getUsers();     
                    for (User aUser : userList) {
                        if (!aUser.equals(user) && aUser.getUserName().equals(usernameField.getText())) {
                            JOptionPane.showMessageDialog(null, "Username Already Taken", 
                                            "Error", JOptionPane.ERROR_MESSAGE);
                            return;
                        }
                    }                 
                    username = usernameField.getText();
                    password = passwordField.getText();                 
                    name = nameField.getText();
                    balance = Double.parseDouble(balanceField.getText());                                                       
                    user = new User(name, balance, username, password);                  
                    client.updateUser(user);                                  
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Unable To Update Account", 
                                            "Error", JOptionPane.ERROR_MESSAGE);
                }
                frame.dispose();
                GUI.this.user();    
            }
        }
    };

    public void updateUser() {
        frame = new JFrame("Login"); //new frame
        JPanel centerPanel = new JPanel(); //new JPanel Object for panel
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));

        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setFont(new Font("SansSerif", Font.BOLD, 18));
        centerPanel.add(usernameLabel);

        usernameField = new JTextField(15);
        centerPanel.add(usernameField);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setFont(new Font("SansSerif", Font.BOLD, 18));
        centerPanel.add(passwordLabel);

        passwordField = new JTextField(15);
        centerPanel.add(passwordField);

        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setFont(new Font("SansSerif", Font.BOLD, 18));
        centerPanel.add(nameLabel);

        nameField = new JTextField(15);
        centerPanel.add(nameField);

        JLabel balanceLabel = new JLabel("Balance:");
        balanceLabel.setFont(new Font("SansSerif", Font.BOLD, 18));
        centerPanel.add(balanceLabel);

        balanceField = new JTextField(15);
        centerPanel.add(balanceField);

        updateUserEnterButton = new JButton("Enter");
        centerPanel.add(updateUserEnterButton);
        updateUserEnterButton.addActionListener(actionListener);
        frame.add(centerPanel, BorderLayout.CENTER);

        exitGUI();

        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    /**
     * The frame and panel for the view user screen
     */
    public void user() {
        frame = new JFrame("User Profile"); //new frame
        Container content = frame.getContentPane();
        content.setLayout(new BorderLayout());

        //show user profile
        JLabel userProfileLabel = new JLabel("User Profile:");
        userProfileLabel.setFont(new Font("SansSerif", Font.BOLD, 28));
        content.add(userProfileLabel, BorderLayout.NORTH);

        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));

        JLabel nameLabel = new JLabel(String.format("Name: %s", name));
        nameLabel.setFont(new Font("SansSerif", Font.PLAIN, 21));
        infoPanel.add(nameLabel);

        JLabel usernameLabel = new JLabel(String.format("Username: %s", username));
        usernameLabel.setFont(new Font("SansSerif", Font.PLAIN, 21));
        infoPanel.add(usernameLabel);

        JLabel passwordLabel = new JLabel(String.format("Password: %s", password));
        passwordLabel.setFont(new Font("SansSerif", Font.PLAIN, 21));
        infoPanel.add(passwordLabel);

        JLabel balanceLabel = new JLabel(String.format("Balance: %.2f", balance));
        balanceLabel.setFont(new Font("SansSerif", Font.PLAIN, 21));
        infoPanel.add(balanceLabel);

        updateUserButton = new JButton("Update Account");
        infoPanel.add(updateUserButton);
        updateUserButton.addActionListener(actionListener);

        content.add(infoPanel, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel(); //bottom panel
        exitButton = new JButton("Back");
        bottomPanel.add(exitButton);
        exitButton.addActionListener(actionListener);
        content.add(bottomPanel, BorderLayout.SOUTH);

        exitGUI();

        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    /**
     * The frame and panel for the add item screen
     */
    public void item() {
        frame = new JFrame("Add Item"); //new frame
        Container content = frame.getContentPane();
        content.setLayout(new BorderLayout());

        JPanel centerPanel = new JPanel(); //new JPanel Object for panel
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));

        JLabel itemNameLabel = new JLabel("Name of Item:");
        itemNameLabel.setFont(new Font("SansSerif", Font.BOLD, 24));
        centerPanel.add(itemNameLabel);

        itemNameField = new JTextField(15);
        centerPanel.add(itemNameField);

        JLabel itemPriceLabel = new JLabel("Price of Item:");
        itemPriceLabel.setFont(new Font("SansSerif", Font.BOLD, 24));
        centerPanel.add(itemPriceLabel);

        itemPriceField = new JTextField(15);
        centerPanel.add(itemPriceField);

        JLabel itemLocationLabel = new JLabel("Location of Item:");
        itemLocationLabel.setFont(new Font("SansSerif", Font.BOLD, 24));
        centerPanel.add(itemLocationLabel);

        itemLocationField = new JTextField(15);
        centerPanel.add(itemLocationField);

        JLabel itemPictureFileNameLabel = new JLabel("Picture File Name of Item:");
        itemPictureFileNameLabel.setFont(new Font("SansSerif", Font.BOLD, 24));
        centerPanel.add(itemPictureFileNameLabel);

        itemPictureFileNameField = new JTextField(15);
        centerPanel.add(itemPictureFileNameField);

        itemEnterButton = new JButton("Enter");
        centerPanel.add(itemEnterButton);
        itemEnterButton.addActionListener(actionListener);
        frame.add(centerPanel, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel(); //bottom panel
        exitButton = new JButton("Back");
        bottomPanel.add(exitButton);
        exitButton.addActionListener(actionListener);
        content.add(bottomPanel, BorderLayout.SOUTH);

        exitGUI();

        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public void addMessage() {
        frame = new JFrame("Add Message"); //new frame
        JPanel centerPanel = new JPanel(); //new JPanel Object for panel
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));

        JLabel messageLabel = new JLabel("Write Message:");
        messageLabel.setFont(new Font("SansSerif", Font.BOLD, 24));
        centerPanel.add(messageLabel);

        messageField = new JTextField(30);
        centerPanel.add(messageField);

        addMessageEnterButton = new JButton("Enter");
        centerPanel.add(addMessageEnterButton);
        addMessageEnterButton.addActionListener(actionListener);
        frame.add(centerPanel, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel(); //bottom panel
        exitButton = new JButton("Back");
        bottomPanel.add(exitButton);
        exitButton.addActionListener(actionListener);
        frame.add(bottomPanel, BorderLayout.SOUTH);

        exitGUI();

        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);   
    }

    public void viewChats() {
        try {
            frame = new JFrame("View Chats"); //new frame
            JPanel centerPanel = new JPanel(); //new JPanel Object for panel
            centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS)); 
            chatList = client.getChats(user);
            chatListIndex = 0;

            if (chatList.length != 0) {
                User[] users = chatList[0].getUsers();
                if (!users[0].equals(user)) {
                    userSendTo = users[0];
                } else {
                    userSendTo = users[1];
                }
                JLabel userSendToLabel = new JLabel(String.format("Chat with %s", userSendTo.getName()));
                userSendToLabel.setFont(new Font("SansSerif", Font.BOLD, 24));
                centerPanel.add(userSendToLabel);

                viewChatsEnterButton = new JButton("Add Message");
                centerPanel.add(viewChatsEnterButton);
                viewChatsEnterButton.addActionListener(actionListener); 

                chatListIndex++;
                chatDownButton = new JButton("↓");
                centerPanel.add(chatDownButton);
                chatDownButton.addActionListener(actionListener);    
                frame.add(centerPanel, BorderLayout.CENTER);      
            }

            JPanel bottomPanel = new JPanel(); //bottom panel
            exitButton = new JButton("Back");
            bottomPanel.add(exitButton);
            exitButton.addActionListener(actionListener);
            frame.add(bottomPanel, BorderLayout.SOUTH);

            exitGUI();

            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame.setSize(600, 400);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);   
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Unable To View Chats", 
            "Error", JOptionPane.ERROR_MESSAGE);
            GUI.this.defaultView();
        }       
    }

    public void createChat() {
        frame = new JFrame("Create Chat"); //new frame
        JPanel centerPanel = new JPanel(); //new JPanel Object for panel
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));

        JLabel userSendToLabel = new JLabel("Message Being Sent To (Insert Username):");
        userSendToLabel.setFont(new Font("SansSerif", Font.BOLD, 24));
        centerPanel.add(userSendToLabel);

        userSendToField = new JTextField(15);
        centerPanel.add(userSendToField);

        createChatEnterButton = new JButton("Enter");
        centerPanel.add(createChatEnterButton);
        createChatEnterButton.addActionListener(actionListener);
        frame.add(centerPanel, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel(); //bottom panel
        exitButton = new JButton("Back");
        bottomPanel.add(exitButton);
        exitButton.addActionListener(actionListener);
        frame.add(bottomPanel, BorderLayout.SOUTH);

        exitGUI();

        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);    
    }

    /**
     * The frame and panel for the chat screen
     */
    public void chat() {
        frame = new JFrame("Chat"); //frame
        Container content = frame.getContentPane();
        content.setLayout(new BorderLayout());

        JPanel panel = new JPanel(); //panel
        panel.setLayout(new GridLayout(2,0));

        createChatButton = new JButton("Create Chat");
        panel.add(createChatButton);
        createChatButton.addActionListener(actionListener);

        addMessageButton = new JButton("Add Message");
        panel.add(addMessageButton);
        addMessageButton.addActionListener(actionListener);
        content.add(panel, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel(); //bottom panel
        exitButton = new JButton("Back");
        bottomPanel.add(exitButton);
        exitButton.addActionListener(actionListener);
        content.add(bottomPanel, BorderLayout.SOUTH);

        exitGUI();

        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    /**
     * The frame and panel for the search screen
     */
    public void search() { //if item search is valid
        try {
            frame = new JFrame("Search"); //new frame
            JPanel centerPanel = new JPanel(); //new JPanel Object for panel
            centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));

            //show items
            itemListIndex = 0;
            if (itemList.length != 0) {
                Item item = itemList[0];
                JLabel itemNameLabel = new JLabel(String.format("Name of Item: %s", item.getItemName()));
                itemNameLabel.setFont(new Font("SansSerif", Font.BOLD, 24));
                centerPanel.add(itemNameLabel);
        
                JLabel itemPriceLabel = new JLabel(String.format("Price of Item: %.2f", item.getItemPrice()));
                itemPriceLabel.setFont(new Font("SansSerif", Font.BOLD, 24));
                centerPanel.add(itemPriceLabel);
        
                JLabel itemLocationLabel = new JLabel(String.format("Location of Item: %s", 
                                                        item.getItemLocation()));
                itemLocationLabel.setFont(new Font("SansSerif", Font.BOLD, 24));
                centerPanel.add(itemLocationLabel);
        
                JLabel itemPictureFileNameLabel = new JLabel(String.format("Picture of Item: %s", 
                                                                item.getItemPictureFileName()));
                itemPictureFileNameLabel.setFont(new Font("SansSerif", Font.BOLD, 24));
                centerPanel.add(itemPictureFileNameLabel);

                itemListIndex++;
                itemDownButton = new JButton("↓");
                centerPanel.add(itemDownButton);
                itemDownButton.addActionListener(actionListener);    
                frame.add(centerPanel, BorderLayout.CENTER);      
            }
            

            JPanel bottomPanel = new JPanel(); //bottom panel
            exitButton = new JButton("Back");
            bottomPanel.add(exitButton);
            exitButton.addActionListener(actionListener);
            frame.add(bottomPanel, BorderLayout.SOUTH);

            exitGUI();

            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame.setSize(800, 600);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Unable To Search Item", 
            "Error", JOptionPane.ERROR_MESSAGE);
            GUI.this.defaultView();
        }
    }

    /**
     * The frame and panel for the default view screen
     */
    public void defaultView() {
        frame = new JFrame("Market Place"); //new frame
        Container content = frame.getContentPane();
        content.setLayout(new BorderLayout());

        JPanel topPanel = new JPanel(); //top panel
        JLabel searchLabel = new JLabel("Search For Items");
        topPanel.add(searchLabel);
        itemSearch = new JTextField(30);
        topPanel.add(itemSearch);

        searchButton = new JButton("Enter");
        topPanel.add(searchButton);
        searchButton.addActionListener(actionListener);
        content.add(topPanel, BorderLayout.NORTH);

        JPanel panel = new JPanel(); //panel
        panel.setLayout(new GridLayout(3,0));

        chatButton = new JButton("Chat");
        panel.add(chatButton);
        chatButton.addActionListener(actionListener);

        itemButton = new JButton("Add Item");
        panel.add(itemButton);
        itemButton.addActionListener(actionListener);

        userButton = new JButton("View User Profile");
        panel.add(userButton);
        userButton.addActionListener(actionListener);
        content.add(panel, BorderLayout.CENTER);

        exitGUI();

        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    /**
     * The frame and panel for the create account screen
     */
    public void createAccount() { //while loop until new user is valid
        frame = new JFrame("Create Account"); //new frame
        JPanel centerPanel = new JPanel(); //new JPanel Object for panel
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));

        JLabel nameLabel = new JLabel("Enter First and Last Name:");
        nameLabel.setFont(new Font("SansSerif", Font.BOLD, 24));
        centerPanel.add(nameLabel);

        nameField = new JTextField(15);
        centerPanel.add(nameField);

        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setFont(new Font("SansSerif", Font.BOLD, 24));
        centerPanel.add(usernameLabel);

        usernameField = new JTextField(15);
        centerPanel.add(usernameField);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setFont(new Font("SansSerif", Font.BOLD, 24));
        centerPanel.add(passwordLabel);

        passwordField = new JTextField(15);
        centerPanel.add(passwordField);

        createEnterButton = new JButton("Enter");
        centerPanel.add(createEnterButton);
        createEnterButton.addActionListener(actionListener);
        frame.add(centerPanel, BorderLayout.CENTER);

        exitGUI();

        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    /**
     * The frame and panel for the login screen
     */
    public void login() { //while loop until login is correct
                //using boolean login method from client
        frame = new JFrame("Login"); //new frame
        JPanel centerPanel = new JPanel(); //new JPanel Object for panel
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));

        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setFont(new Font("SansSerif", Font.BOLD, 24));
        centerPanel.add(usernameLabel);

        usernameField = new JTextField(15);
        centerPanel.add(usernameField);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setFont(new Font("SansSerif", Font.BOLD, 24));
        centerPanel.add(passwordLabel);

        passwordField = new JTextField(15);
        centerPanel.add(passwordField);

        loginEnterButton = new JButton("Enter");
        centerPanel.add(loginEnterButton);
        loginEnterButton.addActionListener(actionListener);
        frame.add(centerPanel, BorderLayout.CENTER);

        exitGUI();

        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    /**
     * The frame and panel for the welcome screen
     */
    public void welcome() {    
        try {
            client.connectToServer("localhost", 8000);
        } catch (IOException e) { 
            JOptionPane.showMessageDialog(null, "Unable To Connect To Server", 
                                            "Error", JOptionPane.ERROR_MESSAGE);
        }     
        frame = new JFrame("Welcome"); //new frame
        JPanel centerPanel = new JPanel(); //new JPanel Object for panel
        centerPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        loginButton = new JButton("Login");
        centerPanel.add(loginButton);
        loginButton.addActionListener(actionListener);

        createButton = new JButton("Create Account");
        centerPanel.add(createButton);
        createButton.addActionListener(actionListener);
        frame.add(centerPanel, BorderLayout.CENTER);

        exitGUI();

        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public void exitGUI() {
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                client.disconnectFromServer();
            }
        });
    }
}
