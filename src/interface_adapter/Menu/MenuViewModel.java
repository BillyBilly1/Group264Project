package interface_adapter.Menu;

import entity.Channel.Channel;
import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

public class MenuViewModel extends ViewModel {

    private ArrayList<Channel> channelList;

    private String userNickname;

    private String userID;




    public MenuViewModel() {
        super("menu");
        this.channelList = new ArrayList<Channel>();
    }


    public String getUserID() {
        return this.userID;
    }

    public String getUserNickname() {return this.userNickname;}

    public void setChannelList(Channel channel) {
        channelList.add(channel);

    }




    public List<String> getChannelNameList() {
        ArrayList<String> nameList = new ArrayList<String>();
        for (Channel channel : channelList) {
            nameList.add(channel.getChannelName());}
        return nameList;


        }

    @Override
    public void firePropertyChanged() {

    }

    @Override
    public void addPropertyChangedListener(PropertyChangeListener listener) {

    }

    public void setUserID(String userId) {

        this.userID = userId;
    }

    public void setUserNickname(String nickname) {
        this.userNickname = nickname;
    }
}
