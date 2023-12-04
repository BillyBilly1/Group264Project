package view;

import interface_adapter.Signup.SignupState;
import interface_adapter.create_channel.CreateChannelController;
import interface_adapter.create_channel.CreateChannelState;
import interface_adapter.create_channel.CreateChannelViewModel;
import org.w3c.dom.ls.LSOutput;
import use_case.CreateChannel.CreateChannelInputBoundary;
import use_case.CreateChannel.CreateChannelInputData;
import use_case.CreateChannel.CreateChannelInteractor;


import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

public class CreateChannelView extends JPanel implements ActionListener, PropertyChangeListener{



    public final String viewName = "create channel";

    private final CreateChannelViewModel createChannelViewModel;
    private final CreateChannelController createChannelController;
    final JTextField channel_urlINFO = new JTextField();
    final JTextField channelNameINFO = new JTextField();
    private final JButton createButton;

    private final JButton backButton;




    public CreateChannelView(CreateChannelViewModel createChannelViewModel, CreateChannelController createChannelController) {
        this.createChannelViewModel = createChannelViewModel;
        this.createChannelController = createChannelController;

        this.setLayout(null);

        JLabel titleLabel = new JLabel(CreateChannelViewModel.CREATE_CHANNEL_TITLE, SwingConstants.CENTER);
        titleLabel.setBounds(100, 20, 200, 30);
        this.add(titleLabel);

        JLabel channelIDLabel = new JLabel(CreateChannelViewModel.CHANNEL_URL_LABEL);
        channelIDLabel.setBounds(10, 120, 180, 25);
        this.add(channelIDLabel);

        channel_urlINFO.setBounds(200, 120, 275, 25);
        this.add(channel_urlINFO);

        JLabel channelNameLabel = new JLabel(CreateChannelViewModel.CHANNEL_NAME_LABEL);
        channelNameLabel.setBounds(10, 70, 180, 25);
        this.add(channelNameLabel);

        channelNameINFO.setBounds(200, 70, 275, 25);
        this.add(channelNameINFO);

        createButton = new JButton(CreateChannelViewModel.CREATE_CHANNEL_BUTTON_LABLE);
        createButton.setBounds(150, 190, 100, 40);
        this.add(createButton);
        createButton.addActionListener(this);

        backButton = new JButton("Back");
        backButton.setBounds(150, 250, 100, 40);
        this.add(backButton);
        backButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == createButton && channel_urlINFO.getText().length()
                >= 4 && channelNameINFO.getText().length() >= 4) {
            CreateChannelState currentState = createChannelViewModel.getState();
            currentState.setChannelName(channelNameINFO.getText());
            currentState.setChannel_url(channel_urlINFO.getText());

            createChannelController.execute(
                    currentState.getUser_ids(),
                    currentState.getChannel_url(),
                    currentState.getChannelName(),
                    currentState.getOperator(),
                    currentState.getIs_distinct(),
                    currentState.getIsEphemeral()
            );
        } else if (e.getSource() == createButton && (
                channel_urlINFO.getText().length() < 4 || channelNameINFO.getText().length() < 4)) {
            JOptionPane.showMessageDialog(null,
                    "Channel name or channel URL must be more than 3 characters.",
                    "Error Message", JOptionPane.ERROR_MESSAGE);
        } else if (e.getSource() == backButton) {
            createChannelController.back();
        }
    }

    public void displaySuccess(String channelName, String message) {
        SwingUtilities.invokeLater(() -> {
            JOptionPane.showMessageDialog(this, "Channel '" + channelName + "' created successfully: " + message, "Success", JOptionPane.INFORMATION_MESSAGE);
            channelNameINFO.setText(""); // Clear the input fields if needed
            channel_urlINFO.setText("");   // Assuming you want to clear the channel ID as well
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
        channel_urlINFO.setText(state.getChannel_url());
    }


}

