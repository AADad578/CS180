package GUI;

/**
 * Team Project Phase 2 -- GUI Interface
 *
 * This interface is for the GUI for the Marketplace (TO BE IMPLEMENTED IN PHASE 3)
 *
 * @author Vincent Holloway, lab sec 24
 *
 * @version April 20, 2025
 *
 */
public interface GUI_Interface {
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

    void updateUser();

    // Method to display the item management screen
    void item();

    // Method to display the chat screen
    void chat();

    void createChat();

    void viewChats();

    void addMessage();

    // Method to display the item search screen
    void search();

    void exitGUI();
}