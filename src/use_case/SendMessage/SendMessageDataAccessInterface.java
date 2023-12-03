package use_case.SendMessage;

import entity.Message.Message;

import java.util.ArrayList;

public interface SendMessageDataAccessInterface {


    boolean sendMessage(String userId, String message, String channelType, String channelUrl);

    ArrayList<Message> getMessages(String channelType, String channelUrl, long messageTs);}
