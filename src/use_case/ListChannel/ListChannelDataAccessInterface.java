package use_case.ListChannel;

import org.json.JSONArray;

public interface ListChannelDataAccessInterface {
    String get_username(String user_id);
    JSONArray list_channel(String user_id);
}
