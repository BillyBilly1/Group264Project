package entity.Message;

public class CommonMessage implements Message {

    private String user_id;
    private String message;
    private String channel_type;
    private String channel_url;
    private String data;
    private boolean send_push;


    public CommonMessage(String userId, String message, String channelType, String channelUrl, String data, boolean sendPush) {
        this.user_id = userId;
        this.message = message;
        this.channel_type = channelType;
        this.channel_url = channelUrl;
        this.data = data;
        this.send_push = sendPush;
    }

    public static CommonMessageBuilder builder() { return new CommonMessageBuilder();}

    public static class CommonMessageBuilder {
        private String user_id;
        private String message;
        private String channel_type;
        private String channel_url;
        private String data;
        private boolean send_push;

        public CommonMessageBuilder user_id(String id) {
            this.user_id = id;
            return this;
        }

        public CommonMessageBuilder message(String message) {
            this.message = message;
            return this;
        }

        public CommonMessageBuilder channelType(String channelType) {
            this.channel_type = channelType;
            return this;
        }

        public CommonMessageBuilder channelUrl(String channelUrl) {
            this.channel_url = channelUrl;
            return this;
        }

        public CommonMessageBuilder data(String data) {
            this.data = data;
            return this;
        }

        public CommonMessageBuilder sendPush(Boolean sendPush) {
            this.send_push = sendPush;
            return this;
        }

        public CommonMessage build() {
            return new CommonMessage(user_id, message, channel_type, channel_url,
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


