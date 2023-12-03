package interface_adapter.buy;

import interface_adapter.ViewManagerModel;
import interface_adapter.searching.SearchViewModel;
import use_case.buy.BuyOutputBoundary;
import use_case.buy.BuyOutputData;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class BuyPresenter implements BuyOutputBoundary {

    private ViewManagerModel viewManagerModel;

    private final BuyViewModel buyViewModel;

    private SearchViewModel searchViewModel;


    public BuyPresenter(ViewManagerModel viewManagerModel, BuyViewModel buyViewModel, SearchViewModel searchViewModel){
        this.viewManagerModel = viewManagerModel;
        this.buyViewModel = buyViewModel;
        this.searchViewModel = searchViewModel;
    }
    @Override
    public void prepareFailView(String message) {
        BuyState buyState = buyViewModel.getBuyState();
        buyState.SetBuyError(message);
        buyViewModel.firePropertyChanged();
    }

    @Override
    public void prepareSuccessView(BuyOutputData response) {
        LocalDateTime responseTime = LocalDateTime.parse(response.getCreationTime());
        response.setCreationTime(responseTime.format(DateTimeFormatter.ofPattern("hh:mm:ss")));
        BuyState buyState = buyViewModel.getBuyState();
        buyState.SetBuySuccess();
        viewManagerModel.setActiveView(searchViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
