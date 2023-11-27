package interface_adapter.Channel;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Date;


public class ChannelViewModel {


    private final PropertyChangeSupport support;
    private  ArrayList<String> sourceMessageList = new ArrayList<>();

    private ArrayList<String> messageList = sourceMessageList;

    public ChannelViewModel() {this.support = new PropertyChangeSupport(this);
    }


    public String getChannelName() {
        return "Group264";
    }

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

    public void addMessage(String userInput) {
        String formattedMessage = formatMessage(userInput);
        sourceMessageList.add(formattedMessage);
        this.messageList = sourceMessageList;
        support.firePropertyChange("messagesUpdated", null, messageList);

    }

    private String formatMessage(String message) {
        // 获取当前时间
        String timeStamp = new SimpleDateFormat("HH:mm").format(new Date());
        // 格式化消息
        return "You" + ": \n " + message + "  " + timeStamp;
    }
}
