package interface_adapter.sell;

import interface_adapter.ViewModel;
import interface_adapter.sell.SellState;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
public class SellViewModel extends ViewModel{
    public static final String Sell_Button_Label = "Sell";

    private SellState sellState = new SellState();

    public SellViewModel() {super("Sell Stock"); }

    public void setState (SellState sellState) {
        this.sellState = sellState;
    }

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.sellState);
    }


    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public SellState getSellState() {
        return sellState;
    }

}
