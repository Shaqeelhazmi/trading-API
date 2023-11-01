package use_case.buy;
public interface BuyOutputBoundary {
    void prepareNotAvailable(String message);

    void prepareSuccessView(BuyOutputData buyOutputData);

    void prepareNotEnough(String message);
}
