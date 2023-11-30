package use_case.ListChannel;

import java.util.ArrayList;

public interface ListChannelDataAccessInterface {
    String get_username(String user_id);
    ArrayList<String> list_channel(String user_id);
}
