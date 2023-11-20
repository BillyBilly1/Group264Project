package use_case.clear_users;

public class ClearInteractor implements ClearInputBoundary {
    private ClearOutputBoundary outputBoundary;
    private  ClearUserDataAccessInterface dataAccess;

    public ClearInteractor(ClearOutputBoundary outputBoundary, ClearUserDataAccessInterface dataAccess) {
        this.outputBoundary = outputBoundary;
        this.dataAccess =  dataAccess;
    }

    public void execute() {
        ClearOutputData clearOutputData = new ClearOutputData(true, dataAccess.get_Dlist());
        dataAccess.clearAllUsers();
        outputBoundary.prepareSuccessView(clearOutputData);
    }
}

