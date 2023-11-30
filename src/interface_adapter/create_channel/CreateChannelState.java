package interface_adapter.create_channel;

import java.util.ArrayList;

public class CreateChannelState {
    private String channelId = "";
    private String channelIdError = null;
    private String channelName = "";

    private ArrayList<String> operator = new ArrayList<>();

    private String isEphemeral = "true";
    private String channelNameError = null;
    private boolean isCreating = false;

    public CreateChannelState(CreateChannelState copy) {
        channelId = copy.channelId;
        channelIdError = copy.channelIdError;
        channelName = copy.channelName;
        channelNameError = copy.channelNameError;
        isCreating = copy.isCreating;
        operator = copy.operator;
        isEphemeral = copy.isEphemeral;
    }

    public CreateChannelState() {
    }

    // Getters
    public String getChannelId() {
        return channelId;
    }

    public ArrayList<String> getOperator(){
        return operator;
    }

    public String getChannelIdError() {
        return channelIdError;
    }

    public String getChannelName() {
        return channelName;
    }

    public String getChannelNameError() {
        return channelNameError;
    }

    public boolean isCreating() {
        return isCreating;
    }

    // Setters
    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    public void setOperator(ArrayList<String> operators){
        this.operator = operators;
    }

    public void setIsEphemeral(String isEphemeral) {
        this.isEphemeral = isEphemeral;
    }

    public void setChannelIdError(String channelIdError) {
        this.channelIdError = channelIdError;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    public void setChannelNameError(String channelNameError) {
        this.channelNameError = channelNameError;
    }

    public void setCreating(boolean isCreating) {
        this.isCreating = isCreating;
    }

    @Override
    public String toString() {
        return "CreateChannelState{" +
                "channelId='" + channelId + '\'' +
                ", channelName='" + channelName + '\'' +
                ", isCreating=" + isCreating +
                '}';
    }
}
