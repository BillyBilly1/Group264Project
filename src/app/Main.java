package app;

import interface_adapter.Channel.ChannelViewModel;
import interface_adapter.translation.TranslateController;
import interface_adapter.translation.TranslatePresenter;
import interface_adapter.translation.TranslateViewModel;
import use_case.translate.TranslateInputBoundary;
import use_case.translate.TranslateInteractor;
import use_case.translate.TranslateOutputBoundary;
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

        ChannelViewModel channelviewModel = new ChannelViewModel();

        TranslateViewModel translateViewModel = new TranslateViewModel();

        TranslateOutputBoundary translateOutputBoundary = new TranslatePresenter(channelviewModel, translateViewModel);

        TranslateInputBoundary translateInputBoundary = new TranslateInteractor(translateOutputBoundary, translateViewModel);

        TranslateController translateController = new TranslateController(translateInputBoundary);


        ChannelView channelView = new ChannelView(channelviewModel, translateViewModel, translateController);

        frame.getContentPane().add(channelView);

        frame.pack();

        frame.setLocationRelativeTo(null);

        frame.setVisible(true);
    }
}
