package interface_adapter.stock;

import interface_adapter.buy.BuyState;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class StockViewModel {
    public static final String Buy_Button_Label = "Buy";
    public static final String Sell_Button_Label = "Sell";
    public static final String Amount_Label_Buy = "Amount";
    public static final String Amount_Label_Sell = "Amount";

    public static final String HOME = "Go to Main Menu";

    public static final String GOBACK = "Back";

    private StockState stockState = new StockState();

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.stockState);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public StockState getStockState() {
        return stockState;
    }

    public void setStockState(StockState currentState) {
        this.stockState = currentState;
    }
}
