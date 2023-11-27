package use_case.translate;

import interface_adapter.translation.TranslateState;

public interface TranslateOutputBoundary {
    void presentTranslationResult(TranslateState state);
}
