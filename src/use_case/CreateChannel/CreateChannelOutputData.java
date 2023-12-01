package use_case.CreateChannel;

public class CreateChannelOutputData {
    private final boolean success;
    private final String message;
    private final String channelName;

    public CreateChannelOutputData(String channelName, String message, boolean success) {
        this.success = success;
        this.message = message;
        this.channelName = channelName;
    }

    // Getters
    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public String getChannelName() {
        return channelName;
    }
}
