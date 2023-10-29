package entity;

public class Message {
    private final String user_id;
    private String message;
    private String channel_type;
    private String channel_url;
    private String data;
    private boolean send_push;


    public Message(String userId, String message, String channelType, String channelUrl, String data, boolean sendPush) {
        this.user_id = userId;
        this.message = message;
        this.channel_type = channelType;
        this.channel_url = channelUrl;
        this.data = data;
        this.send_push = sendPush;
    }

    public static MessageBuilder builder() { return new MessageBuilder();}

    public static class MessageBuilder {
        private String user_id;
        private String message;
        private String channel_type;
        private String channel_url;
        private String data;
        private boolean send_push;

        public MessageBuilder user_id(String id) {
            this.user_id = id;
            return this;
        }

        public MessageBuilder message(String message) {
            this.message = message;
            return this;
        }

        public MessageBuilder channelType(String channelType) {
            this.channel_type = channelType;
            return this;
        }

        public MessageBuilder channelUrl(String channelUrl) {
            this.channel_url = channelUrl;
            return this;
        }

        public MessageBuilder data(String data) {
            this.data = data;
            return this;
        }

        public MessageBuilder sendPush(Boolean sendPush) {
            this.send_push = sendPush;
            return this;
        }

        public Message build() {
            return new Message(user_id, message, channel_type, channel_url,
                    data, send_push);
        }
    }


    public String getUser_id() {return user_id;};

    public String getMessage() {return message;};

    public String getChannel_type() {return channel_type;};

    public  String getChannel_url() {return channel_url;};

    public String getData() {return data;};

    public Boolean getSendPush() {return send_push;};
}
