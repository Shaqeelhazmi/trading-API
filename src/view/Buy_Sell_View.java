//package view;
//
//import interface_adapter.buy.BuyController;
//import interface_adapter.buy.BuyState;
//import interface_adapter.buy.BuyViewModel;
//import interface_adapter.searching.SearchState;
//
//import interface_adapter.sell.SellController;
//import interface_adapter.sell.SellState;
//import interface_adapter.sell.SellViewModel;
//
//import javax.swing.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.beans.PropertyChangeEvent;
//import java.beans.PropertyChangeListener;
//
//public class Buy_Sell_View extends JPanel implements ActionListener, PropertyChangeListener {
//
//
//    private final SellController sellController;
//
//    private final SellViewModel sellViewModel;
//
//    private final String stockName;
//
//    private final int amount;
//
//    private final JButton buy;
//
//    private final JButton sell;
//
//    public Buy_Sell_View(BuyController buyController, BuyViewModel buyViewModel,
//                         SellController sellcontroller, SellViewModel sellViewModel, String stockName, int amount){
//        this.buyController = buyController;
//        this.buyViewModel = buyViewModel;
//        this.sellController = sellcontroller;
//        this.sellViewModel = sellViewModel;
//        this.stockName = stockName;
//        this.amount = amount;
//
//        buyViewModel.addPropertyChangeListener(this);
//
//        JPanel buttons = new JPanel();
//        buy = new JButton(BuyViewModel.Buy_Button_Label);
//        buttons.add(buy);
//
//        buy.addActionListener(
//                new ActionListener() {
//                    @Override
//                    public void actionPerformed(ActionEvent evt) {
//                        if (evt.getSource().equals(buy)){
//                            buyController.buy(stockName, amount);
//                        }
//                    }
//                }
//        );
//
//        sellViewModel.addPropertyChangeListener(this);
//
//        sell = new JButton(SellViewModel.SELL_BUTTON_LABEL);
//        buttons.add(sell);
//
//        sell.addActionListener(
//                new ActionListener() {
//                    @Override
//                    public void actionPerformed(ActionEvent evt) {
//                        if (evt.getSource().equals(sell)){
//                            sellcontroller.sell(stockName, amount);
//                        }
//                    }
//                }
//        );
//    }
//
//    @Override
//    public void actionPerformed(ActionEvent e) {
//
//    }
//
//    @Override
//    public void propertyChange(PropertyChangeEvent evt) {
//
//    }
//}
