package entity.Channel;

import java.time.LocalDateTime;
import java.util.List;

public interface Channel {

    List<String> getUser_ids();

    String is_distinct();

    String getChannelName();

    String getChannelUrl();

    String getNote();


    List<String> getOperator();

    String isEphemeral();

    void setNote(String content);

    void setOperator(List<String> addedOperator);



}
