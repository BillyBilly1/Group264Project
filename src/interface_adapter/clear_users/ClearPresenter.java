package interface_adapter.clear_users;


import interface_adapter.ViewManagerModel;
import use_case.clear_users.ClearOutputBoundary;
import use_case.clear_users.ClearOutputData;

public class ClearPresenter implements ClearOutputBoundary {

    private final ClearViewModel clearViewModel;

    private ViewManagerModel viewManagerModel;
    public ClearPresenter(ViewManagerModel viewManagerModel, ClearViewModel clearViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.clearViewModel = clearViewModel;
    }

    @Override
    public void prepareSuccessView(ClearOutputData response) {
        updateViewModelState(response);
        System.out.println(clearViewModel.getState().getDeadList());
        activateClearView();
    }

    private void updateViewModelState(ClearOutputData response){
        ClearState clearState = clearViewModel.getState();
        clearState.setDeadList(response);
        clearViewModel.setState(clearState);
        clearViewModel.firePropertyChanged();
        System.out.println(clearState.getDeadList());
    }

    private void activateClearView(){
        viewManagerModel.setActiveView(clearViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

}
