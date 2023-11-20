package entity.Channel;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class CommonChannel implements Channel {

    private final String channelName;

    private final String channelUrl;

    private String note;

    private ArrayList<String> operator;

    private final String isEphemeral;

    private final LocalDateTime creationTime;

    public CommonChannel(String channelName, String channelUrl,
                         ArrayList<String> operator, String isEphemeral, LocalDateTime creationTime) {
        this.channelName = channelName;
        this.channelUrl = channelUrl;
        this.operator = operator;
        this.isEphemeral = isEphemeral;
        this.creationTime = creationTime;
    }

    public String getChannelName() {return channelName;}

    public String getChannelUrl() {return channelUrl;}

    public String getNote() {return note;}

    public LocalDateTime getCreationTime() {return creationTime;}

    public ArrayList<String> getOperator() {return operator;}

    public String isEphemeral() {return isEphemeral;}

    public void setNote(String content) {this.note = content;}

    public void setOperator(ArrayList<String> addedOperator) {
        this.operator.addAll(addedOperator);
    }






}