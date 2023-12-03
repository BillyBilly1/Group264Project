package use_case.SendMessage;

import use_case.SendMessageInputdata;

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
            SendMessageOutputdata sendMessageOutputdata
                    = new SendMessageOutputdata("Sent successfully");
            sendMessagePresenter.prepareSuccessView(sendMessageOutputdata);
        }
    }

}
