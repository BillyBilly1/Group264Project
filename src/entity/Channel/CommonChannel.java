package entity.Channel;

import java.time.LocalDateTime;
import java.util.List;

public class CommonChannel implements Channel {

    private final String channelName;

    private final String channelUrl;

    private String note;

    private List<String> operator;

    private final String isEphemeral;


    public CommonChannel(String channelName, String channelUrl,
                         List<String> operator, String isEphemeral) {
        this.channelName = channelName;
        this.channelUrl = channelUrl;
        this.operator = operator;
        this.isEphemeral = isEphemeral;
    }

    public String getChannelName() {return channelName;}

    public String getChannelUrl() {return channelUrl;}

    public String getNote() {return note;}


    public List<String> getOperator() {return operator;}

    public String isEphemeral() {return isEphemeral;}

    public void setNote(String content) {this.note = content;}

    public void setOperator(List<String> addedOperator) {
        this.operator.addAll(addedOperator);
    }






}