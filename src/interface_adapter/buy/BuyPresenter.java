package interface_adapter.buy;

import interface_adapter.ViewManagerModel;
import interface_adapter.logged_in.LoggedInState;
import interface_adapter.logged_in.LoggedInViewModel;
import interface_adapter.searching.SearchViewModel;
import interface_adapter.stock.StockViewModel;
import use_case.buy.BuyOutputBoundary;
import use_case.buy.BuyOutputData;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class BuyPresenter implements BuyOutputBoundary {

    private ViewManagerModel viewManagerModel;

    private final BuyViewModel buyViewModel;

    private SearchViewModel searchViewModel;

    private StockViewModel stockViewModel;

    private LoggedInViewModel loggedInViewModel;


    public BuyPresenter(ViewManagerModel viewManagerModel, BuyViewModel buyViewModel, SearchViewModel searchViewModel,
                        StockViewModel stockViewModel, LoggedInViewModel loggedInViewModel){
        this.viewManagerModel = viewManagerModel;
        this.buyViewModel = buyViewModel;
        this.searchViewModel = searchViewModel;
        this.stockViewModel =stockViewModel;
        this.loggedInViewModel = loggedInViewModel;
    }
    @Override
    public void prepareFailView(String message) {
        BuyState buyState = buyViewModel.getBuyState();
        buyState.SetBuyError(message);
        buyState.setFailed(true);
        buyViewModel.firePropertyChanged();
    }

    @Override
    public void prepareSuccessView(BuyOutputData response) {
        LocalDateTime responseTime = LocalDateTime.parse(response.getCreationTime());
        response.setCreationTime(responseTime.format(DateTimeFormatter.ofPattern("hh:mm:ss")));
        BuyState buyState = buyViewModel.getBuyState();
        LoggedInState balance = loggedInViewModel.getState();
        balance.setAccountBalance(response.getBalance());
        buyState.setSuccessed(true);
        buyState.SetBuySuccess("You bought " + response.getAmount() +" "+ response.getStockBought());

        buyViewModel.firePropertyChanged();
    }
}
