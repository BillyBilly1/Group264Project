package Message;

public class CommonMessageFactory implements MessageFactory{
    @Override
    public Message create(String userId, String message, String channelType,
                          String channelUrl, String data, boolean sendPush){
        return new Message(userId, message, channelType, channelUrl, data, sendPush);
    }
}
