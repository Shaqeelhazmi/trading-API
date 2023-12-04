package view;

import interface_adapter.ViewManagerModel;
import interface_adapter.ViewModel;
import interface_adapter.buy.BuyViewModel;
import interface_adapter.logged_in.LoggedInViewModel;
import interface_adapter.searching.SearchState;
import interface_adapter.searching.SearchViewModel;
import interface_adapter.sell.SellViewModel;
import interface_adapter.stock.StockState;
import interface_adapter.stock.StockViewModel;
import org.knowm.xchart.*;
import org.knowm.xchart.style.lines.SeriesLines;
import org.knowm.xchart.style.markers.SeriesMarkers;

import javax.swing.*;
import javax.swing.text.View;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

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
        SearchState searchState = searchViewModel.getSearchState();
        stockViewModel.addPropertyChangeListener(this);

        String stockSymbol = stockViewModel.getStockState().getStockSymbol();
        String stockName = stockViewModel.getStockState().getStockName();


        JLabel title = new JLabel(stockName);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        List<Date> xData = new ArrayList<>();
        List<Double> yData = new ArrayList<>();

        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        ArrayList<String> priceHistory = searchState.getInformation();

        if (priceHistory != null) {
//            priceHistory.remove(0);
//            for (int i = 0; i < searchState.getDay_list().size() - 1; i++) {
//                try {
//                    date = sdf.parse(searchState.getDay_list().get(i));
//                } catch (ParseException e) {
//                    e.printStackTrace();
//                }
//                xData.add(date);
//                yData.add(Double.parseDouble(priceHistory.get(i)));
//            }
//
//            XYChart chart = new XYChartBuilder().width(800).height(600).title(stockName).xAxisTitle("Date").yAxisTitle("Price").build();
//            XYSeries series = chart.addSeries(stockSymbol, xData, yData);
//            series.setLineColor(Color.BLUE);
//            series.setLineStyle(SeriesLines.SOLID);
//            series.setMarker(SeriesMarkers.CIRCLE);
//            series.setMarkerColor(Color.RED);
//
//            XChartPanel chartPanel = new XChartPanel<>(chart);

            double[] testdata = new double[2];
            double[] testdata2 = new double[2];

            XYChart chart = QuickChart.getChart("Test", "x", "y", "test", testdata, testdata2);
            XChartPanel chartPanel = new XChartPanel<>(chart);

            this.add(chartPanel);
        }


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

        this.add(buttons);
        this.revalidate();
    }

    public void actionPerformed(ActionEvent evt) {

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
    }
}
