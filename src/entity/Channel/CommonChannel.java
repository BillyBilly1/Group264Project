package entity.Channel;

import java.time.LocalDateTime;
import java.util.List;

public class CommonChannel implements Channel {

    private final String channelName;

    private final String channelUrl;

    private String note;

    private List<String> operator;

    private List<String> user_ids;

    private final String isEphemeral;

    private final String is_distinct;


    public CommonChannel(List<String> user_ids, String channelName, String channelUrl,
                         List<String> operator, String is_distinct,String isEphemeral) {
        this.user_ids = user_ids;
        this.channelName = channelName;
        this.channelUrl = channelUrl;
        this.operator = operator;
        this.is_distinct = is_distinct;
        this.isEphemeral = isEphemeral;
    }

    public String getChannelName() {return channelName;}

    public String getChannelUrl() {return channelUrl;}

    public String getNote() {return note;}


    public List<String> getUser_ids() {return user_ids;}
    public List<String> getOperator() {return operator;}

    public String is_distinct() {return is_distinct;}

    public String isEphemeral() {return isEphemeral;}

    public void setNote(String content) {this.note = content;}

    public void setOperator(List<String> addedOperator) {
        this.operator.addAll(addedOperator);
    }



}