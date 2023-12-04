package interface_adapter.send_message;

import entity.Message.Message;
import interface_adapter.Channel.ChannelViewModel;
import use_case.SendMessage.SendMessageOutputBoundary;
import use_case.SendMessage.SendMessageOutputdata;

import javax.sound.sampled.*;
import javax.swing.*;
import java.util.ArrayList;

public class SendMessagePresenter implements SendMessageOutputBoundary {

    private final ChannelViewModel channelViewModel;

    public SendMessagePresenter(ChannelViewModel channelViewModel) {
        this.channelViewModel = channelViewModel;
    }

    @Override
    public void prepareFailView() {
        String message = "Sorry, you were unable to send the message. " +
                "Please try again or check if you have been removed from this channel \n" +
                "A message shouldn't start or end with \" (a double quotation mark).";
        JOptionPane.showMessageDialog(null, message, "Message Sending Failed", JOptionPane.ERROR_MESSAGE);
    }

    @Override
    public void prepareSuccessView() {

    }

    @Override
    public void receivedmessage(SendMessageOutputdata sendMessageOutputdata) throws InterruptedException {
        ArrayList<Message> messageList = sendMessageOutputdata.getMessageList();
        long lastMessageTs = 0;
        for (Message message : messageList) {
            String messageContent = message.getMessage();
            String userID = message.getUser_id();
            long messageTs = message.getCreateAt();
            if (messageTs > lastMessageTs) {
                lastMessageTs = messageTs;
            }
            channelViewModel.addMessage(messageContent, userID, messageTs);
        }
        channelViewModel.setLastMessageTS(lastMessageTs);
    }
}
