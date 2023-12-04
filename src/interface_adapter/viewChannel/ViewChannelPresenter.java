package interface_adapter.viewChannel;

import app.ChannelViewFactory;
import entity.Channel.*;
import interface_adapter.Menu.MenuViewModel;
import use_case.ViewChannel.ViewChannelOutputBoundary;
import use_case.ViewChannel.ViewChannelOutputData;
import interface_adapter.Channel.ChannelViewModel;
import view.ChannelView;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class ViewChannelPresenter implements ViewChannelOutputBoundary {

    private ChannelViewModel channelViewModel;


    public ViewChannelPresenter(){

        this.channelViewModel = new ChannelViewModel();
    }

    @Override
    public void prepareSuccessView(ViewChannelOutputData outputData) throws IOException {
        Channel channel = outputData.getChannel();
        String myID = outputData.getUserID();
        channelViewModel.setChannel(channel);
        channelViewModel.setMyID(myID);
        ChannelView channelView = ChannelViewFactory.create(channelViewModel);

        JFrame frame = new JFrame(channel.getChannelName());
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int windowWidth = (int) (screenSize.getWidth() * 0.70);
        int windowHeight = (int) (screenSize.getHeight() * 0.75);
        frame.setPreferredSize(new Dimension(windowWidth, windowHeight));
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        frame.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                // Perform any additional cleanup if required
                channelView.onClose(); // Call onClose method to handle any specific view cleanups
            }
        });

        File imgFile = new File("E:\\2F NOTE\\CSC207\\Group264Project\\src\\view\\DALL·E " +
                "2023-12-04 02.27.17 - Design an app icon for a chat software named 'YouChat'. The " +
                "icon should be modern and appealing, suitable for a messaging app. It should incorporate e.png");
        ImageIcon icon = new ImageIcon(imgFile.toURI().toURL());
        Image img = icon.getImage();
        frame.setIconImage(img);

        frame.getContentPane().add(channelView);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }



    @Override
    public void prepareFailView(String error) {
        System.out.println("Error");
    }
}