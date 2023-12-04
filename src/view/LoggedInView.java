package view;

import interface_adapter.ViewManagerModel;
import interface_adapter.buy.BuyViewModel;
import interface_adapter.logged_in.LoggedInState;
import interface_adapter.logged_in.LoggedInViewModel;
import interface_adapter.login.LoginState;
import interface_adapter.portfolio.PortfolioController;
import interface_adapter.portfolio.PortfolioViewModel;
import interface_adapter.searching.SearchViewModel;
import interface_adapter.signup.SignupViewModel;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class LoggedInView extends JPanel implements ActionListener, PropertyChangeListener {

    public final String viewName = "logged in";
    private final LoggedInViewModel loggedInViewModel;

    private final ViewManagerModel viewManagerModel;

    private final SignupViewModel signupViewModel;

    private final SearchViewModel searchViewModel;
    private final PortfolioViewModel portfolioViewModel;

    private final JButton search_stock;

    private final JButton portfolio;

    Border gridBorder;

    JLabel username;

    final JButton logOut;

    /**
     * A window with a title and a JButton.
     */
    public LoggedInView(LoggedInViewModel loggedInViewModel, ViewManagerModel viewManagerModel, SignupViewModel signupViewModel, SearchViewModel searchViewModel, PortfolioViewModel portfolioViewModel) {
        this.loggedInViewModel = loggedInViewModel;
        this.viewManagerModel = viewManagerModel;
        this.signupViewModel = signupViewModel;
        this.searchViewModel = searchViewModel;
        this.portfolioViewModel = portfolioViewModel;
        loggedInViewModel.addPropertyChangeListener(this);
        this.setBackground(new Color(199, 0, 57));


        JLabel usernameInfo = new JLabel("Currently logged in: ");
        username = new JLabel();
        usernameInfo.setBackground(new Color(199, 0, 57));

        search_stock = new JButton(LoggedInViewModel.SEARCH_STOCK);
        search_stock.setBackground(new Color(253, 253, 84));
        search_stock.setOpaque(true);
        search_stock.setBorderPainted(false);
        search_stock.setFont(new Font("Helvetica", Font.ITALIC, 30));

        portfolio = new JButton(LoggedInViewModel.PORTFOLIO);
        portfolio.setBackground(new Color(84, 128, 245));
        portfolio.setOpaque(true);
        portfolio.setBorderPainted(false);
        portfolio.setFont(new Font("Helvetica", Font.ITALIC, 30));


        JPanel bottom_buttons = new JPanel();
        logOut = new JButton(loggedInViewModel.LOGOUT_BUTTON_LABEL);
        bottom_buttons.add(logOut);
        bottom_buttons.setBackground(new Color(218, 247, 166));


        search_stock.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (e.getSource().equals(search_stock)){
                            switch_to_search();
                        }
                    }
                }
        );

        portfolio.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (e.getSource().equals(portfolio)){
                            switch_to_portfolio();
                        }
                    }
                }
        );


        logOut.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (e.getSource().equals(logOut)){
                            switch_to_signUp();
                        }
                    }
                }
        );


        this.setLayout(new GridLayout(3, 2, 5, 10));
        this.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        this.add(search_stock);
        this.add(portfolio);
        this.add(bottom_buttons);

    }


    /**
     * React to a button click that results in evt.
     */
    public void actionPerformed(ActionEvent evt) {
        System.out.println("Click " + evt.getActionCommand());
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        LoggedInState state = (LoggedInState) evt.getNewValue();
        username.setText(state.getUsername());
        gridBorder = BorderFactory.createTitledBorder("Currently logged in: " + username.getText());
        this.setBorder(gridBorder);
    }

    private void switch_to_signUp(){
        viewManagerModel.setActiveView(signupViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    private void switch_to_search(){
        SearchViewModel searchViewModel = new SearchViewModel();
        searchViewModel.firePropertyChanged();
        viewManagerModel.setActiveView(searchViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    private void switch_to_portfolio(){
        PortfolioViewModel portfolioViewModel = new PortfolioViewModel();
        portfolioViewModel.firePropertyChanged();
        viewManagerModel.setActiveView(portfolioViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}