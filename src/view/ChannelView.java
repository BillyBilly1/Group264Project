    package view;

    import interface_adapter.Channel.ChannelViewModel;
    import interface_adapter.translation.TranslateController;
    import interface_adapter.translation.TranslateState;
    import interface_adapter.translation.TranslateViewModel;
    import use_case.SendMessage;

    import java.awt.*;
    import java.awt.event.ActionEvent;
    import java.awt.event.ActionListener;
    import java.awt.event.KeyEvent;
    import java.beans.PropertyChangeEvent;
    import java.beans.PropertyChangeListener;
    import java.io.IOException;
    import java.util.ArrayList;
    import java.util.Objects;
    import javax.swing.*;


    public class ChannelView extends JPanel implements ActionListener, PropertyChangeListener {
        public final String viewName = "channel";

        private final interface_adapter.Channel.ChannelViewModel channelViewModel;

        private int fontSize = 14;

        private JLabel channelNameLabel;

        private JButton sendButton;

        private JTextField inputField;

        private JList<String> messageList;

        private JButton translateChineseButton;

        private JButton translateFrenchButton;

        private JButton translateEnglishButton;

        private JButton viewProButton;

        private final TranslateController translateController;

        private final TranslateViewModel translateViewModel;



        public ChannelView(ChannelViewModel channelViewModel,TranslateViewModel translateViewModel,
                           TranslateController translateController
                           ) {
            this.channelViewModel = channelViewModel;
            this.translateController = translateController;
            this.translateViewModel = translateViewModel;

            channelViewModel.addPropertyChangeListener(this);


            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            int width = screenSize.width / 5 * 2;
            int height = screenSize.height / 3 * 2;
            setPreferredSize(new Dimension(width, height));
            setLayout(new BorderLayout());
            initializeUI(width, height);
            setKeyBindings();
        }

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
                    // 分割消息内容和时间，假设格式是 "SenderName: \n MessageContent \n Time"
                    String[] parts = value.toString().split("\n");
                    String senderAndMessage = parts[0].trim() + (parts.length > 1 ? "<br>" + parts[1].trim() : "");
                    String time = parts.length > 2 ? parts[2].trim() : "";

                    String htmlText = "<html><div style='width: 100%;'>" +
                            "<div style='float: left;'>" + senderAndMessage + "</div>" +
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
            buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS)); // 垂直堆叠组件

            sendButton = new JButton("Send");
            sendButton.setPreferredSize(new Dimension(width / 10, buttonSize)); // 定义按钮大小
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

            JPanel viewProPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
            JButton viewProButton = new JButton("Edit");
            viewProButton.setPreferredSize(new Dimension(width / 8, buttonSize / 8));
            viewProButton.setFont(new Font((viewProButton.getFont().getName()), Font.PLAIN, 12));
            viewProButton.addActionListener(this);
            viewProPanel.add(viewProButton);
            topPanel.add(viewProPanel, BorderLayout.WEST);
            add(topPanel, BorderLayout.NORTH);

            //Setting the InputPanel

            JPanel inputPanel = new JPanel(new BorderLayout());
            inputField = new JTextField();
            inputField.addActionListener(this);
            inputField.setFont(new Font(getFont().getName(), Font.BOLD, 18));
            inputPanel.add(inputField, BorderLayout.CENTER);
            inputPanel.add(buttonPanel, BorderLayout.EAST);
            inputPanel.setPreferredSize(new Dimension(width, height * 3 / 12));
            add(inputPanel, BorderLayout.SOUTH);
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
                channelViewModel.addMessage(inputField.getText()); // add messages to ViewModel
                inputField.setText(""); // clear the inputtextfield
                messageList.setListData(channelViewModel.getMessages().
                        toArray(new String[0])); // Update the message view
                updateFromViewModel();

            }
            //about translation
            else if (e.getSource() == translateChineseButton) {
                TranslateState currentState = translateViewModel.getState();
                try {
                    translateController.translate("zh", arrayList);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
            else if (e.getSource() == translateFrenchButton) {
                TranslateState currentState = translateViewModel.getState();
                try {
                    translateController.translate("fr", arrayList);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
            else if (e.getSource() == translateEnglishButton) {
                TranslateState currentState = translateViewModel.getState();
                try {
                    translateController.translate("en", arrayList);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }

            // Edit channel profile

            else if (e.getSource() == viewProButton) {
                //
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
    }
