package view;

import interface_adapter.Menu.MenuViewModel;
import interface_adapter.ViewManagerModel;
import interface_adapter.ViewProfile.ViewProfileState;
import interface_adapter.create_channel.CreateChannelState;
import interface_adapter.create_channel.CreateChannelViewModel;
import interface_adapter.list_Channel.ChannelInfo;
import interface_adapter.list_Channel.ListChannelController;
import interface_adapter.list_Channel.ListChannelViewModel;
import interface_adapter.ViewProfile.ViewProfileViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

public class MenuView extends JPanel implements ActionListener, PropertyChangeListener {

    private final MenuViewModel menuViewModel;
    private final ViewManagerModel viewManagerModel;
    private final ListChannelViewModel listChannelViewModel;
    private final ListChannelController listChannelController;
    private final CreateChannelViewModel createChannelViewModel;
    private final ViewProfileViewModel viewProfileViewModel;
    private JButton createChannelButton;
    private JButton profileButton;
    private JList<String> channelList;

    public MenuView(ViewManagerModel viewManagerModel, MenuViewModel menuViewModel,
                    ListChannelViewModel listChannelViewModel, CreateChannelViewModel createChannelViewModel,
                    ListChannelController listChannelController, ViewProfileViewModel viewProfileViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.menuViewModel = menuViewModel;
        this.listChannelViewModel = listChannelViewModel;
        this.listChannelController = listChannelController;
        this.createChannelViewModel = createChannelViewModel;
        this.viewProfileViewModel = viewProfileViewModel;
        menuViewModel.addPropertyChangedListener(this);
        createChannelViewModel.addPropertyChangeListener(this);
        initializeUI();
        loadChannelList();
        updateChannelList();
    }

    private void initializeUI() {
        setLayout(new BorderLayout());
        profileButton = new JButton("View Profile");
        profileButton.addActionListener(this);
        add(profileButton, BorderLayout.SOUTH);
        createChannelButton = new JButton("Create Channel");
        createChannelButton.addActionListener(this);
        add(createChannelButton, BorderLayout.NORTH);
        channelList = new JList<>();
        updateChannelList();
        channelList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        channelList.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                if (evt.getClickCount() == 2) {
                    int index = channelList.locationToIndex(evt.getPoint());
                    String selectedItem = channelList.getModel().getElementAt(index);
                    String channelUrl = extractChannelUrl(selectedItem);
                    navigateToChannel(channelUrl);
                }
            }
        });
        channelList.setFont(new Font("channelList", Font.BOLD, 15));
        JScrollPane scrollPane = new JScrollPane(channelList);
        add(scrollPane, BorderLayout.CENTER);
    }

    private String extractChannelUrl(String listItem) {
        int urlStart = listItem.indexOf("<URL>: ") + "<URL>: ".length();
        return listItem.substring(urlStart);
    }


    public void loadChannelList() {
        listChannelController.execute(menuViewModel.getUserID());
        ArrayList<ChannelInfo> channelInfoList = listChannelViewModel.getListChannelState().getChannels();
        menuViewModel.initChannelList(channelInfoList);
        updateChannelList();
    }

    private void updateChannelList() {
        List<String> channels = menuViewModel.getChannelNameList();
        if (channels == null) {
            channels = new ArrayList<>();
        }
        DefaultListModel<String> model = new DefaultListModel<>();
        for (String channel : channels) {
            model.addElement(channel);
        }
        channelList.setModel(model);
    }

    private void navigateToChannel(String channelName) {
        System.out.println("Navigate to channel: " + channelName);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == createChannelButton) {
            CreateChannelState currentState = new CreateChannelState();
            List<String> opID = new ArrayList<>();
            opID.add(menuViewModel.getUserID());
            currentState.setOperator(opID);
            currentState.setUser_ids(opID);
            createChannelViewModel.setState(currentState);
            viewManagerModel.setActiveView(createChannelViewModel.getViewName());
            viewManagerModel.firePropertyChanged();
        } else if (e.getSource() == profileButton) {
            ViewProfileState currentState = viewProfileViewModel.getState();
            currentState.setUser_id(menuViewModel.getUserID());
            currentState.setNickname(menuViewModel.getUserNickname());
            viewProfileViewModel.setState(currentState);
            viewManagerModel.setActiveView("profile");
            viewManagerModel.firePropertyChanged();
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent
                                       evt) {

        if ("userID".equals(evt.getPropertyName()) || "channelInfo".equals(evt.getPropertyName())) {
            loadChannelList();
            updateChannelList();
        }

        }




    }




