package interface_adapter.send_message;

import entity.Message.Message;
import interface_adapter.Channel.ChannelViewModel;
import use_case.SendMessage.SendMessageOutputBoundary;
import use_case.SendMessage.SendMessageOutputdata;

import java.util.ArrayList;

public class SendMessagePresenter implements SendMessageOutputBoundary {

    private final ChannelViewModel channelViewModel;

    public SendMessagePresenter(ChannelViewModel channelViewModel) {
        this.channelViewModel = channelViewModel;
    }

    @Override
    public void prepareFailView() {

    }

    @Override
    public void prepareSuccessView() {

    }

    @Override
    public void receivedmessage(SendMessageOutputdata sendMessageOutputdata) {
        ArrayList<Message> messageList = sendMessageOutputdata.getMessageList();
        System.out.println("pre触发了");
        long messageTs = 0;

        for (Message message: messageList) {
            String messageContent = message.getMessage();
            String userID = message.getUser_id();
            messageTs = message.getCreateAt();
            channelViewModel.addMessage(messageContent, userID, messageTs);

        }
        channelViewModel.setLastMessageTS(messageTs);

    }
}
