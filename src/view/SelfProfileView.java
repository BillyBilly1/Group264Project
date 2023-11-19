package view;

import javax.swing.*;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class SelfProfileView {
    public SelfProfileView(){
        JFrame frame = new JFrame("Profile");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 600);

        JPanel panel = new JPanel();
        frame.add(panel);
        placeComponents(panel);

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public static void placeComponents(JPanel panel){
        panel.setLayout(null);

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

    }

}
