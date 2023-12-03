package view;

import interface_adapter.ViewManagerModel;
import interface_adapter.logged_in.LoggedInViewModel;
import interface_adapter.portfolio.PortfolioController;
import interface_adapter.portfolio.PortfolioState;
import interface_adapter.portfolio.PortfolioViewModel;

import interface_adapter.searching.SearchViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class PortfolioView extends JPanel implements ActionListener, PropertyChangeListener {
    public final String viewName = "View Portfolio";
    private final PortfolioController portfolioController;

    private final PortfolioViewModel portfolioViewModel;

    private final JButton goBack;

    public PortfolioView(PortfolioController portfolioController, PortfolioViewModel portfolioViewModel,
                         ViewManagerModel viewManagerModel, LoggedInViewModel loggedInViewModel) {
        this.portfolioController = portfolioController;
        this.portfolioViewModel = portfolioViewModel;
        portfolioViewModel.addPropertyChangeListener(this);

        JPanel buttons = new JPanel();
        goBack = new JButton(SearchViewModel.GOBACK);
        buttons.add(goBack);
        buttons.setAlignmentX(Component.CENTER_ALIGNMENT);


        goBack.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (e.getSource().equals(goBack)){
                            viewManagerModel.setActiveView(loggedInViewModel.getViewName());
                            viewManagerModel.firePropertyChanged();
                        }
                    }
                }
        );

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(buttons);
    }
    @Override
    public void actionPerformed(ActionEvent e) {System.out.println("Click" + e.getActionCommand());}

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getSource().equals(portfolioViewModel)) {
            PortfolioState state = (PortfolioState) evt.getNewValue();

            if (state.getError() != null) {
                JOptionPane.showMessageDialog(this, state.getError());
            }
        }
    }
}