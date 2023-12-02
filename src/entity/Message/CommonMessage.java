package entity.Message;

public class CommonMessage implements Message {

    private String user_id;
    private String message;
    private String channel_type;
    private String channel_url;

    private long createAt;



    public CommonMessage(String userId, String message, String channelType, String channelUrl, long createAt) {
        this.user_id = userId;
        this.message = message;
        this.channel_type = channelType;
        this.channel_url = channelUrl;
        this.createAt = createAt;

    }

    public String getUser_id() {return user_id;};

    public String getMessage() {return message;};

    public String getChannel_type() {return channel_type;};

    public String getChannel_url() {return channel_url;};

    public long getCreateAt() {return getCreateAt();}
}


