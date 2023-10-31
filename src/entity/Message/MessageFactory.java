package Message;

public interface MessageFactory{
    Message create(String userId, String message, String channelType,
                   String channelUrl, String data, boolean sendPush);
}
