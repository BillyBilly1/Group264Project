package interface_adapter.translation;

import use_case.translate.TranslateInputBoundary;
import use_case.translate.TranslateInputData;

import java.io.IOException;
import java.util.ArrayList;

public class TranslateController {

    final TranslateInputBoundary translateInteractor;

    public TranslateController(TranslateInputBoundary translateInteractor) {
        this.translateInteractor = translateInteractor;
    }


    public void translate(String targetLang, ArrayList<String> sourceText) throws IOException {
        TranslateInputData translateInputData = new TranslateInputData(targetLang, sourceText);
        translateInteractor.translate(translateInputData);
    }

}
