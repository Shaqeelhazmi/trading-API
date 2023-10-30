package buy;

import use_case.buy.BuyOutputBoundary;
import use_case.buy.BuyOutputData;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class BuyPresenter implements BuyOutputBoundary {

    private final ViewManagerModel viewManagerModel;

    private final BuyViewModel buyViewModel;


    public BuyPresenter(ViewManagerModel viewManagerModel, BuyViewModel buyViewModel){
        this.viewManagerModel = viewManagerModel;
        this.buyViewModel = buyViewModel;
    }
    @Override
    public void prepareNotAvailable(String message) {
        BuyState buyState = buyViewModel.getBuyState();
        buyState.SetBuyError(message);
        BuyViewModel.firePropertyChanged();
    }

    @Override
    public void prepareSuccessView(BuyOutputData response) {
        LocalDateTime responseTime = LocalDateTime.parse(response.getCreationTime());
        response.setCreationTime(responseTime.format(DateTimeFormatter.ofPattern("hh:mm:ss")));
        BuyState buyState = buyViewModel.getBuyState();
        buyState.SetBuySuccess();
        BuyViewModel.firePropertyChanged();
    }
}
