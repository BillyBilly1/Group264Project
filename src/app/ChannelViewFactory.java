package app;

import data_access.SendMessageDataAccessObject;
import interface_adapter.Channel.ChannelViewModel;
import interface_adapter.send_message.SendMessageController;
import interface_adapter.send_message.SendMessagePresenter;
import interface_adapter.send_message.SendMessageViewModel;
import interface_adapter.translation.TranslateController;
import interface_adapter.translation.TranslatePresenter;
import interface_adapter.translation.TranslateViewModel;
import use_case.SendMessage.SendMessageDataAccessInterface;
import use_case.SendMessage.SendMessageInputBoundary;
import use_case.SendMessage.SendMessageInteractor;
import use_case.SendMessage.SendMessageOutputBoundary;
import use_case.translate.TranslateInputBoundary;
import use_case.translate.TranslateInteractor;
import use_case.translate.TranslateOutputBoundary;
import view.ChannelView;

import java.io.IOException;

public class ChannelViewFactory {


    public static ChannelView create(ChannelViewModel channelViewModel) throws IOException {
        TranslateViewModel translateViewModel = new TranslateViewModel();
        SendMessageViewModel sendMessageViewModel = new SendMessageViewModel();
        TranslateController translateController = createTranslateController(channelViewModel, translateViewModel);
        SendMessageController sendMessageController = createSendMessageController(channelViewModel);

        return new ChannelView(channelViewModel,
                translateViewModel, sendMessageViewModel, translateController, sendMessageController);


    }


    private static TranslateController createTranslateController(
            ChannelViewModel channelViewModel, TranslateViewModel translateViewModel) {

        TranslateOutputBoundary translateOutputBoundary = new TranslatePresenter(channelViewModel,
                translateViewModel);
        TranslateInputBoundary translateInputBoundary = new TranslateInteractor(
                translateOutputBoundary, translateViewModel);
        TranslateController translateController = new TranslateController(translateInputBoundary);
    return translateController;}


    private static SendMessageController createSendMessageController(ChannelViewModel channelViewModel) throws IOException {
        SendMessageDataAccessInterface sendMessageDataAccessInterface = new SendMessageDataAccessObject();
        SendMessageOutputBoundary sendMessageOutputBoundary = new SendMessagePresenter(channelViewModel);
        SendMessageInputBoundary sendMessageInputBoundary =
                new SendMessageInteractor(sendMessageDataAccessInterface, sendMessageOutputBoundary);
        SendMessageController sendMessageController = new SendMessageController(sendMessageInputBoundary);
        return sendMessageController;
    }
}
