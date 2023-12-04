package interface_adapter.sell;

import interface_adapter.ViewManagerModel;
import interface_adapter.logged_in.LoggedInState;
import interface_adapter.logged_in.LoggedInViewModel;
import interface_adapter.searching.SearchViewModel;
import interface_adapter.stock.StockViewModel;
import use_case.sell.SellOutputBoundary;
import use_case.sell.SellOutputData;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class SellPresenter implements SellOutputBoundary {
    private ViewManagerModel viewManagerModel;

    private final SellViewModel sellViewModel;
    private SearchViewModel searchViewModel;

    private StockViewModel stockViewModel;

    private LoggedInViewModel loggedInViewModel;


    public SellPresenter(ViewManagerModel viewManagerModel, SellViewModel sellViewModel, SearchViewModel searchViewModel, StockViewModel stockViewModel
    , LoggedInViewModel loggedInViewModel){
        this.viewManagerModel = viewManagerModel;
        this.sellViewModel = sellViewModel;
        this.searchViewModel = searchViewModel;
        this.stockViewModel = stockViewModel;
        this.loggedInViewModel = loggedInViewModel;
    }
    @Override
    public void prepareFailView(String message) {
        SellState sellState = sellViewModel.getSellState();
        sellState.setSellError(message);
        sellViewModel.firePropertyChanged();
    }

    @Override
    public void prepareSuccessView(SellOutputData response) {
        LocalDateTime responseTime = LocalDateTime.parse(response.getCreationTime());
        response.setCreationTime(responseTime.format(DateTimeFormatter.ofPattern("hh:mm:ss")));
        SellState sellState = sellViewModel.getSellState();
        LoggedInState balance  = loggedInViewModel.getState();
        balance.setAccountBalance(response.getAccountBalance());
        sellState.setSuccessed(true);
        sellState.setSellSuccess("You sold " + response.getStockSold());

        sellViewModel.firePropertyChanged();
    }

}
