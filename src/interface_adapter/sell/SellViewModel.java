package interface_adapter.sell;

import interface_adapter.ViewModel;
import interface_adapter.sell.SellState;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
public class SellViewModel extends ViewModel{
    public static final String TITLE_LABEL = "Sell View";
    public static final String SELL_BUTTON_LABEL = "Sell";
    public static final String AMOUNT_LABEL = "Amount";
    public static final String HOME = "Go to Home Page";
    public static final String GOBACK = "Go back";

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
