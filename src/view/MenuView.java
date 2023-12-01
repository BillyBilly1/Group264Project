package view;

import interface_adapter.Menu.MenuViewModel;
import interface_adapter.ViewManagerModel;
import interface_adapter.create_channel.CreateChannelState;
import interface_adapter.create_channel.CreateChannelViewModel;
import interface_adapter.list_Channel.ListChannelController;
import interface_adapter.list_Channel.ListChannelViewModel;

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

    private static final String viewName = "menu";
    private final MenuViewModel menuViewModel;

    private final ViewManagerModel viewManagerModel;

    private final ListChannelViewModel listChannelViewModel;

    private final ListChannelController listChannelController;

    private final CreateChannelViewModel createChannelViewModel;

    private JButton createChannelButton;
    private JList<String> channelList;

    public MenuView(ViewManagerModel viewManagerModel, MenuViewModel menuViewModel, ListChannelViewModel listChannelViewModel,
                    CreateChannelViewModel createChannelViewModel, ListChannelController listChannelController) {
        this.viewManagerModel = viewManagerModel;
        this.listChannelViewModel = listChannelViewModel;
        this.listChannelController = listChannelController;
        this.menuViewModel = menuViewModel;
        this.createChannelViewModel = createChannelViewModel;
        initializeUI();
        loadChannelList();
    }
  
    private void initializeUI() {
    setLayout(new BorderLayout());

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
                navigateToChannel(channelList.getModel().getElementAt(index));
            }
        }
    });
    JScrollPane scrollPane = new JScrollPane(channelList);
    add(scrollPane, BorderLayout.CENTER);
}


public void loadChannelList() {

    listChannelController.execute(menuViewModel.getUserID());
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
            CreateChannelState currentState= new CreateChannelState();
            List<String> opID = new ArrayList<String>();
            opID.add(menuViewModel.getUserID());
            currentState.setOperator(opID);
            createChannelViewModel.setState(currentState);


        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }


}



