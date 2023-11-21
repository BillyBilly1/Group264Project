package interface_adapter.Channel;
import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Date;


public class ChannelViewModel {

    private ArrayList<String> messageList = new ArrayList<>();


    public String getChannelName() {
        return "Group264";
    }

    public ArrayList<String> getMessages() {

        return messageList;

    }

    public void addMessage(String userInput) {
        String formattedMessage = formatMessage(userInput);
        messageList.add(formattedMessage);
    }

    private String formatMessage(String message) {
        // 获取当前时间
        String timeStamp = new SimpleDateFormat("HH:mm").format(new Date());
        // 格式化消息
        return "You" + ": \n " + message + "  " + timeStamp;
    }
}
