package use_case.sell;

public interface SellOutputBoundary {
    void prepareNotAvailable(String message);

    void prepareSuccessView(SellOutputData sellOutputData);
}
