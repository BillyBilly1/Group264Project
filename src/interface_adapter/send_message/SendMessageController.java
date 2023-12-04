package interface_adapter.send_message;

import use_case.SendMessage.SendMessageInputBoundary;
import use_case.SendMessageInputdata;

public class SendMessageController {

    private final SendMessageInputBoundary sendMessageInteractor;

    public SendMessageController(SendMessageInputBoundary sendMessageInteractor) {
        this.sendMessageInteractor = sendMessageInteractor;
    }

    public void send(String userID, String message, String channelURL, long messageTs) {
        SendMessageInputdata sendMessageInputdata = new SendMessageInputdata(userID, message, channelURL, messageTs);
        sendMessageInteractor.send(sendMessageInputdata);
    }


    public void receive(String usrID, String message, String channelURL, long messageTs) throws InterruptedException {
        SendMessageInputdata sendMessageInputdata = new SendMessageInputdata(usrID, message, channelURL, messageTs);
        sendMessageInteractor.receive(sendMessageInputdata);
    }
}
