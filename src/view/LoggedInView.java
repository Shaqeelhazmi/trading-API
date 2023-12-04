package view;

import interface_adapter.ViewManagerModel;
import interface_adapter.buy.BuyViewModel;
import interface_adapter.logged_in.LoggedInState;
import interface_adapter.logged_in.LoggedInViewModel;
import interface_adapter.login.LoginState;
import interface_adapter.login.LoginViewModel;
import interface_adapter.portfolio.PortfolioController;
import interface_adapter.portfolio.PortfolioState;
import interface_adapter.portfolio.PortfolioViewModel;
import interface_adapter.searching.SearchState;
import interface_adapter.searching.SearchViewModel;
import interface_adapter.signup.SignupViewModel;
import interface_adapter.stock.StockState;
import interface_adapter.stock.StockViewModel;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.HashMap;

public class LoggedInView extends JPanel implements ActionListener, PropertyChangeListener {

    public final String viewName = "logged in";
    private final LoggedInViewModel loggedInViewModel;

    private final ViewManagerModel viewManagerModel;

    private final LoginViewModel loginViewModel;

    private final SearchViewModel searchViewModel;
    private final PortfolioViewModel portfolioViewModel;

    private final StockViewModel stockViewModel;

    private final JButton search_stock;

    private final JButton portfolio;

    private final JButton update;



    Border gridBorder;

    JLabel username;

    final JButton logOut;

    /**
     * A window with a title and a JButton.
     */
    public LoggedInView(LoggedInViewModel loggedInViewModel, ViewManagerModel viewManagerModel, LoginViewModel loginViewModel,
                        SearchViewModel searchViewModel, PortfolioViewModel portfolioViewModel, StockViewModel stockViewModel) {
        this.loggedInViewModel = loggedInViewModel;
        this.viewManagerModel = viewManagerModel;
        this.loginViewModel = loginViewModel;
        this.searchViewModel = searchViewModel;
        this.portfolioViewModel = portfolioViewModel;
        this.stockViewModel = stockViewModel;
        loggedInViewModel.addPropertyChangeListener(this);
        this.setBackground(new Color(60, 47, 47));


        JLabel usernameInfo = new JLabel("Currently logged in: ");
        username = new JLabel();
        usernameInfo.setBackground(new Color(199, 0, 57));

        search_stock = new JButton(LoggedInViewModel.SEARCH_STOCK);
        search_stock.setBackground(new Color(190, 155, 123));
        search_stock.setOpaque(true);
        search_stock.setBorderPainted(false);
        search_stock.setFont(new Font("Helvetica", Font.ITALIC, 30));

        portfolio = new JButton(LoggedInViewModel.PORTFOLIO);
        portfolio.setBackground(new Color(60, 47, 47  ));
        portfolio.setOpaque(true);
        portfolio.setBorderPainted(false);
        portfolio.setFont(new Font("Helvetica", Font.ITALIC, 30));

        update = new JButton(LoggedInViewModel.UPDATE);
        portfolio.setBackground(new Color(255, 244, 230));
        portfolio.setOpaque(true);
        portfolio.setBorderPainted(false);
        portfolio.setFont(new Font("Helvetica", Font.ITALIC, 30));

        JPanel stocks_you_own = new JPanel();
        stocks_you_own.setBackground(new Color(255, 244, 230));

        JPanel bottom_buttons = new JPanel();
        logOut = new JButton(loggedInViewModel.LOGOUT_BUTTON_LABEL);
        bottom_buttons.add(logOut);
        bottom_buttons.setBackground(new Color(60, 47, 47  ));


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

        update.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        stocks_you_own.removeAll();
                        PortfolioState current = portfolioViewModel.getState();
                        HashMap<String, Integer> stocks = current.getStocksOwned();
//                        stocks_you_own.setText(stocks.keySet().toString());
                        for(String stock: stocks.keySet()){
                            JButton stock1 = new JButton();
                            stock1.setText(stock + " , Amount you own: " + stocks.get(stock));
                            stocks_you_own.add(stock1);
                            stocks_you_own.revalidate();


                            stock1.addActionListener(
                                    new ActionListener() {
                                        @Override
                                        public void actionPerformed(ActionEvent e) {
                                            SearchState state = searchViewModel.getSearchState();
                                            state.setSearchName(stock1.getText());
                                            StockState stockState = stockViewModel.getStockState();
                                            stockState.setStockSymbol(stock1.getText());
                                            ArrayList<String> information = state.getStoredStocks().get(stock1.getText());
                                            state.setInformation(information);
                                            viewManagerModel.setActiveView(stockViewModel.getViewName());
                                            viewManagerModel.firePropertyChanged();
                                        }
                                    }
                            );
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
                            switch_to_login();
                        }
                    }
                }
        );

        this.setLayout(new GridLayout(3, 2, 5, 10));
        this.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        this.add(search_stock);
        this.add(portfolio);
        this.add(update);
        this.add(stocks_you_own);
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

    private void switch_to_login(){
        viewManagerModel.setActiveView(loginViewModel.getViewName());
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