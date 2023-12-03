package use_case.ViewChannel;

import use_case.CreateChannel.CreateChannelOutputData;

import java.io.IOException;

public interface ViewChannelOutputBoundary {
    void prepareSuccessView(ViewChannelOutputData outputData) throws IOException;
    void prepareFailView(String error);
}
