package app;

import data_access.FileUserDataAccessObject;
import data_access.SendMessageDataAccessObject;
import interface_adapter.Channel.ChannelViewModel;
import interface_adapter.Invite_Member.InviteMemberController;
import interface_adapter.Invite_Member.InviteMemberPresenter;
import interface_adapter.Remove_Member.RemoveMemberController;
import interface_adapter.Remove_Member.RemoveMemberPresenter;
import interface_adapter.send_message.SendMessageController;
import interface_adapter.send_message.SendMessagePresenter;
import interface_adapter.send_message.SendMessageViewModel;
import interface_adapter.translation.TranslateController;
import interface_adapter.translation.TranslatePresenter;
import interface_adapter.translation.TranslateViewModel;
import use_case.InviteMember.InviteMemberDataAccessInterface;
import use_case.InviteMember.InviteMemberInputBoundary;
import use_case.InviteMember.InviteMemberInteractor;
import use_case.InviteMember.InviteMemberOutputBoundary;
import use_case.RemoveMember.RemoveMemberDataAccessInterface;
import use_case.RemoveMember.RemoveMemberInputBoundary;
import use_case.RemoveMember.RemoveMemberInteractor;
import use_case.RemoveMember.RemoveMemberOutputBoundary;
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


    public ChannelViewFactory() throws IOException {
    }

    public static ChannelView create(ChannelViewModel channelViewModel) throws IOException {
        TranslateViewModel translateViewModel = new TranslateViewModel();
        SendMessageViewModel sendMessageViewModel = new SendMessageViewModel();
        TranslateController translateController = createTranslateController(channelViewModel, translateViewModel);
        SendMessageController sendMessageController = createSendMessageController(channelViewModel);
        InviteMemberController inviteMemberController = createIniviteController(channelViewModel);
        RemoveMemberController removeMemberController = createRemoveController(channelViewModel);

        return new ChannelView(channelViewModel,
                translateViewModel, sendMessageViewModel, translateController,
                sendMessageController, inviteMemberController, removeMemberController);


    }

    private static InviteMemberController createIniviteController(ChannelViewModel
                                                                          channelViewModel) throws IOException {
        InviteMemberDataAccessInterface inviteMemberDataAccessInterface = new FileUserDataAccessObject();
        InviteMemberOutputBoundary inviteMemberOutputBoundary = new InviteMemberPresenter(channelViewModel);
        InviteMemberInputBoundary inviteMemberInputBoundary = new InviteMemberInteractor(
                inviteMemberDataAccessInterface, inviteMemberOutputBoundary);

        return new InviteMemberController(inviteMemberInputBoundary);
    }

    private static RemoveMemberController createRemoveController(ChannelViewModel
                                                                         channelViewModel) throws  IOException {
        RemoveMemberDataAccessInterface removeMemberDataAccessInterface = new FileUserDataAccessObject();
        RemoveMemberOutputBoundary removeMemberOutputBoundary = new RemoveMemberPresenter(channelViewModel);
        RemoveMemberInputBoundary removeMemberInputBoundary = new RemoveMemberInteractor(
                removeMemberDataAccessInterface, removeMemberOutputBoundary);
        return new RemoveMemberController(removeMemberInputBoundary);

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
