package entity.Message;

public interface MessageFactory{
    CommonMessage create(String userId, String message, String channelType,
                   String channelUrl, String data, boolean sendPush);
}
