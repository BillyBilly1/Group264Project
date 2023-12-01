package use_case.CreateChannel;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CreateChannelInputData {
    final private String name;
    final private String channelUrl;
    final private String isEphemeral;
    final private List<String> operator;

    public CreateChannelInputData(String name, String channelUrl, List<String> operator,
                                  String isEphemeral){
        this.name = name;
        this.channelUrl = channelUrl;
        this.operator = operator;
        this.isEphemeral = isEphemeral;
    }

    public String getChannelName(){ return name; }
    List<String> getOperator(){ return operator; }
    String getChannelUrl() { return channelUrl; }
    public String getIsEphemeral() { return isEphemeral; }

    public boolean isFilled() {
        // Check that the name and channelUrl are not null or empty
        boolean isNameValid = name != null && !name.trim().isEmpty();
        boolean isChannelUrlValid = channelUrl != null && !channelUrl.trim().isEmpty();

        // Check that isEphemeral is not null and has a valid value if there's a specific format or value expected
        boolean isEphemeralValid = isEphemeral != null && (isEphemeral.equals("yes") || isEphemeral.equals("no"));

        // Check that the operator list is not null and not empty
        boolean isOperatorListValid = operator != null && !operator.isEmpty() && operator.stream().noneMatch(Objects::isNull);

        // The method returns true only if all checks are true
        return isNameValid && isChannelUrlValid && isEphemeralValid && isOperatorListValid;
    }

}

