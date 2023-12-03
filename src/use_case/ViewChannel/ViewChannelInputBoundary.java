package use_case.ViewChannel;

import use_case.CreateChannel.CreateChannelInputData;

import java.io.IOException;

public interface ViewChannelInputBoundary {
    void execute(ViewChannelInputData inputData) throws IOException;
}
