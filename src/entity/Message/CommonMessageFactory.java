package Message;

import Message.Message;
import Message.MessageFactory;
import Message.CommonMessage;

public class CommonMessageFactory implements MessageFactory{

    public Message create(String userId, String message, String channelType,
                          String channelUrl, String data, boolean sendPush){
        return new Message(userId, message, channelType, channelUrl, data, sendPush);
    }
}
