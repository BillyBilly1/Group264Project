package interface_adapter.viewChannel;

import app.ChannelViewFactory;
import entity.Channel.*;
import interface_adapter.Menu.MenuViewModel;
import use_case.ViewChannel.ViewChannelOutputBoundary;
import use_case.ViewChannel.ViewChannelOutputData;
import interface_adapter.Channel.ChannelViewModel;
import view.ChannelView;

import javax.swing.*;
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
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        frame.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                // Perform any additional cleanup if required
                channelView.onClose(); // Call onClose method to handle any specific view cleanups
            }
        });

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