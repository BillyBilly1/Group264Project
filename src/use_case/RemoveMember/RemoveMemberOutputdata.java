package use_case.RemoveMember;

public class RemoveMemberOutputdata {
    private final String user_id;
    private final String channel_url;

    public RemoveMemberOutputdata(String user_id, String channel_url) {
        this.user_id = user_id;
        this.channel_url = channel_url;
    }

    public String getUser_id() {
        return this.user_id;
    }

    public String getChannel_url() {
        return this.channel_url;
    }
}
