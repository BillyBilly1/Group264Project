package interface_adapter.translation;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class TranslateViewModel extends ViewModel {

    private TranslateState state;

    public TranslateViewModel() {
        super("translate");
        this.state = new TranslateState(); // 初始化 TranslateState
    }

    public void setState(TranslateState state) {
        TranslateState oldState = state;
        this.state = state;
        support.firePropertyChange("state", oldState, this.state);
    }

    public TranslateState getState() {
        return state;
    }


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
