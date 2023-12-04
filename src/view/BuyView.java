package view;

import interface_adapter.ViewManagerModel;
import interface_adapter.buy.BuyController;
import interface_adapter.buy.BuyState;
import interface_adapter.buy.BuyViewModel;
import interface_adapter.logged_in.LoggedInState;
import interface_adapter.logged_in.LoggedInViewModel;
import interface_adapter.searching.SearchState;
import interface_adapter.searching.SearchViewModel;
import interface_adapter.stock.StockState;
import interface_adapter.stock.StockViewModel;

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

    public final String viewName = "Buy Stock";
    private final BuyController buyController;

    private final BuyViewModel buyViewModel;

    private final LoggedInViewModel loggedInViewModel;

    private final SearchViewModel searchViewModel;

    private final StockViewModel stockViewModel;

    private final JButton home;

    private final JButton back;

    private String stockName;

    private String stockSymbol;


    private final JTextField amount = new JTextField(15);

    private final JButton buy;

    public BuyView(BuyController buyController, BuyViewModel buyViewModel, LoggedInViewModel loggedInViewModel,
                   SearchViewModel searchViewModel, ViewManagerModel viewManagerModel, StockViewModel stockViewModel){
        this.buyController = buyController;
        this.buyViewModel = buyViewModel;
        this.loggedInViewModel = loggedInViewModel;
        this.searchViewModel = searchViewModel;
        this.stockViewModel = stockViewModel;


        String stockSymbol = buyViewModel.getBuyState().getStockSymbol();
        String stockName = buyViewModel.getBuyState().getStockName();
        buyViewModel.addPropertyChangeListener(this);

        JLabel title = new JLabel(stockName);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        LabelTextPanel amountInfo = new LabelTextPanel(
        new JLabel(BuyViewModel.Amount_Label), amount);
        title.setFont(new Font("Cambria", Font.ITALIC, 50));
        amountInfo.setBackground(new Color(199, 0, 57));
        amount.setFont(new Font("Helvetica", Font.ITALIC, 50));

        JPanel buttons = new JPanel();
        buy = new JButton(BuyViewModel.Buy_Button_Label);
        buttons.add(buy);

        home = new JButton(BuyViewModel.HOME);
        buttons.add(home);

        back = new JButton(BuyViewModel.GOBACK);
        buttons.add(back);


        buy.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(buy)){
                            StockState currentState = stockViewModel.getStockState();
                            BuyState state = buyViewModel.getBuyState();
                            LoggedInState user = loggedInViewModel.getState();
                            try {
                                buyController.buy(currentState.getStockSymbol(), state.getAmount(), user.getUsername());
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
                        BuyState currentState = buyViewModel.getBuyState();
                        char c = e.getKeyChar();
                        if (!Character.isDigit(c)){
                            e.consume();
                        } else {
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

        back.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (e.getSource().equals(back)){
                            viewManagerModel.setActiveView(stockViewModel.getViewName());
                            viewManagerModel.firePropertyChanged();
                        }
                    }
                }
        );

        home.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (e.getSource().equals(home)){
                            viewManagerModel.setActiveView(loggedInViewModel.getViewName());
                            viewManagerModel.firePropertyChanged();
                        }
                    }
                }
        );
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(title);
        this.add(amountInfo);
        this.add(buttons);
    }

    @Override
    public void actionPerformed(ActionEvent e) {System.out.println("Click" + e.getActionCommand());}

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        BuyState state = (BuyState) evt.getNewValue();
        if(state.getSuccessed()) {
            JOptionPane.showMessageDialog(this, state.getBuySuccess());
            state.setSuccessed(false);
        } else if (state.getFailed()){
            JOptionPane.showMessageDialog(this, state.getError());
            state.setFailed(false);
        }
    }
}
