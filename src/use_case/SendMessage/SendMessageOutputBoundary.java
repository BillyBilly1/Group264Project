package use_case.SendMessage;

public interface SendMessageOutputBoundary {
    void prepareFailView();

    void prepareSuccessView();

    void receivedmessage(SendMessageOutputdata sendMessageOutputdata);
}
