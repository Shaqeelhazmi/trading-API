package view;

import interface_adapter.buy.BuyController;
import interface_adapter.buy.BuyState;
import interface_adapter.buy.BuyViewModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class BuyView extends JPanel implements ActionListener, PropertyChangeListener {

    private final BuyController buyController;

    private final BuyViewModel buyViewModel;

    private final JButton buy;

    public BuyView(BuyController controller, BuyViewModel buyViewModel) {
        this.buyController = controller;
        this.buyViewModel = buyViewModel;
        buyViewModel.addPropertyChangeListener(this);

        JPanel buttons = new JPanel();
        buy = new JButton(BuyViewModel.Buy_Button_Label);
        buttons.add(buy);

        buy.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt) {

                    }
                });
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }
}
