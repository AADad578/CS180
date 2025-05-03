package GUI;

/**
 * Team Project Phase 3 -- GUI Interface
 *
 * This interface is for the GUI for the Marketplace (TO BE IMPLEMENTED IN PHASE 3)
 *
 * @author Vincent Holloway, lab sec 24
 *
 * @version May 3, 2025
 *
 */
public interface GUIInterface {
    // Method to display the welcome screen
    void welcome();

    // Method to display the login screen
    void login();

    // Method to display the create account screen
    void createAccount();

    // Method to display the default marketplace view screen
    void defaultView();

    // Method to display the user profile screen
    void user();

    //Method to display update user screen
    void updateUser();

    // Method to display the add item management screen
    void item();

    // Method to display the remove item management screen
    void removeItem();

    // Method to display the chat screen
    void chat();

    //Method to display create chat screen
    void createChat();

    //Method to display view chats screen
    void viewChats();

    //Method to display add message screen
    void addMessage();

    // Method to display the item search screen
    void search();

    //Method for exiting GUI
    void exitGUI();
}