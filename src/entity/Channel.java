package entity;

import java.time.LocalDateTime;
import java.util.ArrayList;

public interface interface_Channel {

    String getChannelName();

    String getChannelUrl();

    String getNote();

    LocalDateTime getCreationTime();

    ArrayList<String> getOperator();

    boolean isEphemeral();

    void setNote(String content);

    void setOperator(ArrayList<String> addedOperator);

}
