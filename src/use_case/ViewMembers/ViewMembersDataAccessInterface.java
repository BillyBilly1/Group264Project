package use_case.ViewMembers;

import java.util.List;

public interface ViewMembersDataAccessInterface {

    List<String> viewMembers(String channel_url);
}
