package interface_adapter.translation;

import java.util.ArrayList;

public class TranslateState {

    private boolean isTranslating;
    private ArrayList<String> sourceText; // 文本列表
    private String sourceLang;
    private String targetLang;
    private ArrayList<String> translatedText;
    private boolean translationSuccessful;
    private String error;
    private long timestamp;

    public TranslateState() {
        // Initialize default values
        this.isTranslating = false;
        this.translationSuccessful = false;
        this.sourceText = new ArrayList<String>();
        this.sourceLang = "";
        this.targetLang = "";
        this.translatedText = new ArrayList<String>();
        this.error = "";
        this.timestamp = System.currentTimeMillis(); // Default to current time
    }

    // Getters
    public boolean isTranslating() {
        return isTranslating;
    }

    public ArrayList<String> getSourceText() {
        return sourceText;
    }

    public String getSourceLang() {
        return sourceLang;
    }

    public String getTargetLang() {
        return targetLang;
    }

    public ArrayList<String> getTranslatedText() {
        return translatedText;
    }

    public boolean isTranslationSuccessful() {
        return translationSuccessful;
    }

    public String getError() {
        return error;
    }

    public long getTimestamp() {
        return timestamp;
    }

    // Setters
    public void setTranslating(boolean translating) {
        isTranslating = translating;
    }

    public void setSourceText(ArrayList<String> sourceText) {
        this.sourceText = sourceText;
    }

    public void setSourceLang(String sourceLang) {
        this.sourceLang = sourceLang;
    }

    public void setTargetLang(String targetLang) {
        this.targetLang = targetLang;
    }

    public void setTranslatedText(ArrayList<String> translatedText) {
        this.translatedText = translatedText;
    }

    public void setTranslationSuccessful(boolean translationSuccessful) {
        this.translationSuccessful = translationSuccessful;
    }

    public void setError(String error) {
        this.error = error;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
}
