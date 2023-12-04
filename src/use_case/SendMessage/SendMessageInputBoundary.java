package use_case.SendMessage;

import use_case.SendMessageInputdata;

public interface SendMessageInputBoundary {
    void send(SendMessageInputdata sendMessageInputdata);

    void receive(SendMessageInputdata sendMessageInputdata) throws InterruptedException;
}
