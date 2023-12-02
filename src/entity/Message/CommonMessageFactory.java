package entity.Message;

import entity.Message.Message;
import entity.Message.MessageFactory;
import entity.Message.CommonMessage;

public class CommonMessageFactory implements MessageFactory{

    public CommonMessage create(String userId, String message, String channelType,
                          String channelUrl, long createAt){
        return new CommonMessage(userId, message, channelType, channelUrl, createAt);
    }
}
