package interface_adapter.Channel;
import app.Sounds;
import entity.Channel.Channel;

import java.awt.*;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.regex.Pattern;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;


public class ChannelViewModel {


    private final PropertyChangeSupport support;

    private String myID;
    private  ArrayList<String> sourceMessageList = new ArrayList<>();

    private Channel channel;

    private ArrayList<String> messageList = sourceMessageList;

    private long lastMessageTS = 0;

    private String selectedMessage;

    private boolean mute = false;


    public ChannelViewModel() {this.support = new PropertyChangeSupport(this);
    }

    public void setmute() {this.mute = !this.mute;}


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
        String lowerCaseQuery = query.toLowerCase();

        for (String omessage : sourceMessageList) {
            // 移除时间戳，时间戳是最后15个字符
            String messageContent = omessage.substring(0, omessage.length() - 15);

            if (messageContent.toLowerCase().contains(lowerCaseQuery)) {
                // 在消息内容中高亮显示查询关键字
                String highlightedContent = messageContent.replaceAll("(?i)" + Pattern.quote(query),
                        "<span style='color:orange;'>" + query + "</span>");

                // 重新拼接消息和时间戳
                searchResults.add(highlightedContent + omessage.substring(omessage.length() - 15));
            }
        }

        return searchResults;
    }


    public ArrayList<String> searchMessagesByDate(int year, int month, int day, int hour) {
        ArrayList<String> searchResults = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yy-MM-dd  HH:mm");

        LocalDateTime startDateTime = LocalDateTime.of(year, month, day, hour, 0).minusHours(1); // 开始时间：向前一个小时
        LocalDateTime endDateTime = LocalDateTime.of(year, month, day, hour, 0).plusHours(1); // 结束时间：加一个小时

        for (String message : sourceMessageList) {
            try {
                String dateTimePart = message.substring(message.length() - 15).trim();
                LocalDateTime messageDateTime = LocalDateTime.parse(dateTimePart, formatter);

                if (!messageDateTime.isBefore(startDateTime) && !messageDateTime.isAfter(endDateTime)) {
                    searchResults.add(message);
                }
            } catch (Exception e) {
                // 解析错误处理，可以根据需要记录日志或者忽略
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
            if (!this.mute){
                Sounds.playTone();}
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


    public void setSelectedMessage(String selectedMessage) {
        this.selectedMessage = selectedMessage;
    }

    public String getSelectedMessage() {
        return selectedMessage;
    }
}
