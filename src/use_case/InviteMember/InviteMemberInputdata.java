package use_case.InviteMember;

public class InviteMemberInputdata {
    final private String user_id;
    final private String channel_url;

    public InviteMemberInputdata(String user_id, String channel_url){
        this.user_id = user_id;
        this.channel_url = channel_url;
    }

    public String getUser_id(){return this.user_id;}
    public String getChannel_url(){return this.channel_url;}
}
