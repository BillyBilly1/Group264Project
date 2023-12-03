package interface_adapter.send_message;

import use_case.SendMessage.SendMessageInputBoundary;
import use_case.SendMessageInputdata;

public class SendMessageController {

    private final SendMessageInputBoundary sendMessageInteractor;

    public SendMessageController(SendMessageInputBoundary sendMessageInteractor) {
        this.sendMessageInteractor = sendMessageInteractor;
    }

    public void send(String userID, String message, String channelURL) {
        SendMessageInputdata sendMessageInputdata = new SendMessageInputdata(userID, message, channelURL);
        sendMessageInteractor.send(sendMessageInputdata);
    }
}
