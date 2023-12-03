package interface_adapter.searching;

import interface_adapter.ViewModel;
import interface_adapter.searching.SearchState;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class SearchViewModel extends ViewModel {
    public final String TITLE_LABEL = "Search View";
    public static final String Search_Button_Label = "Search";

    public static final String STOCKNAME_LABEL = "Search stockname";

    public static final String STOCK = "Buy Stock";

    private SearchState searchState = new SearchState();

    public SearchViewModel() {super("Search Stock"); }

    public void setState (SearchState searchState) {
        this.searchState = searchState;
    }

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.searchState);
    }


    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public SearchState getSearchState() {
        return searchState;
    }

}
