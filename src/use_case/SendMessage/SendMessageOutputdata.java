package use_case.SendMessage;

import entity.Message.Message;

import java.util.ArrayList;

public class SendMessageOutputdata {

    private ArrayList<Message> messageList;
    public SendMessageOutputdata(ArrayList<Message> messages) {
        this.messageList = messages;
    }

    public ArrayList<Message> getMessageList() {
        return messageList;
    }
}
