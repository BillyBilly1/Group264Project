package interface_adapter.list_Channel;

public class ChannelInfo {
        private String channelUrl;
        private String name;

        public ChannelInfo(String channelUrl, String name) {
            this.channelUrl = channelUrl;
            this.name = name;
        }

        // Getters
        public String getChannelUrl() {
            return channelUrl;
        }

        public String getName() {
            return name;
        }
    }

