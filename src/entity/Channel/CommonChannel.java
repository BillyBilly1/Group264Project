package entity.Channel;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class CommonChannel implements Channel {

    private final String channelName;

    private final String channelUrl;

    private String note;

    private List<String> operator;

    private List<String> user_ids;

    private final String isEphemeral;

    private final String is_distinct;


    public CommonChannel(String channelName, String channelUrl,
                         String is_distinct,String isEphemeral) {
        this.user_ids = new ArrayList<String>();
        this.channelName = channelName;
        this.channelUrl = channelUrl;
        this.operator = new ArrayList<String>();
        this.is_distinct = is_distinct;
        this.isEphemeral = isEphemeral;
    }

    public String getChannelName() {return channelName;}

    public String getChannelUrl() {return channelUrl;}

    public String getNote() {return note;}


    public List<String> getUser_ids() {return user_ids;}


    public List<String> getOperator() {return operator;}
    @Override
    public void setOperator(List<String> operator) {
        this.operator = operator;
    }

    @Override
    public void setUser_id(List<String> member) {
        this.user_ids = member;
    }

    public String is_distinct() {return is_distinct;}

    public String isEphemeral() {return isEphemeral;}

    public void setNote(String content) {this.note = content;}

}