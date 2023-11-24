package interface_adapter.searching;

import interface_adapter.ViewModel;
import interface_adapter.searching.SearchState;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class SearchViewModel extends ViewModel {
    public final String TITLE_LABEL = "Search View";
    public static final String Search_Button_Label = "Search";

    private SearchState searchState = new SearchState();

    public SearchViewModel() {super("Search Stock"); }

    public void SetState (SearchState searchState) {
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
