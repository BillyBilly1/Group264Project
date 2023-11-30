package view;

import interface_adapter.Signup.SignupState;
import interface_adapter.create_channel.CreateChannelController;
import interface_adapter.create_channel.CreateChannelState;
import interface_adapter.create_channel.CreateChannelViewModel;


import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;

public class CreateChannelView extends JPanel implements ActionListener, PropertyChangeListener{

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {

            JFrame frame = new JFrame("YouChat - Create Channel");


            CreateChannelView view = new CreateChannelView(null, null);

            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(600, 400);
            frame.add(view);
            frame.setLocationRelativeTo(null);

            // Display the frame
            frame.setVisible(true);
        });
    }


    public final String viewName = "create channel";

    private final CreateChannelViewModel createChannelViewModel;
    private final CreateChannelController createChannelController;
    final JTextField channelIDINFO = new JTextField();
    final JTextField channelNameINFO = new JTextField();
    final JTextField channelOperatorINFO = new JTextField();
    final String channelIsEphemeral = "true";
    private final JButton createButton;

    public CreateChannelView(CreateChannelViewModel createChannelViewModel, CreateChannelController createChannelController) {
        this.createChannelViewModel = createChannelViewModel;
        this.createChannelController = createChannelController;

        this.setLayout(null);

        JLabel titleLabel = new JLabel(CreateChannelViewModel.CREATE_CHANNEL_TITLE, SwingConstants.CENTER);
        titleLabel.setBounds(100, 20, 200, 30);
        this.add(titleLabel);

        JLabel channelIDLabel = new JLabel(CreateChannelViewModel.CHANNEL_ID_LABEL);
        channelIDLabel.setBounds(10, 70, 180, 25);
        this.add(channelIDLabel);

        channelIDINFO.setBounds(200, 70, 275, 25);
        this.add(channelIDINFO);

        JLabel channelNameLabel = new JLabel(CreateChannelViewModel.CHANNEL_NAME_LABEL);
        channelNameLabel.setBounds(10, 120, 180, 25);
        this.add(channelNameLabel);

        channelNameINFO.setBounds(200, 120, 275, 25);
        this.add(channelNameINFO);

        JLabel operatorLable = new JLabel(CreateChannelViewModel.OPERATOR_LABEL);
        operatorLable.setBounds(10, 170, 180, 25);

        channelOperatorINFO.setBounds(200, 170, 2755, 25);

        createButton = new JButton(CreateChannelViewModel.CREATE_CHANNEL_BUTTON_LABLE);
        createButton.setBounds(150, 190, 100, 40);
        createButton.addActionListener(this);
        this.add(createButton);

        JFrame frame = new JFrame("YouChat - Create Channel");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        frame.add(this);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public void actionPerformed(ActionEvent evt) {
        JOptionPane.showConfirmDialog(this, "Cancel not implemented yet.");
    }

    public void displaySuccess(String channelName, String message) {
        SwingUtilities.invokeLater(() -> {
            JOptionPane.showMessageDialog(this, "Channel '" + channelName + "' created successfully: " + message, "Success", JOptionPane.INFORMATION_MESSAGE);
            channelNameINFO.setText(""); // Clear the input fields if needed
            channelIDINFO.setText("");   // Assuming you want to clear the channel ID as well
        });
    }

    public void displayError(String channelName, String message) {
        SwingUtilities.invokeLater(() -> {
            JOptionPane.showMessageDialog(this, "Failed to create channel '" + channelName + "': " + message, "Error", JOptionPane.ERROR_MESSAGE);
        });
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        CreateChannelState state = (CreateChannelState) evt.getNewValue();
        setFields(state);}

    private void setFields(CreateChannelState state) {
        channelIDINFO.setText(state.getChannelId());
    }
}

