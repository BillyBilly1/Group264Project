package use_case.CreateChannel;

public interface CreateChannelOutputBoundary {

    void prepareSuccessView(CreateChannelOutputData outputData);

    void prepareFailView(String error);
}
