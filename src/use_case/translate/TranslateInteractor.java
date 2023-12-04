package use_case.translate;

import api.YandexAPI;
import interface_adapter.Channel.ChannelViewModel;
import interface_adapter.translation.TranslateState;
import interface_adapter.translation.TranslateViewModel;

import java.io.IOException;
import java.util.ArrayList;

public class TranslateInteractor implements TranslateInputBoundary {

    private final TranslateOutputBoundary outputBoundary;
    private final TranslateViewModel translateViewModel;

    private ChannelViewModel viewModel;

    public TranslateInteractor(TranslateOutputBoundary outputBoundary, TranslateViewModel translateViewModel) {
        this.outputBoundary = outputBoundary;
        this.translateViewModel = translateViewModel;
    }


    @Override
    public void translate(TranslateInputData translateInputData) throws IOException {
        ArrayList<String> sourceText = translateInputData.getSourceText();
        String targetLang = translateInputData.getTargetLang();

        TranslateState state = translateViewModel.getState();
        state.setTranslating(true);
        state.setTargetLang(targetLang);

        try {
            ArrayList<String> translatedText = new ArrayList<>();
            for (String text : sourceText) {
                YandexAPI yandexAPI = new YandexAPI(targetLang, text);
                translatedText.add(yandexAPI.translate() + " Translated by Yandex Translate");
            }
            state.setTranslatedText(translatedText);
            state.setTranslationSuccessful(true);
            state.setError(null);
        } catch (Exception e) {
            state.setTranslationSuccessful(false);
            state.setError(e.getMessage());
        } finally {
            state.setTranslating(false);
            state.setTimestamp(System.currentTimeMillis());
            outputBoundary.presentTranslationResult(state);
        }
    }
}