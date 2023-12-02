package use_case.CreateChannel;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CreateChannelInputData {
    final private List<String> user_ids;
    final private String name;
    final private String channelUrl;

    final private String is_distinct;
    final private String isEphemeral;
    final private List<String> operator;

    public CreateChannelInputData(List<String> user_ids, String name, String channelUrl, List<String> operator,
                                  String is_distinct,
                                  String isEphemeral){
        this.user_ids = user_ids;
        this.name = name;
        this.channelUrl = channelUrl;
        this.operator = operator;
        this.is_distinct = is_distinct;
        this.isEphemeral = isEphemeral;
    }

    List<String> getUser_ids() { return user_ids; }
    public String getChannelName(){ return name; }
    List<String> getOperator(){ return operator; }
    String getChannelUrl() { return channelUrl; }

    public String getIs_distinct() { return is_distinct; }
    public String getIsEphemeral() { return isEphemeral; }


}

