package use_case.sell;

public interface SellOutputBoundary {
    void prepareSuccessView(SellOutputData sellOutputData);

    void prepareFailView(String error);
}
