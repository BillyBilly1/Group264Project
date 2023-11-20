package interface_adapter.Channel;

import java.util.ArrayList;

public class ChannelViewModel {


    public String getChannelName() {
        return "Group264";
    }

    public ArrayList<String> getMessages() {
        ArrayList<String> messages = new ArrayList<>();
        messages.add("宋江: \n Hello!  19:42");
        messages.add("卢俊义: \n Good Evening!  19:43");
        messages.add("吴用: \n Bye!  19:43");
        messages.add("鲁智深: \n See you later!  19:44");
        messages.add("宋江: \n LOL!  19:45");
        messages.add("武松: \n Fare thee Well.  20:10");

        return messages;

    }

    public void addMessage(String text) {
    }
}
