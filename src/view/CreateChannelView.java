package view;

import interface_adapter.Login.LoginViewModel;
import interface_adapter.create_channel.CreateChannelController;
import interface_adapter.create_channel.CreateChannelViewModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class CreateChannelView extends JPanel implements ActionListener, PropertyChangeListener {

    public final String viewName = "create channel";

    private final CreateChannelViewModel createChannelViewModel;

    private final CreateChannelController createChannelController;

    final JTextField channelIDINFO = new JTextField();

    final JTextField channelNameINFO = new JTextField();

    private final JButton createButton;

    public CreateChannelView(CreateChannelViewModel createChannelViewModel, CreateChannelController createChannelController) {
        this.createChannelViewModel = createChannelViewModel;
        this.createChannelController = createChannelController;

        JFrame frame = new JFrame("YouChat - Login");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 600);

        JButton createButton = new JButton(CreateChannelViewModel.CREATE_CHANNEL_BUTTON_LABLE);
        createButton.setBounds((400 / 2) - (100 / 2), 300, 100, 40);
        this.createButton = createButton;

        JPanel panel = new JPanel();
        frame.add(panel);
        placeComponents(panel);

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void placeComponents(JPanel panel) {

        panel.setLayout(null);

        JLabel titleLabel = new JLabel(CreateChannelViewModel.CREATE_CHANNEL_TITLE, SwingConstants.CENTER);
        titleLabel.setBounds(150, 100, 100, 30);
        panel.add(titleLabel);

        JLabel channelIDLabel = new JLabel(CreateChannelViewModel.CHANNEL_ID_LABEL);
        channelIDLabel.setBounds(10, 150, 150, 30);
        panel.add(channelIDLabel);

        JTextField channelID = channelIDINFO;
        channelID.setBounds(100, 150, 250, 40);
        panel.add(channelID);

        JLabel channelNameLabel = new JLabel(CreateChannelViewModel.CHANNEL_NAME_LABEL);
        channelNameLabel.setBounds(10, 200, 150, 30);
        panel.add(channelNameLabel);

        JTextField channelName = this.channelNameINFO;
        channelName.setBounds(100, 200, 250, 40);
        panel.add(channelName);


        panel.add(createButton);


        createButton.addActionListener(this);
    }




    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == createButton) {
            createChannelController.execute();
        }

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }
}
