package view;

import javax.swing.*;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class SignUpView {
    public static void main(String[] args) {
        JFrame frame = new JFrame("YouChat - Sign Up");
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

        JLabel titleLabel = new JLabel("Sign Up", SwingConstants.CENTER);
        int titleLabelWidth = 100;
        int titleLabelHeight = 30;
        titleLabel.setBounds(150, 100, titleLabelWidth, titleLabelHeight);
        panel.add(titleLabel);

        JLabel userIdLabel = new JLabel("User ID:");
        userIdLabel.setBounds(10, 150, 150, 30);
        panel.add(userIdLabel);

        JTextField userIdText = new JTextField(20);
        userIdText.setBounds(100, 150, 250, 40);
        panel.add(userIdText);

        // Nickname Label and TextField
        JLabel nicknameLabel = new JLabel("Nickname:");
        nicknameLabel.setBounds(10, 200, 150, 30);
        panel.add(nicknameLabel);

        JTextField nicknameText = new JTextField(20);
        nicknameText.setBounds(100, 200, 250, 40);
        panel.add(nicknameText);


        JButton submitButton = new JButton("Submit");

        // Assuming the panel size is 300x200 as per your initial setup
        int panelWidth = 400;
        int buttonWidth = 100; // You can adjust this based on your button size
        int buttonX = (panelWidth / 2) - (buttonWidth / 2); // Center position

        submitButton.setBounds(buttonX, 300, buttonWidth, 40);
        panel.add(submitButton);

        // Add action listener for submitButton

        JLabel loginLabel = new JLabel("<html><a href=''>Already have an account? Login.</a></html>", SwingConstants.CENTER);
        loginLabel.setForeground(Color.BLUE);
        loginLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        int loginLabelWidth = 200;
        int loginLabelHeight = 20;
        loginLabel.setBounds(100, 350, loginLabelWidth, loginLabelHeight);
        panel.add(loginLabel);

        loginLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Open LoginView
                //new LoginView(); // Make sure LoginView is set up to be instantiated like this
                // Close current SignUpView
                SwingUtilities.getWindowAncestor(panel).dispose();
            }
        });
    }
}
