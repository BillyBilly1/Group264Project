package view;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import interface_adapter.ViewManagerModel;
import interface_adapter.ViewProfile.ViewProfileState;
import interface_adapter.ViewProfile.ViewProfileViewModel;
import interface_adapter.ViewProfile.ViewProfileController;



public class SelfProfileView extends JPanel implements ActionListener, PropertyChangeListener {

    public final String viewname = "view profile";

    private  final ViewManagerModel viewManagerModel;
    private final ViewProfileViewModel viewProfileViewModel;
    private  JLabel userIdLabel;
    private JLabel nicknameLabel;
    private final JButton backbutton;

    public SelfProfileView(ViewManagerModel viewManagerModel, ViewProfileViewModel viewProfileViewModel){

        this.viewManagerModel = viewManagerModel;

        this.viewProfileViewModel = viewProfileViewModel;
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

        Border border = BorderFactory.createLineBorder(Color.BLACK, 2);


        JLabel titleLabel = new JLabel(viewProfileViewModel.PROFILE_TITLE, SwingConstants.CENTER);
        titleLabel.setBounds(150, 100, 100, 30);
        panel.add(titleLabel);

        JLabel userIdTextLabel = new JLabel("User ID: ");
        userIdTextLabel.setBounds(10, 150, 350, 45);
        userIdTextLabel.setBorder(border);
        this.userIdLabel = userIdTextLabel;
        panel.add(userIdTextLabel);

//        JTextField userIdText = new JTextField(20);
//        userIdText.setBounds(100, 150, 250, 40);
//        panel.add(userIdText);

        JLabel nicknameTextLabel = new JLabel("Nickname: ");
        nicknameTextLabel.setBounds(10, 240, 350, 45);
        nicknameTextLabel.setBorder(border);
        panel.add(nicknameTextLabel);
        this.nicknameLabel = nicknameTextLabel;
        panel.add(backbutton);

//        JTextField nicknameText = new JTextField(20);
//        nicknameText.setBounds(100, 200, 250, 40);
//        panel.add(nicknameText);

        this.add(panel);

        backbutton.addActionListener(this);}




    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if ("state".equals(evt.getPropertyName())) {
            ViewProfileState state = (ViewProfileState) evt.getNewValue();
            updateView(state);
        }
    }


    private void updateView(ViewProfileState state) {
        this.userIdLabel.setText("  Your ID: " + state.getUser_id());
        this.nicknameLabel.setText("  Nickname: " + state.getNickname());

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == backbutton) {
            viewManagerModel.setActiveView("menu");
            viewManagerModel.firePropertyChanged();


        }
    }
}

