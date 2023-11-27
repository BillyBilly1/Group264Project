package interface_adapter.translation;

import interface_adapter.Channel.ChannelViewModel;
import use_case.translate.TranslateOutputBoundary;

import javax.swing.*;

public class TranslatePresenter implements TranslateOutputBoundary {

    private final ChannelViewModel channelViewModel;

    private final TranslateViewModel translateViewModel;

    public TranslatePresenter(ChannelViewModel channelViewModel, TranslateViewModel translateViewModel) {
        this.channelViewModel = channelViewModel;
        this.translateViewModel = translateViewModel;
    }


    @Override
    public void presentTranslationResult(TranslateState state) {

        if (state.isTranslationSuccessful()) {
            channelViewModel.updateTranslatedText(translateViewModel.getState().getTranslatedText());
        }

        else {
            JOptionPane.showMessageDialog(null, "Failed: " + state.getError(),
                    "Translation Erro", JOptionPane.ERROR_MESSAGE);

        }


    }
}
