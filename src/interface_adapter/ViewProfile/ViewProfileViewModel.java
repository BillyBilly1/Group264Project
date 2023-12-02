package interface_adapter.ViewProfile;

import interface_adapter.ViewManagerModel;
import interface_adapter.ViewModel;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class ViewProfileViewModel extends ViewModel{

    public final String PROFILE_TITLE = "Profile";
    public final String USER_ID_LABEL = "User_id:";
    public final String NICKNAME_LABEL = "Nickname:";
    public final String BACK_BUTTON = "Back to Menu";

    private ViewProfileState state = new ViewProfileState();

    public ViewProfileViewModel() {
        super("Profile");
    }

    public void setState(ViewProfileState state) {
        ViewProfileState oldState = this.state;
        this.state = state;
        firePropertyChanged();

    }

    public ViewProfileState getState() {return this.state;}

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);




    @Override
    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }

    @Override
    public void addPropertyChangedListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }
}
