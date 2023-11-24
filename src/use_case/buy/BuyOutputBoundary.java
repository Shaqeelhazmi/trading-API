package use_case.buy;
public interface BuyOutputBoundary {
    void prepareFailView(String message);

    void prepareSuccessView(BuyOutputData buyOutputData);

}
