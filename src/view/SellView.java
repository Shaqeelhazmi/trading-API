package view;

import interface_adapter.sell.SellController;
import interface_adapter.sell.SellState;
import interface_adapter.sell.SellViewModel;

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
    private final JTextField amountInputField = new JTextField(15);
    private final SellController sellController;
    private final JButton sell;
    private final String stockName;

    private final String userName;

    public SellView(SellController controller, SellViewModel sellViewModel, String stockName, int amount, String userName){
        this.sellController = controller;
        this.sellViewModel = sellViewModel;
        this.stockName = stockName;
        this.userName = userName;

        sellViewModel.addPropertyChangeListener(this);

        JLabel title = new JLabel(sellViewModel.TITLE_LABEL);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        LabelTextPanel amountInfo = new LabelTextPanel(
                new JLabel(sellViewModel.AMOUNT_LABEL), amountInputField);

        JPanel buttons = new JPanel();
        sell = new JButton(sellViewModel.SELL_BUTTON_LABEL);
        buttons.add(sell);

        sell.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (e.getSource().equals(sell)){
                            try {
                                sellController.sell(stockName, Integer.parseInt(amountInputField.getText()), userName);
                            } catch (IOException ex) {
                                throw new RuntimeException(ex);
                            }
                        }
                    }
                }
        );

        amountInputField.addKeyListener(
                new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {
                        SellState currentState = sellViewModel.getSellState();
                        currentState.setAmount(Integer.parseInt(amountInputField.getText()) + e.getKeyChar());
                        sellViewModel.setState(currentState);
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
        System.out.println("actionPerformed");
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        SellState state = (SellState) evt.getNewValue();
        if (state.getSellError() != null) {
            JOptionPane.showMessageDialog(this, state.getSellError());
        }
    }
}
