package view;

import javax.swing.*;
import java.awt.Color;
import java.awt.Cursor;

public class LoginView {
    public LoginView() {
        JFrame frame = new JFrame("YouChat - Login");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 600);

        JPanel panel = new JPanel();
        frame.add(panel);
        placeComponents(panel);

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private static void placeComponents(JPanel panel) {
        panel.setLayout(null);

        JLabel titleLabel = new JLabel("Login", SwingConstants.CENTER);
        titleLabel.setBounds(150, 100, 100, 30);
        panel.add(titleLabel);

        JLabel userIdLabel = new JLabel("User ID:");
        userIdLabel.setBounds(10, 150, 150, 30);
        panel.add(userIdLabel);

        JTextField userIdText = new JTextField(20);
        userIdText.setBounds(100, 150, 250, 40);
        panel.add(userIdText);

        JLabel nicknameLabel = new JLabel("Nickname:");
        nicknameLabel.setBounds(10, 200, 150, 30);
        panel.add(nicknameLabel);

        JTextField nicknameText = new JTextField(20);
        nicknameText.setBounds(100, 200, 250, 40);
        panel.add(nicknameText);

        JButton loginButton = new JButton("Login");
        loginButton.setBounds((400 / 2) - (100 / 2), 300, 100, 40);
        panel.add(loginButton);

        // Add action listener for loginButton
        // ...
    }
}

