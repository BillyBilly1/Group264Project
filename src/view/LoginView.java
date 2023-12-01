package view;

import javax.swing.*;
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

//        JFrame frame = new JFrame("YouChat - Login");
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.setSize(400, 600);

        JButton loginButton = new JButton(LoginViewModel.LOGIN_BUTTON_LABEL);
        loginButton.setBounds((400 / 2) - (100 / 2), 300, 100, 40);
        this.loginButton = loginButton;

//        frame.add(panel);
        placeComponents();
//
//        frame.setLocationRelativeTo(null);
//        frame.setVisible(true);
    }

    private void placeComponents() {

        JLabel titleLabel = new JLabel(loginViewModel.LOGIN_TITLE, SwingConstants.CENTER);
        titleLabel.setBounds(150, 100, 100, 30);
        this.add(titleLabel);

        JLabel userIdLabel = new JLabel(loginViewModel.USER_ID_LABEL);
        userIdLabel.setBounds(10, 150, 150, 30);
        this.add(userIdLabel);

        JTextField userIdText = userIdTextInfo;
        userIdText.setBounds(100, 150, 250, 40);
        this.add(userIdText);

        JLabel nicknameLabel = new JLabel(loginViewModel.NIKCKNAME_LABEL);
        nicknameLabel.setBounds(10, 200, 150, 30);
        this.add(nicknameLabel);

        JTextField nicknameText = nicknameTextInfo;
        nicknameText.setBounds(100, 200, 250, 40);
        this.add(nicknameText);


        this.add(loginButton);


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
        LoginState state =  (LoginState) evt.getNewValue();
        setFields(state);}


    private void setFields(LoginState state) {
        userIdTextInfo.setText(state.getUser_id());
    }

}