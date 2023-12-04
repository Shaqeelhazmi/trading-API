package view;

import interface_adapter.ViewManagerModel;
import interface_adapter.ViewModel;
import interface_adapter.buy.BuyViewModel;
import interface_adapter.logged_in.LoggedInViewModel;
import interface_adapter.searching.SearchViewModel;
import interface_adapter.sell.SellViewModel;
import interface_adapter.stock.StockState;
import interface_adapter.stock.StockViewModel;

import javax.swing.*;
import javax.swing.text.View;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class StockView extends JPanel implements ActionListener, PropertyChangeListener {

    public final String viewName = "Stock";
    private final JButton homeButton;
    private final JButton backButton;
    private final JButton buyButton;
    private final JButton sellButton;
    private final StockViewModel stockViewModel;
    private final SearchViewModel searchViewModel;


    public StockView(StockViewModel stockViewModel, BuyViewModel buyViewModel, SellViewModel sellViewModel,
                     LoggedInViewModel loggedInViewModel, ViewManagerModel viewManagerModel, SearchViewModel searchViewModel){
        this.stockViewModel = stockViewModel;
        this.searchViewModel = searchViewModel;
        stockViewModel.addPropertyChangeListener(this);

        String stockSymbol = stockViewModel.getStockState().getStockSymbol();
        String stockName = stockViewModel.getStockState().getStockName();


        JLabel title = new JLabel(stockName);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel buttons = new JPanel();

        buyButton = new JButton(StockViewModel.Buy_Button_Label);
        buttons.add(buyButton);

        sellButton = new JButton(StockViewModel.Sell_Button_Label);
        buttons.add(sellButton);

        homeButton = new JButton(StockViewModel.HOME);
        buttons.add(homeButton);

        backButton = new JButton(StockViewModel.GOBACK);
        buttons.add(backButton);

        buyButton.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (e.getSource().equals(buyButton)) {
                            viewManagerModel.setActiveView(buyViewModel.getViewName());
                            viewManagerModel.firePropertyChanged();
                        }
                    }
                }
        );
        sellButton.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (e.getSource().equals(sellButton)) {
                            viewManagerModel.setActiveView(sellViewModel.getViewName());
                            viewManagerModel.firePropertyChanged();
                        }
                    }
                }
        );
        homeButton.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (e.getSource().equals(homeButton)) {
                            viewManagerModel.setActiveView(loggedInViewModel.getViewName());
                            viewManagerModel.firePropertyChanged();
                        }
                    }
                }
        );
        backButton.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (e.getSource().equals(backButton)) {
                            viewManagerModel.setActiveView(searchViewModel.getViewName());
                            viewManagerModel.firePropertyChanged();
                        }
                    }
                }
        );
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(buttons);
    }

    public void actionPerformed(ActionEvent evt) {

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }
}
