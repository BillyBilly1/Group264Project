package use_case.ListChannel;

public class ListChannelInteractor implements ListChannelInputBoundary{

    final ListChannelDataAccessInterface userDataAccessObject;
    final ListChannelOutputBoundary listChannelPresenter;

    public ListChannelInteractor(ListChannelDataAccessInterface userDataAccessObject,
                                 ListChannelOutputBoundary listChannelPresenter){
        this.userDataAccessObject = userDataAccessObject;
        this.listChannelPresenter = listChannelPresenter;
    }

    @Override
    public void execute(ListChannelInputData listChannelInputData){
        String user_id = listChannelInputData.getUser_id();
        if(userDataAccessObject.get_username(user_id) == null){
            listChannelPresenter.prepareFailView(user_id + ": User does not exist.");
        } else {
            ListChannelOutputData listChannelOutputData = new ListChannelOutputData(userDataAccessObject.list_channel(listChannelInputData.getUser_id()));
            listChannelPresenter.prepareSuccessView(listChannelOutputData);


                    }

    }
}
