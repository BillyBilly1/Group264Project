package use_case.translate;

import java.io.IOException;

public interface TranslateInputBoundary {
    void translate(TranslateInputData translateInputData) throws IOException;

}
