package interface_adapter.send_message;

import use_case.SendMessage.SendMessageOutputBoundary;
import use_case.SendMessage.SendMessageOutputdata;

public class SendMessagePresenter implements SendMessageOutputBoundary {

    @Override
    public void prepareFailView() {

    }

    @Override
    public void prepareSuccessView(SendMessageOutputdata sendMessageOutputdata) {

    }
}
