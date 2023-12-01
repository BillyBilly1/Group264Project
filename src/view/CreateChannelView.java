package view;

import interface_adapter.Signup.SignupState;
import interface_adapter.create_channel.CreateChannelController;
import interface_adapter.create_channel.CreateChannelState;
import interface_adapter.create_channel.CreateChannelViewModel;


import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;

public class CreateChannelView extends JPanel implements ActionListener, PropertyChangeListener{

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {

            JFrame frame = new JFrame("YouChat - Create Channel");


            CreateChannelView view = new CreateChannelView(null, null);

            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(500, 300);
            frame.add(view);
            frame.setLocationRelativeTo(null);

            // Display the frame
            frame.setVisible(true);
        });
    }


    public final String viewName = "create channel";

    private final CreateChannelViewModel createChannelViewModel;
    private final CreateChannelController createChannelController;
    final JTextField channel_urlINFO = new JTextField();
    final JTextField channelNameINFO = new JTextField();
    private final JButton createButton;

    public CreateChannelView(CreateChannelViewModel createChannelViewModel, CreateChannelController createChannelController) {
        this.createChannelViewModel = createChannelViewModel;
        this.createChannelController = createChannelController;

        this.setLayout(null);

        JLabel titleLabel = new JLabel(CreateChannelViewModel.CREATE_CHANNEL_TITLE, SwingConstants.CENTER);
        titleLabel.setBounds(100, 20, 200, 30);
        this.add(titleLabel);

        JLabel channelIDLabel = new JLabel(CreateChannelViewModel.CHANNEL_URL_LABEL);
        channelIDLabel.setBounds(10, 70, 180, 25);
        this.add(channelIDLabel);

        channel_urlINFO.setBounds(200, 70, 275, 25);
        this.add(channel_urlINFO);

        JLabel channelNameLabel = new JLabel(CreateChannelViewModel.CHANNEL_NAME_LABEL);
        channelNameLabel.setBounds(10, 120, 180, 25);
        this.add(channelNameLabel);

        channelNameINFO.setBounds(200, 120, 275, 25);
        this.add(channelNameINFO);


        createButton = new JButton(CreateChannelViewModel.CREATE_CHANNEL_BUTTON_LABLE);
        createButton.setBounds(150, 190, 100, 40);
        this.add(createButton);
        createButton.addActionListener(
                new ActionListener(){
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        if(evt.getSource().equals(createButton)){
                            CreateChannelState currentState = createChannelViewModel.getState();

                            createChannelController.execute(
                                    currentState.getChannel_url(),
                                    currentState.getChannelName(),
                                    currentState.getOperator(),
                                    currentState.getIsEphemeral()
                            );
                        }
                    }
                }
        );

        channel_urlINFO.addKeyListener(
                new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {
                        CreateChannelState currentState = createChannelViewModel.getState();
                        currentState.setChannel_url(channel_urlINFO.getText());
                        createChannelViewModel.setState(currentState);

                    }

                    @Override
                    public void keyPressed(KeyEvent e) {

                    }

                    @Override
                    public void keyReleased(KeyEvent e) {

                    }
                }
        );

        channelNameLabel.addKeyListener(
                new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {
                        CreateChannelState currentState = createChannelViewModel.getState();
                        currentState.setChannelName(channelNameINFO.getText());
                        createChannelViewModel.setState(currentState);

                    }

                    @Override
                    public void keyPressed(KeyEvent e) {

                    }

                    @Override
                    public void keyReleased(KeyEvent e) {

                    }
                }
        );



        JFrame frame = new JFrame("YouChat - Create Channel");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 300);
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

