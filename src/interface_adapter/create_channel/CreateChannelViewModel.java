package interface_adapter.create_channel;

import interface_adapter.Signup.SignupState;
import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class CreateChannelViewModel extends ViewModel {

    public static final String CREATE_CHANNEL_TITLE = "Create Channel View";
    public static final String CHANNEL_URL_LABEL = "Enter Channel_Url";
    public static final String CHANNEL_NAME_LABEL = "Enter ChannelName";
    public static final String CREATE_CHANNEL_BUTTON_LABLE = "Create";
    public static final String CANCEL_BUTTON_LABEL = "Cancel";

    private String successMessage = "";



    private CreateChannelState state = new CreateChannelState();


    public CreateChannelViewModel() {
        super("create channel");
    }

    public void setState(CreateChannelState state) {
        this.state = state;
    }



    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    @Override
    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }

    @Override
    public void addPropertyChangedListener(PropertyChangeListener listener) {

    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public CreateChannelState getState() {
        return state;
    }

    public void setSuccessMessage(String s) {
        this.successMessage = s;
    }

    public String getSuccessMessage() {
        return this.successMessage;    }
}


