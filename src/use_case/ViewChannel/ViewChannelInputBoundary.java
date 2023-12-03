package use_case.ViewChannel;

import use_case.CreateChannel.CreateChannelInputData;

public interface ViewChannelInputBoundary {
    void execute(ViewChannelInputData inputData);
}
