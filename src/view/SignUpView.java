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
    private final JButton signupButton = new JButton(SignupViewModel.SIGNUP_BUTTON_LABEL);
    private final JButton skipToLoginButton = new JButton("skip to login");

    public SignUpView(SignupViewModel signupViewModel, SignupController signupController) {
        this.signupViewModel = signupViewModel;
        this.signupController = signupController;
        this.signupViewModel.addPropertyChangeListener(this);
        this.setLayout(new BorderLayout());
        placeComponents();
    }

    public void placeComponents() {
      
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));

        centerPanel.add(new JLabel(signupViewModel.SIGNUP_TITLE, SwingConstants.CENTER));
        centerPanel.add(createLabeledField("User ID:", userIdTextInfo));
        centerPanel.add(createLabeledField("Nickname:", nicknameTextInfo));

      
        JPanel buttonPanel = new JPanel(new FlowLayout());
        signupButton.addActionListener(this);
        skipToLoginButton.addActionListener(this);
        buttonPanel.add(signupButton);
        buttonPanel.add(skipToLoginButton);

        this.add(centerPanel, BorderLayout.CENTER);
        this.add(buttonPanel, BorderLayout.SOUTH);
    }

    private JPanel createLabeledField(String label, JTextField textField) {
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        panel.add(new JLabel(label));
        panel.add(textField);
        return panel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == signupButton) {
            String userID = userIdTextInfo.getText();
            String nickName = nicknameTextInfo.getText();
            signupController.execute(userID, nickName);
        } else if (e.getSource() == skipToLoginButton) {
            signupController.skip();
        }
    }





    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        SignupState state =  (SignupState) evt.getNewValue();
        setFields(state);}


    private void setFields(SignupState state) {
        userIdTextInfo.setText(state.getUser_id());
    }
}