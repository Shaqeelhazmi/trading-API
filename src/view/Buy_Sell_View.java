package view;

import interface_adapter.buy.BuyController;
import interface_adapter.buy.BuyState;
import interface_adapter.buy.BuyViewModel;
import interface_adapter.searching.SearchState;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class Buy_Sell_View extends JPanel implements ActionListener, PropertyChangeListener {

    private final BuyController buyController;

    private final BuyViewModel buyViewModel;

    private final String stockName;

    private final int amount;

    private final JButton buy;

    public Buy_Sell_View(BuyController buyController, BuyViewModel buyViewModel, String stockName, int amount){
        this.buyController = buyController;
        this.buyViewModel = buyViewModel;
        this.stockName = stockName;
        this.amount = amount;

        buyViewModel.addPropertyChangeListener(this);

        JPanel buttons = new JPanel();
        buy = new JButton(BuyViewModel.Buy_Button_Label);
        buttons.add(buy);

        buy.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(buy)){
                            buyController.buy(stockName, amount);
                        }
                    }
                }
        );
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }
}
