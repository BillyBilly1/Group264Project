package use_case.SendMessage;

import entity.Message.Message;
import interface_adapter.Channel.ChannelViewModel;
import use_case.SendMessageInputdata;

import java.util.ArrayList;

public class SendMessageInteractor implements  SendMessageInputBoundary {

    private final SendMessageDataAccessInterface sendMessageDataAccessObject;

    private final SendMessageOutputBoundary sendMessagePresenter;


    public SendMessageInteractor(SendMessageDataAccessInterface sendMessageDataAccessObject,
                                 SendMessageOutputBoundary sendMessagePresenter) {
        this.sendMessageDataAccessObject = sendMessageDataAccessObject;
        this.sendMessagePresenter = sendMessagePresenter;
    }


    @Override
    public void send(SendMessageInputdata sendMessageInputdata) {

        String userID = sendMessageInputdata.getUserID();
        String message = sendMessageInputdata.getMessage();
        String channelType = sendMessageInputdata.getChannelType();
        String channelUrl = sendMessageInputdata.getChannelUrl();

        if(!sendMessageDataAccessObject.sendMessage(userID, message, channelType, channelUrl)) {

            sendMessagePresenter.prepareFailView();
        }
        else {
            sendMessagePresenter.prepareSuccessView();
        }
    }

    @Override
    public void receive(SendMessageInputdata sendMessageInputdata) {
        String channelType = sendMessageInputdata.getChannelType();
        String channelUrl = sendMessageInputdata.getChannelUrl();
        long messageTs = sendMessageInputdata.getMessageTs();
        ArrayList<Message> obj = sendMessageDataAccessObject.getMessages(channelType, channelUrl, messageTs);
        if (obj == null) {sendMessagePresenter.prepareFailView();}
        else {
                SendMessageOutputdata sendMessageOutputdata = new SendMessageOutputdata(obj);
                sendMessagePresenter.receivedmessage(sendMessageOutputdata);
        }
    }

}
