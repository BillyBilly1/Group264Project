package view;

import interface_adapter.Channel.ChannelViewModel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.*;



public class ChannelView extends JPanel implements ActionListener, PropertyChangeListener {
    public final String viewName = "Channel";

    private final interface_adapter.Channel.ChannelViewModel channelViewModel;

    private JLabel channelNameLabel;

    private JButton sendButton;

    private JTextField inputField;

    private JList<String> messageList;


    public ChannelView(ChannelViewModel channelViewModel) {
        this.channelViewModel = channelViewModel;

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = screenSize.width / 5 * 2;
        int height = screenSize.height / 3 * 2;
        setPreferredSize(new Dimension(width, height));
        setLayout(new BorderLayout());
        initializeUI(width, height);

    }

    public void initializeUI(int width, int height) {
        // Setting Channel Name
        channelNameLabel = new JLabel(channelViewModel.getChannelName(), SwingConstants.CENTER);
        channelNameLabel.setPreferredSize(new Dimension(width, height / 12));
        add(channelNameLabel, BorderLayout.NORTH);

        // Setting Channel Message List
        DefaultListModel<String> listModel = new DefaultListModel<>();
        for (String item : channelViewModel.getMessages()) {
            listModel.addElement(item);
        }
        messageList = new JList<>(listModel);
        messageList.setCellRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(
                    JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                // 分割消息内容和时间，假设格式是 "SenderName: \n MessageContent \n Time"
                String[] parts = value.toString().split("\n");
                String senderAndMessage = parts[0].trim() + (parts.length > 1 ? "<br>" + parts[1].trim() : "");
                String time = parts.length > 2 ? parts[2].trim() : "";

                // 使用 HTML 和 CSS 来格式化文本，将时间右对齐
                String htmlText = "<html><div style='width: 100%;'>" +
                        "<div style='float: left;'>" + senderAndMessage + "</div>" +
                        "<div style='float: right;'>" + time + "</div>" +
                        "<div style='clear: both;'></div></div></html>";
                setText(htmlText);
                return this;
            }
        });


        JScrollPane scrollPane = new JScrollPane(messageList);
        scrollPane.setPreferredSize(new Dimension(width, height * 8 / 12));
        add(scrollPane, BorderLayout.CENTER);

        // Setting the input field area as well as the send button
        JPanel inputPanel = new JPanel(new BorderLayout());
        inputField = new JTextField();
        inputField.addActionListener(this);


        // Setting the button
        int buttonSize = height * 2 / 12;
        sendButton = new JButton("Send");
        sendButton.setPreferredSize(new Dimension(width / 10, buttonSize)); // Square button
        sendButton.setFont(new Font(sendButton.getFont().getName(), Font.PLAIN, 10));


        // The button panel is used to position the send button to the right end of the input field.
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.add(sendButton);

        inputPanel.add(inputField, BorderLayout.CENTER);
        inputPanel.add(buttonPanel, BorderLayout.EAST);
        inputPanel.setPreferredSize(new Dimension(width, height * 3 / 12));
        add(inputPanel, BorderLayout.SOUTH);
    }




    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == sendButton || e.getSource() == inputField) {
            channelViewModel.addMessage(inputField.getText()); // add messages to ViewModel
            inputField.setText(""); // clear the inputtextfield
            messageList.setListData(channelViewModel.getMessages().toArray(new String[0])); // Update the message view
        }
    }

    // show the changes in view-model
    public void updateFromViewModel() {
        channelNameLabel.setText(channelViewModel.getChannelName());
        messageList.setListData(channelViewModel.getMessages().toArray(new String[0])); // update the list
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }

}