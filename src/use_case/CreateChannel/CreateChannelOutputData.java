package use_case.CreateChannel;

public class CreateChannelOutputData {
    private final boolean success;
    private final String message;
    private final String channelId;

    public CreateChannelOutputData(boolean success, String message, String channelId) {
        this.success = success;
        this.message = message;
        this.channelId = channelId;
    }

    // Getters
    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public String getChannelId() {
        return channelId;
    }

    // Setters if necessary
    // ...
}
