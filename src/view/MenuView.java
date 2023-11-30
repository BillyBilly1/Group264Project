package view;

import interface_adapter.Menu.MenuViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;

public class MenuView extends JPanel implements ActionListener, PropertyChangeListener {

    private static final String viewName = "menu";
    private final MenuViewModel menuViewModel;

    private JButton createChannelButton;
    private JList<String> channelList;

    public MenuView(MenuViewModel menuViewModel) {
        this.menuViewModel = menuViewModel;
        initializeUI();
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

    private void updateChannelList() {
        List<String> channels = menuViewModel.getChannelNameList("s"); //这里是一个overide method，仅用于测试。
        //实际使用的时候要替换掉里面的string
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
            System.out.println("Create channel button clicked");
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }




    }


