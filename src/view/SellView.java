//package view;
//
//import interface_adapter.sell.SellController;
//import interface_adapter.sell.SellState;
//import interface_adapter.sell.SellViewModel;
//
//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.awt.event.KeyEvent;
//import java.awt.event.KeyListener;
//import java.beans.PropertyChangeEvent;
//import java.beans.PropertyChangeListener;
//public class SellView extends JPanel implements ActionListener, PropertyChangeListener{
//    public final String viewName = "sell";
//    private final SellViewModel sellViewModel;
//    private final JTextField stockInputField = new JTextField(15);
//    private final JTextField amountInputField = new JTextField(15);
//    private final SellController sellController;
//    private final JButton sell;
//
//    public SellView(SellController controller, SellViewModel sellViewModel){
//        this.sellController = controller;
//        this.sellViewModel = sellViewModel;
//        sellViewModel.addPropertyChangeListener(this);
//
//        JLabel title = new JLabel(sellViewModel.TITLE_LABEL);
//        title.setAlignmentX(Component.CENTER_ALIGNMENT);
//
//        // Cannot resolve LabelTextPanel for an unknown reason
//        LabelTextPanel  stockInfo = new LabelTextPanel(
//                new JLabel(sellViewModel.STOCK_LABEL), stockInputField);
//        LabelTextPanel  amountInfo = new LabelTextPanel(
//                new JLabel(sellViewModel.AMOUNT_LABEL), amountInputField);
//
//
//        JPanel buttons = new JPanel();
//        sell = new JButton(sellViewModel.SELL_BUTTON_LABEL);
//        buttons.add(sell);
//
//        sell.addActionListener(
//                new ActionListener() {
//                    @Override
//                    public void actionPerformed(ActionEvent e) {
//                        if (e.getSource().equals(sell)){
//                            // Need to figure out how to match the given string to Stock object
//                            sellController.sell(stockInputField.getText(), Integer.parseInt(amountInputField.getText()));
//                        }
//                    }
//                }
//        );
//
//        stockInputField.addKeyListener(
//                new KeyListener() {
//                    @Override
//                    public void keyTyped(KeyEvent e) {
//                        SellState currentState = sellViewModel.getSellState();
//
//                    }
//
//                    @Override
//                    public void keyPressed(KeyEvent e) {
//                    }
//
//                    @Override
//                    public void keyReleased(KeyEvent e) {
//                    }
//                });
//
//        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
//
//        this.add(title);
//        this.add(stockInfo);
//        this.add(amountInfo);
//        this.add(buttons);
//    }
//    public void actionPerformed(ActionEvent e) {
//        System.out.println("Cancel not implemented yet.");
//    }
//
//    @Override
//    public void propertyChange(PropertyChangeEvent evt) {
//        SellState state = (SellState) evt.getNewValue();
//        if (state.getSellError() != null) {
//            JOptionPane.showMessageDialog(this, state.getSellError());
//        }
//    }
//
//}
