package GUI;

import User.User;

public interface GUIInterface {

    /**
     * Opens a item listing page in full screen
     * 
     * @param item the Item to open
     */
    public void openItemListing(Item item);

    /**
     * Opens a search popup
     * 
     * @param searchTerm the term you are searching by
     */
    public void openSearch(String searchTerm);

    /**
     * Opens a chat popup
     * closes all other chats before running
     * 
     * @param chat the chat to open
     */
    public void openChat(Chat chat);

    /**
     * Closes all open chats
     */
    public void closeChat();

    /**
     * opens a user profile popup.
     * Closes all other open users before running
     * 
     * @param user the user to show
     */
    public void openUserProfile(User user);

    /**
     * close all open users
     */
    public void closeUserProfile();
}
