package use_case.ListChannel;

public interface ListChannelOutputBoundary {

    void prepareSuccessView(ListChannelOutputData channel_list) ;

    void prepareFailView(String error);
}
