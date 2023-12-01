package view;

import interface_adapter.buy.BuyController;
import interface_adapter.buy.BuyState;
import interface_adapter.buy.BuyViewModel;
import interface_adapter.logged_in.LoggedInViewModel;
import interface_adapter.searching.SearchViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;


public class BuyView extends JPanel implements ActionListener, PropertyChangeListener {

    private final BuyController buyController;

    private final BuyViewModel buyViewModel;

    private final LoggedInViewModel loggedInViewModel;

    private final SearchViewModel searchViewModel;

    private String stockSymbol;
    private String stockName;

    private final JTextField amount = new JTextField();

    private final JButton buy;

    public BuyView(BuyController buyController, BuyViewModel buyViewModel, LoggedInViewModel loggedInViewModel, SearchViewModel searchViewModel){
        this.buyController = buyController;
        this.buyViewModel = buyViewModel;
        this.loggedInViewModel = loggedInViewModel;
        this.searchViewModel = searchViewModel;


        String stockSymbol = buyViewModel.getBuyState().getStockSymbol();
        String stockName = buyViewModel.getBuyState().getStockName();
        buyViewModel.addPropertyChangeListener(this);

        JLabel title = new JLabel(stockName);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        LabelTextPanel amountInfo = new LabelTextPanel(
        new JLabel(BuyViewModel.Amount_Label), amount);

        JPanel buttons = new JPanel();
        buy = new JButton(BuyViewModel.Buy_Button_Label);
        buttons.add(buy);


        buy.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(buy)){
                            BuyState currentState = buyViewModel.getBuyState();
                            try {
                                buyController.buy(stockSymbol, currentState.getAmount(), currentState.getUsername());
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                        }
                    }
                }
        );

        amount.addKeyListener(
                new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {
                        char c = e.getKeyChar();
                        if (!Character.isDigit(c)){
                            e.consume();
                        } else {
                            BuyState currentState = buyViewModel.getBuyState();
                            int amount_buy = Integer.parseInt(amount.getText() + e.getKeyChar());
                            currentState.setAmount(amount_buy);
                            buyViewModel.setBuyState(currentState);
                        }
                    }

                    @Override
                    public void keyPressed(KeyEvent e) {

                    }

                    @Override
                    public void keyReleased(KeyEvent e) {

                    }
                }
        );
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(title);
        this.add(amountInfo);
        this.add(buttons);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }
}
