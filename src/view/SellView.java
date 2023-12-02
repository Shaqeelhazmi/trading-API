package view;

import interface_adapter.sell.SellController;
import interface_adapter.sell.SellState;
import interface_adapter.sell.SellViewModel;
import interface_adapter.logged_in.LoggedInViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;

public class SellView extends JPanel implements ActionListener, PropertyChangeListener{
    public final String viewName = "sell";
    private final SellViewModel sellViewModel;
    private final LoggedInViewModel loggedInViewModel;
    private final JTextField amountInputField = new JTextField(15);
    private final SellController sellController;
    private final JButton sell;
    private String stockSymbol;
    private String stockName;

    public SellView(SellController controller, SellViewModel sellViewModel, LoggedInViewModel loggedInViewModel){
        this.sellController = controller;
        this.sellViewModel = sellViewModel;
        this.loggedInViewModel = loggedInViewModel;

        String stockSymbol = sellViewModel.getSellState().getStockSymbol();
        String stockName = sellViewModel.getSellState().getStockName();

        sellViewModel.addPropertyChangeListener(this);

        JLabel title = new JLabel(sellViewModel.TITLE_LABEL);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        LabelTextPanel  amountInfo = new LabelTextPanel(
                new JLabel(sellViewModel.AMOUNT_LABEL), amountInputField);


        JPanel buttons = new JPanel();
        sell = new JButton(sellViewModel.SELL_BUTTON_LABEL);
        buttons.add(sell);

        sell.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(sell)){
                            SellState currentState = sellViewModel.getSellState();
                            try {
                                sellController.sell(stockSymbol, currentState.getAmount(), currentState.getUsername());
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                        }
                    }
                }
        );

        amountInputField.addKeyListener(
                new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {
                        char c = e.getKeyChar();
                        if (!Character.isDigit(c)){
                            e.consume();
                        } else {
                            SellState currentState = sellViewModel.getSellState();
                            int amount = Integer.parseInt(amountInputField.getText() + e.getKeyChar());
                            currentState.setAmount(amount);
                            sellViewModel.setState(currentState);
                        }

                    }

                    @Override
                    public void keyPressed(KeyEvent e) {
                    }

                    @Override
                    public void keyReleased(KeyEvent e) {
                    }
                });

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(title);
        this.add(amountInfo);
        this.add(buttons);
    }
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }

}
