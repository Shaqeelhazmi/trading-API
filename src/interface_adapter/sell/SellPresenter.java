package interface_adapter.sell;

import interface_adapter.ViewManagerModel;
import use_case.sell.SellInputBoundary;
import use_case.sell.SellOutputBoundary;
import use_case.sell.SellOutputData;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class SellPresenter implements SellOutputBoundary {
    private ViewManagerModel viewManagerModel;

    private final SellViewModel sellViewModel;

    public SellPresenter(ViewManagerModel viewManagerModel, SellViewModel sellViewModel){
        this.viewManagerModel = viewManagerModel;
        this.sellViewModel = sellViewModel;
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
        // We change to another view after a successful transaction, not added yet.
        sellState.setSellSuccess();
        sellViewModel.firePropertyChanged();
    }

}
