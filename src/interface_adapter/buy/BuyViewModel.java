package interface_adapter.buy;

import interface_adapter.buy.BuyState;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class BuyViewModel extends ViewModel{
    public static final String Buy_Button_Label = "Buy";

    private BuyState buyState = new BuyState();

    public BuyViewModel() {super("Buy Stock"); }

    public void SetState (BuyState buyState) {
        this.buyState = buyState;
    }

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.buyState);
    }


    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public BuyState getBuyState() {
        return buyState;
    }

}
