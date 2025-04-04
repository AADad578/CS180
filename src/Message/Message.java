import java.io.*;
import java.util.ArrayList;

public class Message {
    private String messageContent;
    private int timeSent;
    private User sender;
    private User receiver;

    public Message(String messageContent, int timeSent, User sender, User receiver) {
        this.messageContent = messageContent;
        this.timeSent = timeSent;
        this.sender = sender;
        this.receiver = receiver;
    }

    public void sendMessage() {
        try (PrintWriter pw = new PrintWriter(new
                FileOutputStream("messagelog.txt", true))) {
            pw.write(toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String viewMessages() {
        String messages = String.format("Here's your messages:\n");
        ArrayList<String> lineList = new ArrayList<String>();
        try (BufferedReader bfr = new BufferedReader(new FileReader("messagelog.txt"))) {
            String line = bfr.readLine();
            while(line != null) {
                if (line.substring(4, receiver.getUserName().length() + 4).equals(
                        receiver.getUserName())) {
                    messages += String.format("%s\n", line);
                    for (int i = 0; i < 3; i++) {
                        line = bfr.readLine();
                        messages += String.format("%s\n", line);
                    }
                } else {
                    lineList.add(line);
                }
                line = bfr.readLine();
            }
            PrintWriter pw = new PrintWriter(new FileOutputStream("messagelog.txt"));
            for (String str : lineList) {
                pw.println(str);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return messages;
    }

    public String toString() {
        return String.format("To: %s\nMessage: %s\nFrom: %s\nTime: %d\n",
                receiver.getUserName(), messageContent, sender.getUserName(), timeSent);
    }

    public String getMessageContent() {
        return messageContent;
    }

    public int getTimeSent() {
        return timeSent;
    }

    public User getReceiver() {
        return receiver;
    }

    public User getSender() {
        return sender;
    }

    public void setMessageContent(String messageContent) {
        this.messageContent = messageContent;
    }

    public void setTimeSent(int timeSent) {
        this.timeSent = timeSent;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public void setReceiver(User receiver) {
        this.receiver = receiver;
    }
}
