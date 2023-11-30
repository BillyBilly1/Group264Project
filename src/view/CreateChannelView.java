package view;

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

        this.setLayout(null);

        JLabel titleLabel = new JLabel(CreateChannelViewModel.CREATE_CHANNEL_TITLE, SwingConstants.CENTER);
        titleLabel.setBounds(100, 20, 200, 30); // 修改 titleLabel 的 bounds
        this.add(titleLabel);

        JLabel channelIDLabel = new JLabel(CreateChannelViewModel.CHANNEL_ID_LABEL);
        channelIDLabel.setBounds(10, 70, 180, 25); // 修改 channelIDLabel 的 bounds
        this.add(channelIDLabel);

        channelIDINFO.setBounds(200, 70, 275, 25); // 修改 channelIDINFO 的 bounds
        this.add(channelIDINFO);

        JLabel channelNameLabel = new JLabel(CreateChannelViewModel.CHANNEL_NAME_LABEL);
        channelNameLabel.setBounds(10, 120, 180, 25); // 修改 channelNameLabel 的 bounds
        this.add(channelNameLabel);

        channelNameINFO.setBounds(200, 120, 275, 25); // 修改 channelNameINFO 的 bounds
        this.add(channelNameINFO);

        createButton = new JButton(CreateChannelViewModel.CREATE_CHANNEL_BUTTON_LABLE);
        createButton.setBounds(150, 170, 100, 40); // 修改 createButton 的 bounds
        createButton.addActionListener(this);
        this.add(createButton);

        // 设置 JFrame
        JFrame frame = new JFrame("YouChat - Create Channel");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 300);
        frame.add(this);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }


    public static void main(String[] args) {

        //测试用，后续请删除。
        new CreateChannelView(new CreateChannelViewModel(), new CreateChannelController());
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
