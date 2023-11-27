package use_case.translate;

import java.util.ArrayList;

public class TranslateInputData {

    final private String targetLang;

    final private ArrayList<String> sourceText;

    public TranslateInputData(String targetLang, ArrayList<String> sourceText) {
        this.targetLang = targetLang;
        this.sourceText = sourceText;
    }

    public String getTargetLang() {
        return targetLang;
    }

    public ArrayList<String> getSourceText() {
        return sourceText;
    }
}
