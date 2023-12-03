package use_case.ViewChannel;

import use_case.CreateChannel.CreateChannelOutputData;

public interface ViewChannelOutputBoundary {
    void prepareSuccessView(ViewChannelOutputData outputData);
    void prepareFailView(String error);
}
