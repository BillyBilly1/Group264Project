package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import interface_adapter.Login.LoginController;
import interface_adapter.Login.LoginViewModel;
import interface_adapter.Login.LoginState;


public class LoginView extends JPanel implements ActionListener, PropertyChangeListener {

    public final String viewName = "login";

    private final LoginViewModel loginViewModel;
    private final LoginController loginController;

    final JTextField userIdTextInfo = new JTextField(20);

    final JTextField nicknameTextInfo = new JTextField(20);

    private final JButton loginButton;

    public LoginView(LoginViewModel loginViewModel, LoginController loginController) {
        this.loginViewModel = loginViewModel;
        this.loginController = loginController;
        this.loginViewModel.addPropertyChangedListener(this);

        JButton loginButton = new JButton(LoginViewModel.LOGIN_BUTTON_LABEL);
        loginButton.setBounds((400 / 2) - (100 / 2), 300, 100, 40);
        this.loginButton = loginButton;
        this.setLayout(new BorderLayout());
        placeComponents();

    }

    private void placeComponents() {
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(null);

        JLabel titleLabel = new JLabel(loginViewModel.LOGIN_TITLE, SwingConstants.CENTER);
        titleLabel.setBounds(150, 100, 100, 30);
        centerPanel.add(titleLabel);

        JLabel userIdLabel = new JLabel(loginViewModel.USER_ID_LABEL);
        userIdLabel.setBounds(10, 150, 150, 30);
        centerPanel.add(userIdLabel);

        JTextField userIdText = userIdTextInfo;
        userIdText.setBounds(105, 150, 250, 40);
        centerPanel.add(userIdText);

        JLabel nicknameLabel = new JLabel(loginViewModel.NIKCKNAME_LABEL);
        nicknameLabel.setBounds(10, 200, 150, 30);
        centerPanel.add(nicknameLabel);

        JTextField nicknameText = nicknameTextInfo;
        nicknameText.setBounds(105, 200, 250, 40);
        centerPanel.add(nicknameText);


        this.add(loginButton);
        this.add(centerPanel);


        loginButton.addActionListener(this);


    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == loginButton) {
            String userId = userIdTextInfo.getText();
            String nickName = nicknameTextInfo.getText();
            LoginState currentState = loginViewModel.getState();
            currentState.setUser_id(userId);
            currentState.setNickname(nickName);
            loginViewModel.setState(currentState);
            loginController.execute(userId, nickName);

        }

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("state")) {
            LoginState state = (LoginState) evt.getNewValue();
            if (state.getUser_idError() != null) {
                JOptionPane.showMessageDialog(this, state.getUser_idError());
            }
            setFields(state);

        }
    }

    private void setFields(LoginState state) {
        userIdTextInfo.setText(state.getUser_id());
    }

}