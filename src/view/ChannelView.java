    package view;

    import entity.Channel.Channel;
    import interface_adapter.Channel.ChannelViewModel;
    import interface_adapter.Invite_Member.InviteMemberController;
    import interface_adapter.Remove_Member.RemoveMemberController;
    import interface_adapter.send_message.SendMessageController;
    import interface_adapter.send_message.SendMessageViewModel;
    import interface_adapter.translation.TranslateController;
    import interface_adapter.translation.TranslateState;
    import interface_adapter.translation.TranslateViewModel;

    import java.awt.*;
    import java.awt.event.ActionEvent;
    import java.awt.event.ActionListener;
    import java.awt.event.KeyEvent;
    import java.beans.PropertyChangeEvent;
    import java.beans.PropertyChangeListener;
    import java.io.IOException;
    import java.time.LocalDate;
    import java.util.ArrayList;
    import java.util.Collection;
    import java.util.Objects;
    import javax.swing.*;


    public class ChannelView extends JPanel implements ActionListener, PropertyChangeListener {
        public final String viewName = "channel";

        private final interface_adapter.Channel.ChannelViewModel channelViewModel;

        private int fontSize = 14;

        private JLabel channelNameLabel;

        private JButton sendButton;

        private JTextField inputField;

        private JTextField inviteField;

        private JTextField deleteField;

        private JList<String> messageList;

        private JButton translateChineseButton;

        private JButton translateFrenchButton;

        private JButton translateEnglishButton;

        private JButton inviteButton;

        private JButton deleteButton;

        private JButton memberButton;

        private JButton operatorButton;

        private JButton searchButton;

        private JButton dsearchButton;


        private Timer messageUpdateTimer;

        private final TranslateController translateController;

        private final TranslateViewModel translateViewModel;

        private final SendMessageController sendMessageController;

        private final SendMessageViewModel sendMessageViewModel;

        private final InviteMemberController inviteMemberController;

        private final RemoveMemberController removeMemberController;

        private long lastMessageTS = 0;


        public ChannelView(ChannelViewModel channelViewModel, TranslateViewModel translateViewModel,
                           SendMessageViewModel sendMessageViewModel,
                           TranslateController translateController,
                           SendMessageController sendMessageController, InviteMemberController inviteMemberController,
                           RemoveMemberController removeMemberController
        ) {
            this.channelViewModel = channelViewModel;
            this.translateController = translateController;
            this.translateViewModel = translateViewModel;
            this.sendMessageController = sendMessageController;
            this.sendMessageViewModel = sendMessageViewModel;
            this.inviteMemberController = inviteMemberController;
            this.removeMemberController = removeMemberController;

            channelViewModel.addPropertyChangeListener(this);

            //every 2.5s call the receivemessage
            messageUpdateTimer = new Timer(2400, e -> updateMessages());
            messageUpdateTimer.start();


            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            int width = screenSize.width / 2;
            int height = screenSize.height / 3 * 2;
            setPreferredSize(new Dimension(width, height));
            setLayout(new BorderLayout());
            initializeUI(width, height);
            setKeyBindings();
        }

        private void updateMessages() {
            Channel channel = channelViewModel.getChannel();
            String userID = channelViewModel.getMyID();
            String channelUrl = channel.getChannelUrl();

            sendMessageController.receive(userID, "", channelUrl, channelViewModel.getLastMessageTS());}


        public void initializeUI(int width, int height) {

            JPanel topPanel = new JPanel(new BorderLayout());

            // Setting Channel Name
            channelNameLabel = new JLabel(channelViewModel.getChannelName(), SwingConstants.CENTER);
            channelNameLabel.setPreferredSize(new Dimension(width, height / 12));
            topPanel.add(channelNameLabel, BorderLayout.CENTER);


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

                    String myUserID = channelViewModel.getMyID();

                    String[] parts = value.toString().split("\n", 3);
                    String senderName = parts[0].split(":")[0].trim();
                    String messageContent = parts.length > 1 ? parts[1].trim() : "";
                    String time = parts.length > 2 ? parts[2].trim() : "";

                    String textColor = senderName.equals(myUserID) ? "red" : "black";

                    String htmlText = "<html><div style='width: 100%;'>" +
                            "<div style='float: left; color:" + textColor + ";'>" + senderName + ":<br>" + messageContent + "</div>" +
                            "<div style='float: right;'>" + time + "</div>" +
                            "<div style='clear: both;'></div></div></html>";

                    setText(htmlText);
                    setFont(new Font(getFont().getName(), Font.PLAIN, fontSize));

                    return this;
                }


            });


            JScrollPane scrollPane = new JScrollPane(messageList);
            scrollPane.setPreferredSize(new Dimension(width, height * 8 / 12));
            add(scrollPane, BorderLayout.CENTER);


            // Setting the button
            int buttonSize = height * 4 / 12;
            JPanel buttonPanel = new JPanel();
            buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));

            sendButton = new JButton("Send");
            sendButton.setPreferredSize(new Dimension(width / 10, buttonSize));
            sendButton.setFont(new Font(sendButton.getFont().getName(), Font.PLAIN, 10));
            sendButton.addActionListener(this);
            buttonPanel.add(sendButton);


            JPanel translateButtonsPanel = new JPanel();
            translateButtonsPanel.setLayout(new FlowLayout(FlowLayout.LEFT));

            // Initialize translate to Chinese button
            translateChineseButton = new JButton("简");
            translateChineseButton.setPreferredSize(new Dimension(width / 10, buttonSize / 4));
            translateChineseButton.setFont(new Font(translateChineseButton.getFont().getName(), Font.PLAIN, 14));
            translateChineseButton.addActionListener(this);
            translateButtonsPanel.add(translateChineseButton);

            // Initialize translate to English button
            translateEnglishButton = new JButton("en");
            translateEnglishButton.setPreferredSize(new Dimension(width / 10, buttonSize / 4));
            translateEnglishButton.setFont(new Font(translateEnglishButton.getFont().getName(), Font.ITALIC, 18));
            translateEnglishButton.addActionListener(this);
            translateButtonsPanel.add(translateEnglishButton);
            topPanel.add(translateButtonsPanel, BorderLayout.EAST);
            add(topPanel, BorderLayout.NORTH);

            // Initialize translate to French button
            translateFrenchButton = new JButton("fr");
            translateFrenchButton.setPreferredSize(new Dimension(width / 10, buttonSize / 4));
            translateFrenchButton.setFont(new Font(translateFrenchButton.getFont().getName(), Font.ITALIC, 18));
            translateFrenchButton.addActionListener(this);
            translateButtonsPanel.add(translateFrenchButton);
            topPanel.add(translateButtonsPanel, BorderLayout.EAST);


            //View Profile Button

            // Panel for invite and delete buttons and fields
            JPanel topRightPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

            // Invite panel
            Font buttonFont = new Font("buttonfont", Font.PLAIN, 10);
            JPanel invitePanel = new JPanel();
            invitePanel.setLayout(new BoxLayout(invitePanel, BoxLayout.Y_AXIS));
            inviteField = new JTextField();
            inviteField.setMaximumSize(new Dimension(width / 6, 30));
            inviteButton = new JButton("Invite");
            inviteButton.setFont(buttonFont);
            inviteButton.addActionListener(this);
            invitePanel.add(inviteField);
            invitePanel.add(inviteButton);

            // Delete panel
            JPanel deletePanel = new JPanel();
            deletePanel.setLayout(new BoxLayout(deletePanel, BoxLayout.Y_AXIS));
            deleteField = new JTextField();
            deleteField.setMaximumSize(new Dimension(width / 6, 30));
            deleteButton = new JButton("Remove");
            deleteButton.setFont(buttonFont);
            deleteButton.addActionListener(this);
            deletePanel.add(deleteField);
            deletePanel.add(deleteButton);

            // Add panels to the top right panel
            topRightPanel.add(invitePanel);
            topRightPanel.add(deletePanel);

            // Add all to the top panel
            topPanel.add(channelNameLabel, BorderLayout.CENTER);
            topPanel.add(topRightPanel, BorderLayout.WEST);
            this.add(topPanel, BorderLayout.NORTH);

            //Setting the InputPanel

            JPanel inputPanel = new JPanel(new BorderLayout());
            inputField = new JTextField();
            inputField.addActionListener(this);
            inputField.setFont(new Font(getFont().getName(), Font.BOLD, 18));
            inputPanel.add(inputField, BorderLayout.CENTER);
            inputPanel.add(buttonPanel, BorderLayout.EAST);
            inputPanel.setPreferredSize(new Dimension(width, height * 3 / 12));
            add(inputPanel, BorderLayout.SOUTH);



            JPanel bottomPanel = new JPanel();
            bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.Y_AXIS)); // 设置为垂直布局

            bottomPanel.setMaximumSize(new Dimension(width / 7, height * 9 / 12));

            // 初始化Member按钮
            memberButton = new JButton("Member");
            memberButton.addActionListener(this);
            bottomPanel.add(memberButton);

            // 初始化Operator按钮
            operatorButton = new JButton("Operator");
            operatorButton.addActionListener(this);
            bottomPanel.add(operatorButton);

            searchButton = new JButton("search");
            bottomPanel.add(searchButton, BorderLayout.SOUTH);
            searchButton.addActionListener(e -> showSearchDialog());

            dsearchButton = new JButton("Dsearch");
            bottomPanel.add(dsearchButton);
            dsearchButton.addActionListener(e -> showDateSearchDialog());

            // 将新的按钮面板添加到主面板的右侧
            add(bottomPanel, BorderLayout.EAST);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            ListModel<String> listModel = messageList.getModel();
            ArrayList<String> arrayList = new ArrayList<>();
            for (int i = 0; i < listModel.getSize(); i++) {
                arrayList.add(listModel.getElementAt(i));
            }


            if ((e.getSource() == sendButton || e.getSource() == inputField)
                    & !Objects.equals(inputField.getText(), "")) {
                sendMessageController.send(channelViewModel.getMyID(), inputField.getText(),
                        channelViewModel.getChannel().getChannelUrl(), 0);

                inputField.setText(""); // clear the inputtextfield

            }
            //about translation
            else if (e.getSource() == translateChineseButton) {
                TranslateState currentState = translateViewModel.getState();
                try {
                    translateController.translate("zh", arrayList);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            } else if (e.getSource() == translateFrenchButton) {
                TranslateState currentState = translateViewModel.getState();
                try {
                    translateController.translate("fr", arrayList);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            } else if (e.getSource() == translateEnglishButton) {
                TranslateState currentState = translateViewModel.getState();
                try {
                    translateController.translate("en", arrayList);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }

            // Edit channel profile

            else if (e.getSource() == inviteButton && !inviteField.getText().isEmpty()) {
                String inviteID = inviteField.getText();
                inviteField.setText("");
                inviteMemberController.execute(inviteID, channelViewModel.getChannel().getChannelUrl());
            }

            else if (e.getSource() == deleteButton) {
                String removeID = deleteField.getText();
                deleteField.setText("");
                removeMemberController.execute(removeID, channelViewModel.getChannel().getChannelUrl());
        }
            else if (e.getSource() == memberButton) {
                ArrayList<String> userIDs = new ArrayList<>(channelViewModel.getChannel().getUser_ids());
                JTextArea textArea = new JTextArea();
                textArea.setEditable(false);
                for (String userID: userIDs) {
                    textArea.append(userID + "\n");
                }
                JScrollPane scrollPane = new JScrollPane(textArea);
                JOptionPane.showMessageDialog(null, scrollPane,
                        "Members", JOptionPane.INFORMATION_MESSAGE);

            }

            else if (e.getSource() == operatorButton) {
                ArrayList<String> opIDs = new ArrayList<>(channelViewModel.getChannel().getOperator());
                JTextArea textArea = new JTextArea();
                textArea.setEditable(false);
                for (String userID: opIDs) {
                    textArea.append(userID + "\n");
                }
                JScrollPane scrollPane = new JScrollPane(textArea);
                JOptionPane.showMessageDialog(null, scrollPane,
                        "Operators", JOptionPane.INFORMATION_MESSAGE);
            }

        }



        // show the changes in view-model
        public void updateFromViewModel() {
            channelNameLabel.setText(channelViewModel.getChannelName());
            messageList.setListData(channelViewModel.getMessages().toArray(new String[0])); // update the list
        }

        @Override
        public void propertyChange(PropertyChangeEvent evt) {
            if ("messagesUpdated".equals(evt.getPropertyName())) {
                updateMessageList((ArrayList<String>) evt.getNewValue());
            }
        }

        private void updateMessageList(ArrayList<String> newMessages) {
            DefaultListModel<String> model = new DefaultListModel<>();
            for (String message : newMessages) {
                model.addElement(message);
            }
            messageList.setModel(model);
            int lastIndex = model.getSize() - 1;
            if (lastIndex >= 0) {
                messageList.ensureIndexIsVisible(lastIndex);
            }
        }



        private void adjustFontSize(int adjustment) {
            fontSize += adjustment;
            if (fontSize < 8) fontSize = 8;
            else if (fontSize > 30) fontSize = 30;
            Font newFont = new Font("Arial", Font.PLAIN, fontSize);
            messageList.setFont(newFont);
            messageList.repaint();
        }

        private void setKeyBindings() {
            InputMap inputMap = getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
            ActionMap actionMap = getActionMap();

            inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_1, KeyEvent.CTRL_DOWN_MASK), "increaseFontSize");
            actionMap.put("increaseFontSize", new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    adjustFontSize(1);
                }
            });

            inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_2, KeyEvent.CTRL_DOWN_MASK), "decreaseFontSize");
            actionMap.put("decreaseFontSize", new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    adjustFontSize(-1);
                }
            });
        }

        public void onClose() {
            if (messageUpdateTimer != null) {
                messageUpdateTimer.stop();
            }
        }

        // search message

        private void showSearchDialog() {
            String query = JOptionPane.showInputDialog(this, "Enter text to search for:", "Search Messages", JOptionPane.QUESTION_MESSAGE);
            if (query != null && !query.isEmpty()) {
                ArrayList<String> searchResults = channelViewModel.searchMessages(query);
                JTextArea textArea = new JTextArea();
                for (String result : searchResults) {
                    textArea.append(result + "\n\n");
                }
                textArea.setEditable(false);
                JScrollPane scrollPane = new JScrollPane(textArea);
                scrollPane.setPreferredSize(new Dimension(300, 400));
                JOptionPane.showMessageDialog(this, scrollPane, "Search Results", JOptionPane.INFORMATION_MESSAGE);
            }
        }

        private void showDateSearchDialog() {
            // 获取当前日期作为默认值
            LocalDate today = LocalDate.now();
            JTextField yearField = new JTextField("" + today.getYear());
            JTextField monthField = new JTextField("" + (today.getMonthValue()));
            JTextField dayField = new JTextField("" + today.getDayOfMonth());

            JPanel panel = new JPanel(new GridLayout(0, 1));
            panel.add(new JLabel("Year:"));
            panel.add(yearField);
            panel.add(new JLabel("Month:"));
            panel.add(monthField);
            panel.add(new JLabel("Day:"));
            panel.add(dayField);

            int result = JOptionPane.showConfirmDialog(this, panel, "Enter Date (yyyy-mm-dd)", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
            if (result == JOptionPane.OK_OPTION) {
                int year = Integer.parseInt(yearField.getText());
                int month = Integer.parseInt(monthField.getText());
                int day = Integer.parseInt(dayField.getText());

                ArrayList<String> searchResults = channelViewModel.searchMessagesByDate(year, month, day);
                JTextArea textArea = new JTextArea();
                for (String msg : searchResults) {
                    textArea.append(msg + "\n\n");
                }
                textArea.setEditable(false);
                JScrollPane scrollPane = new JScrollPane(textArea);
                scrollPane.setPreferredSize(new Dimension(300, 400));
                JOptionPane.showMessageDialog(this, scrollPane, "Search Results", JOptionPane.INFORMATION_MESSAGE);
            }
        }




    }



