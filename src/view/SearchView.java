package view;

import interface_adapter.searching.SearchController;
import interface_adapter.searching.SearchState;
import interface_adapter.searching.SearchViewModel;
import interface_adapter.sell.SellState;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class SearchView extends JPanel implements ActionListener, PropertyChangeListener {
    private final SearchController searchController;

    private final SearchViewModel searchViewModel;

    private final JTextField stocknameInputField = new JTextField(15);

    private final JButton search;

    public SearchView(SearchController controller, SearchViewModel searchViewModel) {
        this.searchController = controller;
        this.searchViewModel = searchViewModel;
        searchViewModel.addPropertyChangeListener(this);

        JLabel title = new JLabel(searchViewModel.TITLE_LABEL);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        LabelTextPanel stocknameInfo = new LabelTextPanel(
                new JLabel(SearchViewModel.STOCKNAME_LABEL), stocknameInputField);

        JPanel buttons = new JPanel();
        search = new JButton(SearchViewModel.Search_Button_Label);
        buttons.add(search);

        search.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(search)){
                            SearchState currentState = searchViewModel.getSearchState();
                            searchController.search(currentState.getSearchName());
                        }
                    }
                }
        );

        stocknameInputField.addKeyListener(
                new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {
                        SearchState currentState = searchViewModel.getSearchState();
                        String text = stocknameInputField.getText() + e.getKeyChar();
                        currentState.setSearchName(text);
                        searchViewModel.setState(currentState);
                    }

                    @Override
                    public void keyPressed(KeyEvent e) {
                    }

                    @Override
                    public void keyReleased(KeyEvent e) {
                    }
                });
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        SearchState state = (SearchState) evt.getNewValue();
        if (state.getSearchError() != null) {
            JOptionPane.showMessageDialog(this, state.getSearchError());
        }
    }
}
