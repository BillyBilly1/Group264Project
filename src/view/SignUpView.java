
package view;

import interface_adapter.Signup.SignupController;
import interface_adapter.Signup.SignupState;
import interface_adapter.Signup.SignupViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;


public class SignUpView extends JPanel implements ActionListener, PropertyChangeListener {

    public final String viewName = "sign up";

    private final SignupViewModel signupViewModel;

    private final SignupController signupController;

    private final JTextField userIdTextInfo = new JTextField(20);

    private final JTextField nicknameTextInfo = new JTextField(20);

    private final JButton signupButton;


    public SignUpView(SignupViewModel signupViewModel, SignupController signupController) {
        this.signupViewModel = signupViewModel;
        this.signupController = signupController;
        this.signupViewModel.addPropertyChangeListener(this);

//        JFrame frame = new JFrame("Youchat - Signup");
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.setSize(400, 600);

        JButton signupButton = new JButton(SignupViewModel.SIGNUP_BUTTON_LABEL);
        signupButton.setBounds((400 / 2) - (100 / 2), 300, 100, 40);
        this.signupButton = signupButton;


//        frame.add(panel);
        placeComponents();

//        frame.setLocationRelativeTo(null);
//        frame.setVisible(true);
        this.setLayout(new BorderLayout());

    }

    public void placeComponents() {

        JLabel titleLabel = new JLabel(signupViewModel.SIGNUP_TITLE, SwingConstants.CENTER);
        titleLabel.setBounds(150, 100, 100, 30);
        this.add(titleLabel);

        JLabel userIdLabel = new JLabel(signupViewModel.USER_ID_LABEL);
        userIdLabel.setBounds(10, 150, 150, 30);
        this.add(userIdLabel);

        JTextField userIdText = userIdTextInfo;
        userIdText.setBounds(100, 150, 250, 40);
        this.add(userIdText);

        JLabel nicknameLabel = new JLabel(signupViewModel.NICKNAME_LABEL);
        nicknameLabel.setBounds(10, 200, 150, 30);
        this.add(nicknameLabel);

        JTextField nicknameText = nicknameTextInfo;
        nicknameText.setBounds(100, 200, 250, 40);
        this.add(nicknameText);


        this.add(signupButton);

        signupButton.addActionListener(this);
    }


    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == signupButton) {
            String userID = userIdTextInfo.getText();
            String nickName = nicknameTextInfo.getText();
            SignupState currentState = signupViewModel.getState();
            currentState.setUser_id(userID);
            currentState.setNickname(nickName);
            signupViewModel.setState(currentState);
            signupController.execute(userID, nickName);
        }


    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("state")) {
            SignupState state = (SignupState) evt.getNewValue();
            if (state.getUser_idError() != null) {
                JOptionPane.showMessageDialog(this, state.getUser_idError());
            }
            setFields(state);
        }
    }
    private void setFields(SignupState state){
        userIdTextInfo.setText(state.getUser_id());
    }
}



