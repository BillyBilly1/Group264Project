package app;

import interface_adapter.Channel.ChannelViewModel;
import view.ChannelView;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }

    private static void createAndShowGUI() {
        JFrame frame = new JFrame("YouChat");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        ChannelViewModel viewModel = new ChannelViewModel();

        ChannelView channelView = new ChannelView(viewModel);

        frame.getContentPane().add(channelView);

        frame.pack();

        frame.setLocationRelativeTo(null);

        frame.setVisible(true);
    }
}
