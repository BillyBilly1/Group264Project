package interface_adapter.Channel;
import entity.Channel.Channel;

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

    public void addMessage(String userInput, String sender, long ts) {
        if (ts > this.lastMessageTS) {
            String formattedMessage = formatMessage(userInput, sender, ts);
            sourceMessageList.add(formattedMessage);
            this.messageList = sourceMessageList;
            support.firePropertyChange("messagesUpdated", null, messageList);}

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
        SimpleDateFormat sdf = new SimpleDateFormat("  MM-dd  HH:mm");

        String formattedDate = sdf.format(date);
        // 获取当前时间
        // 格式化消息
        return sender + ": \n " + message + "  " +
                formattedDate;
    }


}
