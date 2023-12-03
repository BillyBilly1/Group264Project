package interface_adapter.Menu;

import entity.Channel.Channel;
import interface_adapter.ViewModel;
import interface_adapter.list_Channel.ChannelInfo;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;

public class MenuViewModel extends ViewModel {

    private ArrayList<ChannelInfo> channelList;

    private PropertyChangeSupport support;



    private String userNickname;

    private String userID;




    public MenuViewModel() {
        super("menu");
        support = new PropertyChangeSupport(this);
        this.channelList = new ArrayList<ChannelInfo>();
    }


    public String getUserID() {
        return this.userID;
    }

    public String getUserNickname() {return this.userNickname;}

    public void initChannelList(ArrayList<ChannelInfo> channelInfos) {
        ArrayList<ChannelInfo> oldChannelList = this.channelList;
        this.channelList = channelInfos;
    }

    public void setChannelList(ChannelInfo channelInfo) {
        channelList.add(channelInfo);
        support.firePropertyChange("channelInfo", null, channelList);


    }


    public List<String> getChannelNameList() {
        ArrayList<String> nameList = new ArrayList<String>();
        for (ChannelInfo channelInfo : channelList) {
            nameList.add("<name>: " + channelInfo.getName() + "       <URL>: " + channelInfo.getChannelUrl());}
        return nameList;


        }

    @Override
    public void firePropertyChanged() {


    }

    @Override
    public void addPropertyChangedListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);


    }

    public void setUserID(String userId) {

        String oldUserID = this.userID;
        System.out.println(userId);
        this.userID = userId;

        support.firePropertyChange("userID", oldUserID, userId);
    }

    public void setUserNickname(String nickname) {
        this.userNickname = nickname;

    }


    public ChannelInfo findChannelByName(String name) {
        for (ChannelInfo channel : channelList) {
            if (channel.getName().equals(name)) {
                return channel;
            }
        }
        return null;
}}
