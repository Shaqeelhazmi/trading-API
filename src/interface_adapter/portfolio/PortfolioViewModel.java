package interface_adapter.portfolio;

import interface_adapter.ViewModel;
import interface_adapter.sell.SellState;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class PortfolioViewModel extends ViewModel{
    public final String TITLE_LABEL = "Portfolio View";

    private PortfolioState portfolioState = new PortfolioState();

    public PortfolioViewModel() {super("Portfolio View"); }

    public void setState (PortfolioState portfolioState) {
        this.portfolioState = portfolioState;
    }

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.portfolioState);
    }


    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public PortfolioState getState() {
        return portfolioState;
    }
}
