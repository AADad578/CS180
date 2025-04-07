package GUI;

import Chat.Chat;
import Item.Item;
import User.User;

/**
 * GUI
 * 
 * handles all the GUI tasks for the client
 * 
 * @version 4/6/2025
 * 
 * @author Ankur Raghavan
 */
public class GUI implements GUIInterface {

    @Override
    public void openItemListing(Item item) {
        throw new UnsupportedOperationException("Unimplemented method 'openItemListing'");
    }

    @Override
    public void openSearch(String searchTerm) {
        throw new UnsupportedOperationException("Unimplemented method 'openSearch'");
    }

    @Override
    public void openChat(Chat chat) {
        throw new UnsupportedOperationException("Unimplemented method 'openChat'");
    }

    @Override
    public void closeChat() {
        throw new UnsupportedOperationException("Unimplemented method 'closeChat'");
    }

    @Override
    public void openUserProfile(User user) {
        throw new UnsupportedOperationException("Unimplemented method 'openUserProfile'");
    }

    @Override
    public void closeUserProfile() {
        throw new UnsupportedOperationException("Unimplemented method 'closeUserProfile'");
    }

}
