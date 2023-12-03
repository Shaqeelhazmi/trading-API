package interface_adapter.buy;

import interface_adapter.ViewModel;
import interface_adapter.buy.BuyState;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class BuyViewModel extends ViewModel {
    public static final String Buy_Button_Label = "Buy";

    public static final String Amount_Label = "Amount";

    public static final String HOME = "Go to Main Menu";

    public static final String GOBACK = "Back";

    private BuyState buyState = new BuyState();

    public BuyViewModel() {super("Buy Stock"); }

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

    public void setBuyState(BuyState currentState) {
        this.buyState = currentState;
    }
}
