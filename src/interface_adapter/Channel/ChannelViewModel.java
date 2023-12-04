package interface_adapter.Channel;
import app.Sounds;
import entity.Channel.Channel;

import java.awt.*;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Date;


public class ChannelViewModel {


    private final PropertyChangeSupport support;

    private String myID;
    private  ArrayList<String> sourceMessageList = new ArrayList<>();

    private Channel channel;

    private ArrayList<String> messageList = sourceMessageList;

    private long lastMessageTS = 0;


    public ChannelViewModel() {this.support = new PropertyChangeSupport(this);
    }


    public String getChannelName() {
        return this.channel.getChannelName();
    }

    public Channel getChannel() {
        return this.channel;
    }

    public long getLastMessageTS() {return this.lastMessageTS;}

    public void setLastMessageTS(long lastMessageTS) {this.lastMessageTS = lastMessageTS;}


    public ArrayList<String> getMessages() {

        return messageList;
    }

    public ArrayList<String> getSourceMessageList() {
        return this.sourceMessageList;
    }

    public ArrayList<String> searchMessages(String query) {
        ArrayList<String> searchResults = new ArrayList<>();
        for (String omessage : sourceMessageList) {
            String message = omessage.substring(0, omessage.length() - 14);
            if (message.contains(query)) {
                // Extract the message content without the sender and timestamp
                String[] parts = message.split("  ");  // Assuming the message format has two spaces before the timestamp
                if (parts.length > 1) {
                    searchResults.add(omessage);
                }
            }
        }
        return searchResults;
    }
    public ArrayList<String> searchMessagesByDate(int year, int month, int day) {
        ArrayList<String> searchResults = new ArrayList<>();
        String searchDate = String.format("%02d-%02d-%02d", year % 100, month, day); // Format date as yy-MM-dd

        for (String message : sourceMessageList) {
            int length = message.length();
            // Check if the message has enough length to contain a date in the specified format
            if (length > 15) {
                // Extract the date part from the message
                String datePart = message.substring(length - 15, length - 7); // Extracting date from yy-MM-dd

                if (datePart.equals(searchDate)) {
                    searchResults.add(message);
                }
            }
        }
        return searchResults;
    }







    public void updateTranslatedText(ArrayList<String> translatedText) {
        this.messageList = translatedText;
        support.firePropertyChange("messagesUpdated", null, messageList);
    }

    public void addPropertyChangeListener(PropertyChangeListener pcl) {
        support.addPropertyChangeListener(pcl);
    }


    public void deTranslate() {
        this.messageList = sourceMessageList;

    }

    public void addMessage(String userInput, String sender, long ts) throws InterruptedException {
        if (ts > this.lastMessageTS) {
            String formattedMessage = formatMessage(userInput, sender, ts);
            sourceMessageList.add(formattedMessage);
            this.messageList = sourceMessageList;
            support.firePropertyChange("messagesUpdated", null, messageList);
            Sounds.playTone();
        }


    }

    public void setChannel(Channel channel) {
        this.channel = channel;

    }


    public void setMyID(String myID){
        this.myID = myID;
    }

    public String getMyID() {

        return this.myID;
    }

    private String formatMessage(String message, String sender, long ts) {

        Date date = new Date(ts);
        SimpleDateFormat sdf = new SimpleDateFormat("  yy-MM-dd  HH:mm");

        String formattedDate = sdf.format(date);
        // 获取当前时间
        // 格式化消息
        return sender + ": \n " + message + "  " +
                formattedDate;
    }

    public void reset() {this.sourceMessageList = new ArrayList<>();}


}
