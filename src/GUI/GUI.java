package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
/**
 * Team Project Phase 2 -- GUI Class
 *
 * This class is the GUI for the Marketplace (TO BE IMPLEMENTED IN PHASE 3)
 *
 * @author Vincent Holloway, lab sec 24
 *
 * @version April 20, 2025
 *
 */

//FOR PHASE 2: ONLY LAYOUT OF GUI HAS BEEN CREATED, REST TO BE IMPLEMENTED IN PHASE 3

public class GUI implements Runnable, GUIInterface {
    private JFrame frame; //frame of GUI

    private JButton loginButton; //login button on welcome screen
    private JButton createButton; //create account button on welcome screen
    private JButton enterButton; //enter button for both login and create screens

    private JButton searchButton; //search button for item search
    private JButton chatButton; //chat button that leads to chat screen
    private JButton itemButton; //item button that leads to item screen
    private JButton balanceButton; //balance button that leads to balance screen
    private JButton userButton; //view user profile button that leads to user profile

    private JButton createChatButton; //creates chat button in chat screen
    private JButton viewExistingChatsButton; //views chats button in chat screen
    private JButton removeChatButton; //removes chat button in chat screen

    private JButton addItemButton; //creates item button in item screen
    private JButton viewItemsButton; //views items button in item screen
    private JButton removeItemButton; //removes item button in item screen

    private JButton balanceEnterButton; //enter balance button in balance screen

    private JButton exitButton; //back button in the chat, search, balance,
                                //item, view user profile screens

    private JTextField usernameField; //text field for username
    private JTextField passwordField; //text field for password
    private JTextField nameField; //text field for frist and last name

    private String username; //username of user
    private String password; //password of user

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
            if (e.getSource() == enterButton) {
                username = usernameField.getText();
                password = passwordField.getText();
                frame.dispose();
                GUI.this.defaultView();
            }
            if (e.getSource() == searchButton) {
                frame.dispose();
                GUI.this.search();
            }
            if (e.getSource() == chatButton) {
                frame.dispose();
                GUI.this.chat();
            }
            if (e.getSource() == itemButton) {
                frame.dispose();
                GUI.this.item();
            }
            if (e.getSource() == balanceButton) {
                frame.dispose();
                GUI.this.balance();
            }
            if (e.getSource() == userButton) {
                frame.dispose();
                GUI.this.user();
            }
            if (e.getSource() == exitButton) {
                frame.dispose();
                GUI.this.defaultView();
            }
        }
    };

    /**
     * The frame and panel for the view user screen
     */
    public void user() {
        frame = new JFrame("User Profile"); //new frame
        Container content = frame.getContentPane();
        content.setLayout(new BorderLayout());

        //show user profile

        JPanel bottomPanel = new JPanel(); //bottom panel
        exitButton = new JButton("Back");
        bottomPanel.add(exitButton);
        exitButton.addActionListener(actionListener);
        content.add(bottomPanel, BorderLayout.SOUTH);

        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    /**
     * The frame and panel for the balance screen
     */
    public void balance() {
        frame = new JFrame("Balance"); //new frame
        Container content = frame.getContentPane();
        content.setLayout(new BorderLayout());

        JPanel panel = new JPanel(); //panel
        panel.setLayout(new GridLayout(0, 1));

        JTextField balanceField = new JTextField(10); //field for enter balance
        //show current balance
        balanceField.setText("balance");
        panel.add(balanceField);

        balanceEnterButton = new JButton("Confirm"); //enter balance button
        panel.add(balanceEnterButton);
        balanceEnterButton.addActionListener(actionListener);
        content.add(panel, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel(); //bottom panel
        exitButton = new JButton("Back");
        bottomPanel.add(exitButton);
        exitButton.addActionListener(actionListener);
        content.add(bottomPanel, BorderLayout.SOUTH);

        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    /**
     * The frame and panel for the item screen
     */
    public void item() {
        frame = new JFrame("Item"); //new frame
        Container content = frame.getContentPane();
        content.setLayout(new BorderLayout());

        JPanel panel = new JPanel(); //panel
        panel.setLayout(new GridLayout(3, 0));

        addItemButton = new JButton("Add Item");
        panel.add(addItemButton);
        addItemButton.addActionListener(actionListener);

        viewItemsButton = new JButton("View Items");
        panel.add(viewItemsButton);
        viewItemsButton.addActionListener(actionListener);

        removeItemButton = new JButton("Remove Item");
        panel.add(removeItemButton);
        removeItemButton.addActionListener(actionListener);
        content.add(panel, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel(); //bottom panel
        exitButton = new JButton("Back");
        bottomPanel.add(exitButton);
        exitButton.addActionListener(actionListener);
        content.add(bottomPanel, BorderLayout.SOUTH);

        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(800, 600);
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
        panel.setLayout(new GridLayout(3, 0));

        createChatButton = new JButton("Create Chat");
        panel.add(createChatButton);
        createChatButton.addActionListener(actionListener);

        viewExistingChatsButton = new JButton("View Existing Chats");
        panel.add(viewExistingChatsButton);
        viewExistingChatsButton.addActionListener(actionListener);

        removeChatButton = new JButton("Remove Chat");
        panel.add(removeChatButton);
        removeChatButton.addActionListener(actionListener);
        content.add(panel, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel(); //bottom panel
        exitButton = new JButton("Back");
        bottomPanel.add(exitButton);
        exitButton.addActionListener(actionListener);
        content.add(bottomPanel, BorderLayout.SOUTH);

        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    /**
     * The frame and panel for the search screen
     */
    public void search() { //if item search is valid
        frame = new JFrame("Search"); //new frame
        Container content = frame.getContentPane(); //content pane of frame
        content.setLayout(new BorderLayout());

        //show items

        JPanel bottomPanel = new JPanel(); //bottom panel
        exitButton = new JButton("Back");
        bottomPanel.add(exitButton);
        exitButton.addActionListener(actionListener);
        content.add(bottomPanel, BorderLayout.SOUTH);

        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    /**
     * The frame and panel for the default view screen
     */
    public void defaultView() {
        frame = new JFrame("Market Place"); //new frame
        Container content = frame.getContentPane();
        content.setLayout(new BorderLayout());

        JPanel topPanel = new JPanel(); //top panel
        JTextField itemSearch = new JTextField(30);
        itemSearch.setText("Search");
        topPanel.add(itemSearch);

        searchButton = new JButton("Enter");
        topPanel.add(searchButton);
        searchButton.addActionListener(actionListener);
        content.add(topPanel, BorderLayout.NORTH);

        JPanel panel = new JPanel(); //panel
        panel.setLayout(new GridLayout(4, 0));

        chatButton = new JButton("Chat");
        panel.add(chatButton);
        chatButton.addActionListener(actionListener);

        itemButton = new JButton("Item");
        panel.add(itemButton);
        itemButton.addActionListener(actionListener);

        balanceButton = new JButton("Balance");
        panel.add(balanceButton);
        balanceButton.addActionListener(actionListener);

        userButton = new JButton("View User Profile");
        panel.add(userButton);
        userButton.addActionListener(actionListener);
        content.add(panel, BorderLayout.CENTER);

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

        nameField = new JTextField(15);
        nameField.setText("Enter First and Last Name");
        centerPanel.add(nameField);

        usernameField = new JTextField(15);
        usernameField.setText("Enter Username");
        centerPanel.add(usernameField);

        passwordField = new JTextField(15);
        passwordField.setText("Enter Password");
        centerPanel.add(passwordField);

        enterButton = new JButton("Enter");
        centerPanel.add(enterButton);
        enterButton.addActionListener(actionListener);
        frame.add(centerPanel, BorderLayout.CENTER);

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

        usernameField = new JTextField(15);
        usernameField.setText("username");
        centerPanel.add(usernameField);

        passwordField = new JTextField(15);
        passwordField.setText("password");
        centerPanel.add(passwordField);

        enterButton = new JButton("Enter");
        centerPanel.add(enterButton);
        enterButton.addActionListener(actionListener);
        frame.add(centerPanel, BorderLayout.CENTER);

        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    /**
     * The frame and panel for the welcome screen
     */
    public void welcome() {
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

        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
