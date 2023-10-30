package entity.Channel;

import java.time.LocalDateTime;
import java.util.ArrayList;

public interface Channel {

    String getChannelName();

    String getChannelUrl();

    String getNote();

    LocalDateTime getCreationTime();

    ArrayList<String> getOperator();

    boolean isEphemeral();

    void setNote(String content);

    void setOperator(ArrayList<String> addedOperator);

}