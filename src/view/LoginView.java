package view;

import javax.swing.*;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import interface_adapter.Login.LoginController;
import interface_adapter.Login.LoginViewModel;
import interface_adapter.Login.LoginState;


public class LoginView extends JPanel implements ActionListener, PropertyChangeListener {

    private final LoginViewModel loginViewModel;
    private final LoginController loginController;

    public final String viewName = "log in";

    final JTextField userIdTextInfo = new JTextField(20);

    final JTextField nicknameTextInfo = new JTextField(20);

    public LoginView(LoginViewModel loginViewModel, LoginController loginController) {
        this.loginViewModel = loginViewModel;
        this.loginController = loginController;
        this.loginViewModel.addPropertyChangedListener(this);

        JFrame frame = new JFrame("YouChat - Login");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 600);

        JPanel panel = new JPanel();
        frame.add(panel);
        placeComponents(panel);

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void placeComponents(JPanel panel) {
        panel.setLayout(null);

        JLabel titleLabel = new JLabel(loginViewModel.LOGIN_TITLE, SwingConstants.CENTER);
        titleLabel.setBounds(150, 100, 100, 30);
        panel.add(titleLabel);

        JLabel userIdLabel = new JLabel(loginViewModel.USER_ID_LABEL);
        userIdLabel.setBounds(10, 150, 150, 30);
        panel.add(userIdLabel);

        JTextField userIdText = userIdTextInfo;
        userIdText.setBounds(100, 150, 250, 40);
        panel.add(userIdText);

        JLabel nicknameLabel = new JLabel(loginViewModel.NIKCKNAME_LABEL);
        nicknameLabel.setBounds(10, 200, 150, 30);
        panel.add(nicknameLabel);

        JTextField nicknameText = nicknameTextInfo;
        nicknameText.setBounds(100, 200, 250, 40);
        panel.add(nicknameText);

        JButton loginButton = new JButton(loginViewModel.LOGIN_BUTTON_LABEL);
        loginButton.setBounds((400 / 2) - (100 / 2), 300, 100, 40);
        panel.add(loginButton);

        // Add action listener for loginButton
        // ...
        loginButton.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (e.getSource().equals(loginButton)) {
                            LoginState currentState = loginViewModel.getState();
                            loginController.execute(
                                    currentState.getUser_id(),
                                    currentState.getNickname()
                            );
                        }
                    }
                }
        );

        userIdText.addKeyListener(new KeyListener() {

            @Override
            public void keyTyped(KeyEvent e) {
                LoginState currentState = loginViewModel.getState();
                currentState.setUser_id(userIdText.getText() + e.getKeyChar());
                loginViewModel.setState(currentState);
            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        nicknameText.addKeyListener(
                new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {
                        LoginState currentState = loginViewModel.getState();
                        currentState.setNickname(nicknameText.getText() + e.getKeyChar());
                        loginViewModel.setState(currentState);
                    }

                    @Override
                    public void keyPressed(KeyEvent e) {

                    }

                    @Override
                    public void keyReleased(KeyEvent e) {

                    }
                }
        );
        this.add(titleLabel);
        this.add(userIdText);
        this.add(nicknameText);
        this.add(panel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("Click" + e.getActionCommand());
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        LoginState state = (LoginState) evt.getNewValue();
        setFields(state);
    }

    private void setFields(LoginState state) {
        userIdTextInfo.setText(state.getUser_id());
    }
}
