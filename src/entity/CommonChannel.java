package entity;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Channel implements  interface_Channel{

    private final String channelName;

    private final String channelUrl;

    private String note;

    private ArrayList<String> operator;

    private final boolean isEphemeral;

    private final LocalDateTime creationTime;

    public Channel(String channelName, String channelUrl,
                   ArrayList<String> operator, boolean isEphemeral, LocalDateTime creationTime) {
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

    public boolean isEphemeral() {return isEphemeral;}

    public void setNote(String content) {this.note = content;}

    public void setOperator(ArrayList<String> addedOperator) {
        this.operator.addAll(addedOperator);
    }






}