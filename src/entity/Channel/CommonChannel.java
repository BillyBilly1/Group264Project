package entity.Channel;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class CommonChannel implements Channel {

    private final String channelName;

    private final String channelUrl;

    private String note;

    private ArrayList<String> admin;

    private final boolean isEphemeral;

    private final LocalDateTime creationTime;

    public CommonChannel(String channelName, String channelUrl,
                         ArrayList<String> admin, boolean isEphemeral, LocalDateTime creationTime) {
        this.channelName = channelName;
        this.channelUrl = channelUrl;
        this.admin = admin;
        this.isEphemeral = isEphemeral;
        this.creationTime = creationTime;
    }

    public String getChannelName() {return channelName;}

    public String getChannelUrl() {return channelUrl;}

    public String getNote() {return note;}

    public LocalDateTime getCreationTime() {return creationTime;}

    public ArrayList<String> getAdmin() {return admin;}

    public boolean isEphemeral() {return isEphemeral;}

    public void setNote(String content) {this.note = content;}

    public void setAdmin(ArrayList<String> addedAdmin) {
        this.admin.addAll(addedAdmin);
    }

}