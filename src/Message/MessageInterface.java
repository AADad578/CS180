public interface MessageInterface {
    void sendMessage();
    String viewMessages();
    String toString();
    String getMessageContent();
    int getTimeSent();
    User getReceiver();
    User getSender();
    void setMessageContent(String messageContent);
    void setTimeSent(int timeSent);
    void setSender(User sender);
    void setReceiver(User receiver);
}
