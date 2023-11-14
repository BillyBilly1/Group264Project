package use_case.CreateChannel;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class CreateChannelInputData {

    private final String channelName;
    private final String channelUrl;
    private final ArrayList<String> admin;
    private final boolean isEphemeral;
    private final LocalDateTime creationTime;

    public CreateChannelInputData(String channelName, String channelUrl,
                                  ArrayList<String> admin, boolean isEphemeral,
                                  LocalDateTime creationTime) {
        this.channelName = channelName;
        this.channelUrl = channelUrl;
        this.admin = admin;
        this.isEphemeral = isEphemeral;
        this.creationTime = creationTime;
    }

    // Getter 方法
    public String getChannelName() {
        return channelName;
    }

    public String getChannelUrl() {
        return channelUrl;
    }

    public ArrayList<String> getAdmin() {
        return admin;
    }

    public boolean isEphemeral() {
        return isEphemeral;
    }

    public LocalDateTime getCreationTime() {
        return creationTime;
    }
}
