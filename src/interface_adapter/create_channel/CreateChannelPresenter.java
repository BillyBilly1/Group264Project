package interface_adapter.create_channel;


import use_case.CreateChannel.CreateChannelOutputBoundary;
import use_case.CreateChannel.CreateChannelOutputData;
import view.CreateChannelView;

public class CreateChannelPresenter implements CreateChannelOutputBoundary {

    private CreateChannelView view;

    public CreateChannelPresenter(CreateChannelView view) {
        this.view = view;
    }

    @Override
    public void present(CreateChannelOutputData outputData) {
        if (outputData.isSuccess()) {
            // Here you could convert the output data to your view model if necessary
            view.displaySuccess(outputData.getChannelName(), outputData.getMessage());
        } else {
            view.displayError(outputData.getChannelName(), outputData.getMessage());
        }
    }
}

