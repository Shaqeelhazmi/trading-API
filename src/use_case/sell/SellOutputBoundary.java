package use_case.sell;

public class SellOutputBoundary {
    void prepareSuccessView(SellOutputData sellOutputData);

    void prepareFailView(String error);
}
