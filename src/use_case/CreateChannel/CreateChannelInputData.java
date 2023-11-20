package use_case.CreateChannel;

import java.util.ArrayList;

public class CreateChannelInputData {
    final private String name;
    final private String channelUrl;
    final private String isEphemeral;
    final private ArrayList<String> operator;

    public CreateChannelInputData(String name, String channelUrl, ArrayList<String> operator,
                                  String isEphemeral) {
        this.name = name;
        this.channelUrl = channelUrl;
        this.operator = operator;
        this.isEphemeral = isEphemeral;
    }

    public String getChannelName() {
        return name;
    }

    ArrayList<String> getOperator() {
        return operator;
    }

    String getChannelUrl() {
        return channelUrl;
    }

    public String getIsEphemeral() {
        return isEphemeral;
    }
}

