package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import interface_adapter.ViewProfile.ViewProfileState;
import interface_adapter.ViewProfile.ViewProfileViewModel;
import interface_adapter.ViewProfile.ViewProfileController;



public class SelfProfileView extends JPanel implements ActionListener, PropertyChangeListener {

    public final String viewname = "view profile";
    private final ViewProfileViewModel viewProfileViewModel;
    private final ViewProfileController viewProfileController;
    final JLabel userIdLabel = new JLabel();
    final JLabel nicknameLabel = new JLabel();
    private final JButton backbutton;

    public SelfProfileView(ViewProfileViewModel viewProfileViewModel, ViewProfileController viewProfileController){

        this.viewProfileViewModel = viewProfileViewModel;
        this.viewProfileController = viewProfileController;
        this.viewProfileViewModel.addPropertyChangedListener(this);

        JButton backbutton = new JButton(viewProfileViewModel.BACK_BUTTON);
        backbutton.setBounds((400 / 2) - (100 / 2), 300, 100, 40);
        this.backbutton = backbutton;
        this.setLayout(new BorderLayout());
        placeComponents();

//        JFrame frame = new JFrame("Profile");
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.setSize(400, 600);
//
//        JPanel panel = new JPanel();
//        frame.add(panel);
//        placeComponents(panel);
//
//        frame.setLocationRelativeTo(null);
//        frame.setVisible(true);
    }

    public void placeComponents(){
        JPanel panel = new JPanel();
        panel.setLayout(null);

        JLabel titleLabel = new JLabel(viewProfileViewModel.PROFILE_TITLE, SwingConstants.CENTER);
        titleLabel.setBounds(150, 100, 100, 30);
        panel.add(titleLabel);

        JLabel userIdTextLabel = new JLabel("User ID:");
        userIdTextLabel.setBounds(10, 150, 150, 30);
        panel.add(userIdTextLabel);
        panel.add(userIdLabel);

//        JTextField userIdText = new JTextField(20);
//        userIdText.setBounds(100, 150, 250, 40);
//        panel.add(userIdText);

        JLabel nicknameTextLabel = new JLabel("Nickname:");
        nicknameTextLabel.setBounds(10, 200, 150, 30);
        panel.add(nicknameTextLabel);
        panel.add(nicknameLabel);

//        JTextField nicknameText = new JTextField(20);
//        nicknameText.setBounds(100, 200, 250, 40);
//        panel.add(nicknameText);

        this.add(panel);

        backbutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == backbutton) {

                }
            }
        });

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("state")) {
            ViewProfileState state = (ViewProfileState) evt.getNewValue();
            updateView(state);
        }
    }

    private void updateView(ViewProfileState state) {
        userIdLabel.setText(state.getUser_id());
        nicknameLabel.setText(state.getNickname());
    }
}

