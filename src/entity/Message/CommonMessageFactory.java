package entity.Message;

import entity.Message.Message;
import entity.Message.MessageFactory;
import entity.Message.CommonMessage;

public class CommonMessageFactory implements MessageFactory{

    public CommonMessage create(String userId, String message, String channelType,
                          String channelUrl, String data, boolean sendPush){
        return new CommonMessage(userId, message, channelType, channelUrl, data, sendPush);
    }
}
